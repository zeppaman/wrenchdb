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

import WrenchDb.Core.Helpers.JSONHelper;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.Data.Configuration.CrudTableSet;
import WrenchDb.Data.Helpers.SelectBuilder;
import WrenchDb.Data.Model.CrudTable;
import WrenchDb.Data.Model.CrudTableColumn;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
           
            SelectBuilder sb= new SelectBuilder();
            //TODO: avoid double field inclusion
            for( CrudTableColumn cc:ct.Columns)
            {
                    sb.Field(cc.Name);
            }
            
            sb.From(ct.TableName);
            sb.OrderBy(ct.DefaultSortColumnName,ct.DefaultSortColumnOrder);
            
           String sql= sb.toString();
           
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

    
}