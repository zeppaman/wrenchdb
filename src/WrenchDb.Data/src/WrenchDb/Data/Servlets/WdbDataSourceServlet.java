/* 
 * Copyright (C) 2013 Daniele Fontani
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package WrenchDb.Data.Servlets;

import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.Core.Helpers.JSONHelper;
import WrenchDb.Core.Helpers.WdbStringHelper;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.Data.Configuration.CrudTableSet;
import WrenchDb.Data.Enums.DataSourceMode;
import static WrenchDb.Data.Enums.DataSourceMode.LIST;
import WrenchDb.Data.Enums.JqGridFieldType;
import WrenchDb.Data.Enums.SqlComparators;
import WrenchDb.Data.Helpers.*;
import WrenchDb.Data.Model.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.swing.MenuItemLayoutHelper;

/**
 *
 * @author d.fontani
 */
//WrenchDb.Data.Servlets.WdbDataSourceServlet
public class WdbDataSourceServlet 
extends HttpServlet {
    
   @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        try 
        {
            CrudTableSet cs= new CrudTableSet();
            cs.LoadItems();
            
            CrudTable ct= cs.GetByName(req.getParameter("ctable"));
            DataSourceMode mode= GetDataSourceMode(req);
            
            switch(mode)
            {
                case ADD:
                     doAdd(ct, req, resp);
                    break;
                case DELETE:
                    doDelete(ct, req, resp);
                    break;
                case EDIT:
                     doEdit(ct, req, resp);
                    break;
                case LIST:
                    doList(ct, req, resp);
                    break;
                 case LOOKUP:
                    doLookup(ct, req, resp);
                    break;
                default:
                    throw new Exception("DataSourceMode not supported yet");
            }
              
        }
        catch(Exception err)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"WdbDataSourceServlet.execute()",err);
        }
    }

    public void WriteDataRow(StringBuilder json, Object[] row,int pkidx) {
     
        json.append("{");
        if(row.length>0)
        {            
            //Write id
            if(pkidx>-1)
            {
                json.append("\"id\"");       
                json.append(":");
                json.append(JSONHelper.ConvertBaseType(row[pkidx]));
                 json.append(",");
            }
             json.append("\"cell\":[");
            json.append(  JSONHelper.ConvertBaseType(row[0]));
        
        
            for(int k=1;k<row.length;k++)
            {    json.append(",");        
                json.append(JSONHelper.ConvertBaseType(row[k]));
            }
              json.append("]");
        }
        
        json.append("}");
    }

    public StringBuilder GetJqGridFromList(List l,CrudTable ct) {
        StringBuilder json= new StringBuilder();
        json.append("{");
        json.append("\"total\": \""+l.size()+"\",");
        json.append("\"page\": \"1\",");
        json.append("\"records\": \""+l.size()+"\",");
        json.append("\"rows\":[");
        Object[] row;
        if(l.size()>0)
        {
          //write ID
        int pkIdx= ct.GetPkIdx();
       
            
          WriteDataRow(json, (Object[])l.get(0),pkIdx);
          for(int i=1;i<l.size();i++)
          {
             json.append(",");
             WriteDataRow(json, (Object[])l.get(i),pkIdx);

          }
        }
        json.append("]");
        json.append("}");
        return json;
    }

    private DataSourceMode GetDataSourceMode(HttpServletRequest req) {
        if( WdbStringHelper.isBlank(req.getParameter("oper")) && WdbStringHelper.isBlank(req.getParameter("mode")))
        {
            return DataSourceMode.LIST;
        }else
        if("add".equals(req.getParameter("oper")))
        {
             return DataSourceMode.ADD;
        }else
        if("edit".equals(req.getParameter("oper")))
        {
             return DataSourceMode.EDIT;
        }
        else
        if("del".equals(req.getParameter("oper")))
        {
             return DataSourceMode.DELETE;
        }
        if("lookup".equals( req.getParameter("mode")))
        {
            return DataSourceMode.LOOKUP;
        }
        
        return DataSourceMode.LIST;
        
    }

    public void doList(CrudTable ct, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try
        {
            
        SelectBuilder sb= new SelectBuilder();
        //TODO: avoid double field inclusion
        String ColName=null;
        
        for( CrudTableColumn cc:ct.Columns)
        {
            
            ColName=(cc.ColumnName==null)?cc.Name:cc.ColumnName;
            if(!ColName.contains("."))
                ColName=ct.TableName+"."+ColName;
            
            
                sb.Field(ColName);
           
        }
        
        sb.From(ct.TableName);
        
        //JOIN
        for( CrudTableColumn cc:ct.Columns)
        {
            if(cc.FieldType== JqGridFieldType.Lookup)
            {
                //ADD JOIN
                sb.LeftJoin(ct.TableName+"."+cc.Name,cc.LookupTable,cc.LookupId," lt_"+cc.Name); //LOOKUP FOR...                
            }
        }
        //TODO: parse filters and add where conditions
        sb.OrderBy(ct.DefaultSortColumnName,ct.DefaultSortColumnOrder);
        
       String sql= sb.toString();
       
       //TODO: FIX IN GLOBAL CONFIGURATION
       HContext.Init( req.getServletContext().getRealPath("WEB-INF/wdb_db.properties"));
        
       List l= HContext
            .Current()
            .ExecuteSelectQuery(sql);
       
          
          StringBuilder json = GetJqGridFromList(l,ct);
        
         
         
          resp.getWriter().write(json.toString());
          resp.setContentType("application/json");
          
        }
        catch(Exception err)
        {
            resp.setStatus(500);            
            resp.getWriter().append(err.getMessage());
            resp.sendError(500, err.getMessage());
        }
    }

    private void doEdit(CrudTable ct, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int pkIdx=ct.GetPkIdx();
        if(pkIdx<0)
        {
           throw new Exception("Unable to edit row without pk definition on "+ct.getName());
        }
        CrudTableColumn pkColumn= ct.Columns.get(pkIdx);
        
        UpdateBuilder ub= new UpdateBuilder();
        ub.Update(ct.TableName);
          for(CrudTableColumn ctc: ct.Columns)
          {
              if(ctc.IsEditable)
              {
                  ub.Field(ctc.Name,req.getParameter(ctc.Name),ctc.DbType);
              }
          }
          ub.AndWhere(pkColumn.Name,SqlComparators.EQUAL,req.getParameter("id"),pkColumn.DbType);
          int rows=HContext.Current().ExecuteNoResult(ub.toString());
    }

    private void doDelete(CrudTable ct, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int pkIdx=ct.GetPkIdx();
        if(pkIdx<0)
        {
           throw new Exception("Unable to edit row without pk definition on "+ct.getName());
        }
        CrudTableColumn pkColumn= ct.Columns.get(pkIdx);
        
        DeleteBuilder db= new DeleteBuilder();
        db.From(ct.TableName);
        db.AndWhere(pkColumn.Name,SqlComparators.EQUAL,req.getParameter("id"),pkColumn.DbType);
         int rows=HContext.Current().ExecuteNoResult(db.toString());
    }

    private void doAdd(CrudTable ct, HttpServletRequest req, HttpServletResponse resp) {
        
        InsertBuilder ib= new InsertBuilder();
        ib.Into(ct.TableName);
        for(CrudTableColumn ctc: ct.Columns)
        {
            if(ctc.IsEditable)
            {
                ib.Field(ctc.Name,req.getParameter(ctc.Name),ctc.DbType);
            }
        }
        //TODO: FIX IN GLOBAL CONFIGURATION
       HContext.Init( req.getServletContext().getRealPath("WEB-INF/wdb_db.properties"));
        int rows=HContext.Current().ExecuteNoResult(ib.toString());
        
    }

    private void doLookup(CrudTable ct, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        SelectBuilder sb= new SelectBuilder();
        //TODO: avoid double field inclusion
        
       sb.Field(req.getParameter("ext_id"),"ext_id");
       sb.Field(req.getParameter("ext_label"),"ext_label");
       sb.From(req.getParameter("ext_table"));
       sb.OrderBy(req.getParameter("ext_label"));
       
        //TODO: FIX IN GLOBAL CONFIGURATION
       HContext.Init( req.getServletContext().getRealPath("WEB-INF/wdb_db.properties"));
       List l= HContext
            .Current()
            .ExecuteSelectQuery(sb.toString());
        HtmlRenderer r = new HtmlRenderer();
        r.RenderBeginTag("select");
        Object[] o;
         for(int i=0;i<l.size();i++)
        {
            o=(Object[])l.get(i);
            r.AppendAttribute("value", (o[0]==null)?"":o[0].toString());
            r.RenderBeginTag("option");
            r.WriteHtml((o[1]==null)?"":o[1].toString());
            r.RenderEndTag();
        }
        r.RenderEndTag();
       
        resp.getWriter().append(r.GetHtml());
    }

    
}