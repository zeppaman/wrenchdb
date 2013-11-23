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

import WrenchDb.Data.Enums.JqGridFieldType;
import WrenchDb.Data.Enums.SqlDataTypes;
import WrenchDb.Data.Enums.SqlOrderDirection;
import WrenchDb.Data.Enums.jqGridEditType;
import WrenchDb.Data.Model.CrudTable;
import WrenchDb.Data.Model.CrudTableColumn;
import WrenchDb.Data.Model.CrudTableColumnFormOptions;
import WrenchDb.Data.Model.CrudTableEditOption;
import WrenchDb.Data.Model.CrudTableEditRules;
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
        ct.EditUrl="/WrenchDb.CentralAdmin/handlers/datahandler?mode=edit&ctable="+InstanceId;
        ct.error="function (xHR, status, err) { alert(\"ERROR: \" + status + \", \" + err);}";
        
    }
    
    
     public CrudTableBuilder AddColumnPk(String HeaderText,String Name,SqlDataTypes dt)
    {
      return AddColumn(HeaderText,true,false,Name,null, dt);
    }
     
    public CrudTableBuilder AddColumnHidden(String HeaderText,String Name,SqlDataTypes dt)
    {
      return AddColumn(HeaderText,true,false,Name,null,dt);
    }
      
    public CrudTableBuilder AddColumn(String HeaderText,String Name,SqlDataTypes dt)
    {
      return AddColumn(HeaderText,false,false,Name,null,dt);
    }
    
     public CrudTableBuilder EditColumn(String ColumnName,boolean Editable,
             JqGridFieldType fieldType,
             boolean EditIfhidden,
             boolean required)
     {
        return null;
     }
     /**
     * 
     * @param ColumnName
     * @param Editable
     * @param EditType
     * @param EditIfhidde
     * @param required
     * @return 
     */
     public CrudTableBuilder EditColumn(String ColumnName,boolean Editable,jqGridEditType EditType,boolean EditIfhidden,boolean required)
     {
         return  EditColumn(
                 ColumnName,
                 Editable,
                 EditType,
                null,// * @param NullIfEmpty
                null,//* @param buildSelect
                null,//* @param custom_element
                null,//* @param custom_value
                null,//* @param dataInit
                null,//* @param dataUrl
                null,//* @param value
                 EditIfhidden,
                 required,
                false,//  * @param number
                false,//* @param integer
                null,//* @param minValue
                null,//* @param maxValue
                false,//* @param email
                false,//* @param url
                false,//* @param date
                false,//* @param time
                false,//* @param custom);
                null,//
                null,//     * @param elmprefix
                null,//  * @param elmsuffix
                null,// * @param label
                null,//* @param rowpos
                null// * @param colpos
                );
     }
    
    /**
     *  FULL METHOD OVERLOAD 
     * See shortcut to simpler usage   
     * @param ColumnName
     * @param Editable
     * @param EditType
     * @param NullIfEmpty
     * @param buildSelect
     * @param custom_element
     * @param custom_value
     * @param dataInit
     * @param dataUrl
     * @param value
     * @param edithidden
     * @param required
     * @param number
     * @param integer
     * @param minValue
     * @param maxValue
     * @param email
     * @param url
     * @param date
     * @param time
     * @param custom
     * @param custom_func
     * @param elmprefix
     * @param elmsuffix
     * @param label
     * @param rowpos
     * @param colpos
     * @return 
     */
    public CrudTableBuilder EditColumn(String ColumnName,boolean Editable,jqGridEditType EditType,
            //EditOptions
            Boolean NullIfEmpty, String buildSelect,String custom_element,String custom_value,
            String  dataInit,String dataUrl, String  value,
            //EditRules
            boolean edithidden, boolean required, boolean number,boolean integer, Integer minValue, 
            Integer maxValue, boolean email, boolean url, boolean date, boolean time, boolean custom, String custom_func,
            //Form Options
            String elmprefix, String elmsuffix	, String label, Integer rowpos, Integer colpos
            )
    {
        CrudTableColumn ctc= this.ct.Columns.getByName(ColumnName);
        if(ctc!=null)
        {
            ctc.IsEditable=Editable;
            ctc.EditType= EditType;
            //Set edit options
            ctc.EditOptions= new CrudTableEditOption();
            
            if(NullIfEmpty!=null)
                ctc.EditOptions.NullIfEmpty=NullIfEmpty;
            
            ctc.EditOptions.buildSelect=buildSelect;
            ctc.EditOptions.custom_element=custom_element;
            ctc.EditOptions.custom_value=custom_value;
            ctc.EditOptions.dataInit=dataInit;
            ctc.EditOptions.dataUrl=dataUrl;
            ctc.EditOptions.value=value;
            
            
            //Set Edit rules
            ctc.EditRules= new CrudTableEditRules();
            ctc.EditRules.edithidden=edithidden;
            ctc.EditRules.required=required;
            ctc.EditRules.custom=custom;
            ctc.EditRules.date=date;
            ctc.EditRules.email=email;
            ctc.EditRules.maxValue=maxValue;
            ctc.EditRules.minValue=minValue;
            ctc.EditRules.number=number;
            ctc.EditRules.required=required;
            ctc.EditRules.time=time;
            ctc.EditRules.url=url;
            ctc.EditRules.custom_func=custom_func;
            
            
            //Set Form Options
            ctc.FormOptions= new CrudTableColumnFormOptions();
            ctc.FormOptions.colpos=colpos;
            ctc.FormOptions.elmprefix=elmprefix;
            ctc.FormOptions.elmsuffix=elmsuffix;
            ctc.FormOptions.label=(label==null)? ctc.Header:label;
            ctc.FormOptions.rowpos=rowpos;
            
        }
        return this;
    }
     public CrudTableBuilder AddColumn(String HeaderText,boolean Hidden,boolean IsPk,String Name,Integer  width,SqlDataTypes DbType)
    {
        CrudTableColumn ctc= new CrudTableColumn();
        ctc.Header=HeaderText;
        ctc.Hidden=Hidden;
        ctc.IsPrimaryKey=IsPk;
        ctc.Name=Name;
        ctc.Width=100;
        ctc.DbType=DbType;
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

    public CrudTableBuilder AllowEdit() {
       ct.AllowEdit=true;
       return this;
    }
      public CrudTableBuilder AllowView() {
       ct.AllowView=true;
       return this;
    }
        public CrudTableBuilder AllowAdd() {
       ct.AllowAdd=true;
       return this;
    }
        
    public CrudTableBuilder AllowDelete() {
       ct.AllowDelete=true;
       return this;
    }
}

