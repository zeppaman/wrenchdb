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

import WrenchDb.Core.Helpers.WdbStringHelper;
import WrenchDb.Data.Enums.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d.fontani
 */
public class InsertBuilder {
    StringBuilder _sql;
    StringBuilder _fieldNames= new StringBuilder();
    StringBuilder _fieldValues= new StringBuilder();
    StringBuilder _into= new StringBuilder();
    
   
    
  
    public InsertBuilder Into(String From)
    {
         _into.append(From);
         return this;
    }
    
    public InsertBuilder Field(String FieldName,Object FieldValue)
    {
      SqlDataTypes dt= SqlHelper.GetSqlDataTypeByValue(FieldValue);
      return Field(FieldName,FieldValue,dt);
    }
    public InsertBuilder Field(String FieldName,Object FieldValue,SqlDataTypes dataType)
    {       
        if(_fieldNames.length()>0)
        {
            _fieldNames.append(",");
            _fieldValues.append(",");
        }
        
        _fieldNames.append(FieldName);
        _fieldValues.append(SqlHelper.GetSqlValue(FieldValue,dataType));
        return this;
    }

    @Override
    public String toString() {       
        _sql= new StringBuilder();
        _sql.append("INSERT INTO  ");
        _sql.append(_into);
        _sql.append(" ( ");
        _sql.append(_fieldNames);
        _sql.append(") VALUES  (");
        _sql.append(_fieldValues);
        _sql.append(") ");
        return _sql.toString();
}
     public String lastSelect() {
         return _sql.toString();
     }  
}
