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
        ct.error="function (xHR, status, err) { \n\nalert(\"ERROR: \" + status + \", \" + err);}";
        
    }
    
    
     public CrudTableBuilder AddColumnPk(String HeaderText,String Name,SqlDataTypes dt,JqGridFieldType fieldType)
    {
      return AddColumn(HeaderText,true,false,Name,null, dt,fieldType,null);
    }
     
    public CrudTableBuilder AddColumnHidden(String HeaderText,String Name,SqlDataTypes dt,JqGridFieldType fieldType)
    {
      return AddColumn(HeaderText,true,false,Name,null,dt,fieldType,null);
    }
       public CrudTableBuilder AddHiddenColumn(String HeaderText,String Name,SqlDataTypes dt,JqGridFieldType fieldType)
    {
     return AddColumn(HeaderText,true,false,Name,null,dt,fieldType,null);
    }
    public CrudTableBuilder AddColumn(String HeaderText,String Name,SqlDataTypes dt,JqGridFieldType fieldType)
    {
      return AddColumn(HeaderText,false,false,Name,null,dt,fieldType,null);
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
     public CrudTableBuilder EditColumn(String ColumnName,boolean EditIfhidden,boolean required)
     {
         return  EditColumn(
                 ColumnName,
                 true,
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
    public CrudTableBuilder EditColumn(String ColumnName,boolean Editable,
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
        JqGridFieldType FieldType=ctc.FieldType;
        if(ctc!=null)
        {
            ctc.IsEditable=Editable;
            ctc.FieldType= FieldType;
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
     public CrudTableBuilder AddColumn(String HeaderText,boolean Hidden,
             boolean IsPk,String Name,Integer  width,SqlDataTypes DbType,
             JqGridFieldType fieldType,String ColumnExpression)
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
        ctc.FieldType=fieldType;
        ctc.ColumnName=ColumnExpression;
     
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
    
    
     /**
     * 
     * @param ColumnName
     * @param Editable
     * @param EditType
     * @param EditIfhidde
     * @param required
     * @return 
     */
//     public CrudTableBuilder EditColumn(String ColumnName,String Label,boolean EditIfhidden,boolean required)
//     {
//       CrudTableColumn  ctc=ct.Columns.getByName(ColumnName);
//        switch(ctc.FieldType)
//        {
//            case AutoGeneratedPK:
//                break;
//            case CheckBox:
//                EditAsCheckBox(ColumnName, Label, required);
//               break;
//            case DatePicker:
//                break;
//            case Expression:
//                break;
//            case File:
//                break;
//            case Hidden:
//                break;
//            case HtmlText:
//                break;
//            case Image:
//                break;
//            case Integer:
//                EditAsInteger(ColumnName, Label, required, null, null);
//                break;
//            case Lookup:
//              
//                break;
//            case MultiLineText:
//                break;
//            case Number:
//                break;
//            case Password:
//                break;
//            case RadioChoice:
//                break;
//            case SelectChoice:
//                break;
//            case  SingleLineText:
//                break;
//            case Email:
//                break;
//            case Url
//                break;
//                   
//        }
//     }
    public CrudTableBuilder EditAsLookup(String ColumnName,String Label,boolean required )
    {
        CrudTableColumn ctc=this.ct.Columns.getByName(ColumnName);
        String DataUrl=String.format(
                "/WrenchDb.CentralAdmin/handlers/datahandler?ext_table=%s&ext_id=%s&ext_label=%s&ctable=%s&renderas=select&addnull=true&mode=lookup",
                ctc.LookupTable,ctc.LookupId,ctc.LookupDesc,this.ct.TableName);
        
        return  EditColumn(
                 ColumnName,
                 true,                
                true,// * @param NullIfEmpty
                null,//* @param buildSelect
                null,//* @param custom_element
                null,//* @param custom_value
                null,//* @param dataInit
                DataUrl,//* @param dataUrl
                null,//* @param value
                true,
                required,
                false,//  * @param number
                true,//* @param integer
                null,//* @param minValue
                null,//* @param maxValue
                false,//* @param email
                false,//* @param url
                false,//* @param date
                false,//* @param time
                false,//* @param custom);
                null,//
                null,//     * @param elmprefix
                GetPrefix(required),//  * @param elmsuffix
                Label,// * @param label
                null,//* @param rowpos
                null// * @param colpos
                );
    }
    
    
    
     public CrudTableBuilder EditAsCheckBox(String ColumnName,String Label,boolean required)
    {
        return  EditColumn(
                 ColumnName,
                 true,
                true,// * @param NullIfEmpty
                null,//* @param buildSelect
                null,//* @param custom_element
                null,//* @param custom_value
                null,//* @param dataInit
                null,//* @param dataUrl
                null,//* @param value
                false,
                required,
                false,//  * @param number
                true,//* @param integer
                null,//* @param minValue
                null,//* @param maxValue
                false,//* @param email
                false,//* @param url
                false,//* @param date
                false,//* @param time
                false,//* @param custom);
                null,//
                null,//     * @param elmprefix
                GetPrefix(required),//  * @param elmsuffix
                Label,// * @param label
                null,//* @param rowpos
                null// * @param colpos
                );
    }
    
    
    public CrudTableBuilder EditAsInteger(String ColumnName,String Label,boolean required)
    {
      return EditAsInteger( ColumnName, Label, required,null,null);
    }
    
    public CrudTableBuilder EditAsInteger(String ColumnName,String Label,boolean required,Integer minVal,Integer maxVal)
    {
        return  EditColumn(
                 ColumnName,
                 true,
                
                true,// * @param NullIfEmpty
                null,//* @param buildSelect
                null,//* @param custom_element
                null,//* @param custom_value
                null,//* @param dataInit
                null,//* @param dataUrl
                null,//* @param value
                false,
                required,
                false,//  * @param number
                true,//* @param integer
                minVal,//* @param minValue
                maxVal,//* @param maxValue
                false,//* @param email
                false,//* @param url
                false,//* @param date
                false,//* @param time
                false,//* @param custom);
                null,//
                null,//     * @param elmprefix
                GetPrefix(required),//  * @param elmsuffix
                Label,// * @param label
                null,//* @param rowpos
                null// * @param colpos
                );
    }
    
    public CrudTableBuilder EditAsNumber(String ColumnName,String Label,boolean required)
    {
            return EditAsNumber( ColumnName, Label, required,null,null);
    }
    public CrudTableBuilder EditAsNumber(String ColumnName,String Label,boolean required,Integer minVal,Integer maxVal)
    {
        return  EditColumn(
                 ColumnName,
                 true,
                true,// * @param NullIfEmpty
                null,//* @param buildSelect
                null,//* @param custom_element
                null,//* @param custom_value
                null,//* @param dataInit
                null,//* @param dataUrl
                null,//* @param value
                false,
                required,
                true,//  * @param number
                false,//* @param integer
                minVal,//* @param minValue
                maxVal,//* @param maxValue
                false,//* @param email
                false,//* @param url
                false,//* @param date
                false,//* @param time
                false,//* @param custom);
                null,//
                null,//     * @param elmprefix
                GetPrefix(required),//  * @param elmsuffix
                Label,// * @param label
                null,//* @param rowpos
                null// * @param colpos
                );
    }
    
    
    public CrudTableBuilder EditAsEmail(String ColumnName,String Label,boolean required)
    {
        return  EditColumn(
                 ColumnName,
                 true,
                true,// * @param NullIfEmpty
                null,//* @param buildSelect
                null,//* @param custom_element
                null,//* @param custom_value
                null,//* @param dataInit
                null,//* @param dataUrl
                null,//* @param value
                false,
                required,
                false,//  * @param number
                false,//* @param integer
                null,//* @param minValue
                null,//* @param maxValue
                true,//* @param email
                false,//* @param url
                false,//* @param date
                false,//* @param time
                false,//* @param custom);
                null,//
                null,//     * @param elmprefix
                GetPrefix(required),//  * @param elmsuffix
                Label,// * @param label
                null,//* @param rowpos
                null// * @param colpos
                );
    }
    
    public CrudTableBuilder EditAsUrl(String ColumnName,String Label,boolean required)
    {
        return  EditColumn(
                 ColumnName,
                 true,
                
                true,// * @param NullIfEmpty
                null,//* @param buildSelect
                null,//* @param custom_element
                null,//* @param custom_value
                null,//* @param dataInit
                null,//* @param dataUrl
                null,//* @param value
                false,
                required,
                false,//  * @param number
                false,//* @param integer
                null,//* @param minValue
                null,//* @param maxValue
                false,//* @param email
                true,//* @param url
                false,//* @param date
                false,//* @param time
                false,//* @param custom);
                null,//
                null,//     * @param elmprefix
                GetPrefix(required),//  * @param elmsuffix
                Label,// * @param label
                null,//* @param rowpos
                null// * @param colpos
                );
    }
    
    public static  String GetPrefix(boolean required)
    {
        if(required) return "(*)";
            return null;
    }

    public CrudTableBuilder AddPK(String entity_id, SqlDataTypes sqlDataTypes) {
       return AddColumn(entity_id, true, true, entity_id, 100, sqlDataTypes, JqGridFieldType.AutoGeneratedPK,null);
    }

    
    public CrudTableBuilder LookupColumn(String Header, String ColumnName, 
            SqlDataTypes sqlDataTypes, boolean editable, boolean required,
            String LookupTable, String LookupId, String LookupDesc) {
        
        //TODO:
        //Mettere 2 colonne:
        // colonna fk reale (hidden ed editable)
        // colonna lookup (visibile non editabile)
          
       //Add one column of type Lookup,that point to the fk column on base view table (HIDDEN)
       this.AddColumn(Header,true,false,ColumnName, 10,sqlDataTypes,JqGridFieldType.Lookup,ct.TableName+"."+ColumnName);
       CrudTableColumn ctc= this.ct.Columns.getByName(ColumnName);
        ctc.LookupId=LookupId;
        ctc.LookupDesc=LookupDesc;
        ctc.LookupTable=LookupTable;
            
       //Add a second column of type Lookup,that point to the fk column on base view table (VISIBLE)
       ctc= new CrudTableColumn();
       ctc.ColumnName="lt_"+ColumnName+"."+LookupDesc + " AS lt_"+ColumnName+"_"+LookupDesc;
       ctc.Name="lt_"+ColumnName+"_"+LookupDesc;
       ctc.Header=Header;
       ctc.Hidden=false;
       ctc.DbType=sqlDataTypes.Text;
       ctc.FieldType= JqGridFieldType.Expression;
       
       this.AddColumn(ctc); 
          
          
            
       return EditAsLookup(ColumnName, Header, required);
    }
}

