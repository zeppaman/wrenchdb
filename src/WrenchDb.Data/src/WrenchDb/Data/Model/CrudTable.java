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
package WrenchDb.Data.Model;

import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.Core.Helpers.JSONRenderer;
import WrenchDb.Core.Interfaces.HtmlRenderizzable;
import WrenchDb.Core.Interfaces.JSONRenderizzable;
import WrenchDb.Core.Interfaces.NamedItem;
import WrenchDb.Data.Enums.SqlOrderDirection;
import WrenchDb.Data.Model.Enums.*;
import WrenchDb.Data.Model.Lists.CrudColumnList;
import WrenchDb.MVC.BaseClasses.Model.ModelBase;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d.fontani
 */
public class CrudTable 
implements JSONRenderizzable, NamedItem, HtmlRenderizzable {
    
     
    
       public CrudColumnList Columns= new CrudColumnList();
       public String   DataUrl;
       public CrudTableDataType DataType;
       public Integer InitialRowCount =new Integer(20);
       public List<Integer> RowCountList =new ArrayList<Integer>();
       public String InstanceId="";
       public String DefaultSortColumnName=null;
       public SqlOrderDirection DefaultSortColumnOrder= SqlOrderDirection.ASC;
       public String Title="";
       public String TableName;
      
       /*
     
      pager: jQuery('#gridpager'),
   
    */
       
       
        public CrudTable()
       {
           RowCountList.add(10);
           RowCountList.add(20);
           RowCountList.add(30);
           RowCountList.add(40);
           RowCountList.add(50);
           RowCountList.add(100);
           RowCountList.add(200);
           RowCountList.add(1000);
           
       }
        
        public String getPagerId()
        {
            return this.InstanceId+"_gridpager";
        }
         public String getTableId()
        {
            return this.InstanceId+"_gridtable";
        }
     @Override
    public String RenderAsJSON() {
       JSONRenderer jre= new JSONRenderer();
       RenderAsJSON(jre);
       return jre.GetJSON();
    }

    @Override
    public void RenderAsJSON(JSONRenderer jre) {
      jre.AppendProperty("url", DataUrl);
      jre.AppendProperty("datatype", this.DataType);
      jre.AppendProperty("mtype","GET");
      jre.AppendProperty("rowNum", InitialRowCount);
      jre.AppendProperty("viewrecords", "true");
      jre.AppendPropertyIfValued("sortname", this.DefaultSortColumnName);
      jre.AppendProperty("caption", this.Title);
      jre.AppendProperty("autowidth",true);
      jre.AppendProperty("autowidth",true);
      jre.AppendProperty("shrinkToFit",true);
      jre.WriteRaw(",pager:jQuery('#"+this.getPagerId()+"')");
      if(DefaultSortColumnName!=null)
      {
        jre.AppendProperty("sortorder", this.DefaultSortColumnOrder);
      }
   
      jre.StartArrayProperty("rowList");
      for(Integer pageItemsCount : this.RowCountList)
      {
          jre.AppendProperty(null,pageItemsCount);
      }
      jre.EndArrayProperty();
      //COL MODEL
      CrudTableColumn[] _columns= new CrudTableColumn[this.Columns.size()];
       _columns=this.Columns.toArray(_columns);
       
      jre.StartArrayProperty("colNames");
      
      
        for(CrudTableColumn ct :_columns )
        {
            jre.AppendProperty(null,ct.Header);
        }
      jre.EndArrayProperty(); //Ends ColNames
      
      
       jre.StartArrayProperty("colModel");
        for(CrudTableColumn ct : _columns )
        {
             
            jre.StartObjectProperty(null);
                ct.RenderAsJSON(jre);
            jre.EndObjectProperty();
        }
       jre.EndArrayProperty(); //Ends colModel
      
    }

    
    @Override
    public String getName() {
        return InstanceId;
    }
    public void setName(String value) {
         InstanceId=value;
    }


    public String GetHtml() {
        HtmlRenderer sb= new HtmlRenderer();
        RenderAsHtml(sb);
        return sb.GetHtml();  
   }
    @Override
    public String RenderAsHtml() {
        HtmlRenderer sb= new HtmlRenderer();
        RenderAsHtml(sb);
        return sb.GetHtml();    }

    @Override
    public void RenderAsHtml(HtmlRenderer sb) {
       
        //HTML MARKUP
        sb.AppendAttribute("id", "jqgrid");
        sb.RenderBeginTag("div");
                 //TABLE
                 sb.AppendAttribute("id", getTableId());
                 sb.RenderBeginTag("table");
                 sb.RenderEndTag();
                 //PAGER
                 sb.AppendAttribute("id", getPagerId());
                 sb.RenderBeginTag("div");
                 sb.RenderEndTag();
      sb.RenderEndTag();
      
      
      //JS
      sb.RenderBeginTag("script");
      sb.WriteHtml("jQuery(document).ready(function(){ ");
      sb.WriteHtml("jQuery('#"+this.getTableId()+"').jqGrid("+
              this.RenderAsJSON()+").navGrid('#"+this.getPagerId()+"');");
       sb.WriteHtml("});");
      sb.RenderEndTag();
      
    }

    public  int GetPkIdx() {
       for(int i=0; i<this.Columns.size();i++)
        {
            if(this.Columns.get(i).IsPrimaryKey) return i;
        }
       return -1;
    }
}
