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
package WrenchDb.Data.Helpers;

import WrenchDb.Data.Enums.SqlOrderDirection;
import WrenchDb.Data.Model.CrudTable;
import WrenchDb.Data.Model.CrudTableColumn;
import WrenchDb.Data.Model.Enums.CrudTableDataType;
import WrenchDb.Data.Model.Enums.CrudTableSortOrder;

/**
 *
 * @author Daniele Fontani
 */
public class CrudTableBuilder {
    
    CrudTable ct= null;
    public CrudTableBuilder()
    {
    }
    
    public CrudTableBuilder(String InstanceId,String DefaultSortColumnName,SqlOrderDirection order,String Title,String TableName)
    {
        ct= new CrudTable();
        ct.DataType= CrudTableDataType.json;
       
        ct.DefaultSortColumnName=DefaultSortColumnName;
        ct.DefaultSortColumnOrder= order;
        ct.InitialRowCount=20;
        ct.InstanceId=InstanceId;
        ct.Title=Title;
        ct.TableName=TableName;
         ct.DataUrl="/WrenchDb.CentralAdmin/handlers/datahandler?ctable="+InstanceId;
        
    }
    
    
     public CrudTableBuilder AddColumnPk(String HeaderText,String Name)
    {
      return AddColumn(HeaderText,true,false,Name,null);
    }
     
    public CrudTableBuilder AddColumnHidden(String HeaderText,String Name)
    {
      return AddColumn(HeaderText,true,false,Name,null);
    }
      
    public CrudTableBuilder AddColumn(String HeaderText,String Name)
    {
      return AddColumn(HeaderText,false,false,Name,null);
    }
     public CrudTableBuilder AddColumn(String HeaderText,boolean Hidden,boolean IsPk,String Name,Integer  width)
    {
        CrudTableColumn ctc= new CrudTableColumn();
        ctc.Header=HeaderText;
        ctc.Hidden=Hidden;
        ctc.IsPrimaryKey=IsPk;
        ctc.Name=Name;
        ctc.Width=100;
        if(width!=null)
        ctc.Width=width;
        
     
        return AddColumn(ctc);
        
        
    }
    
    public CrudTableBuilder AddColumn(CrudTableColumn ctc)
    {
        ct.Columns.add(ctc);
        return this;
    }

    public CrudTable getCrudTable() {
       return ct;
    }
}

