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
public class UpdateBuilder {
    StringBuilder _sql;
    StringBuilder _fields= new StringBuilder();
    StringBuilder _from= new StringBuilder();
    StringBuilder _where= new StringBuilder();
    
   
     public UpdateBuilder OrWhere(String FieldName,SqlComparators comp, Object value)
    {
        return OrWhere(FieldName, comp, value,SqlHelper.GetSqlDataTypeByValue(value));
    }
     
    public UpdateBuilder OrWhere(String FieldName,SqlComparators comp, Object value,SqlDataTypes dt)
    {
         if(_where.length()>0)
        {
            _where.append( " OR ");
        }
        
        _where.append(SqlHelper.GetWhereSql(FieldName,comp,value,dt));
        return this;
    }
    public UpdateBuilder AndWhere(String FieldName,SqlComparators comp, Object value)
    {
        return AndWhere(FieldName, comp, value,SqlHelper.GetSqlDataTypeByValue(value));
    }
    public UpdateBuilder AndWhere(String FieldName,SqlComparators comp, Object value,SqlDataTypes dt)
    {
        if(_where.length()>0)
        {
            _where.append( " AND ");
        }
        
        _where.append(SqlHelper.GetWhereSql(FieldName,comp,value,dt));
        return this;
    }
    public UpdateBuilder Update(String Table)
    {
         _from.append(Table);
         return this;
    }
    
    public UpdateBuilder Field(String FieldName,Object FieldValue)
    {
      SqlDataTypes dt= SqlHelper.GetSqlDataTypeByValue(FieldValue);
      return Field(FieldName,FieldValue,dt);
    }
    
 
    public UpdateBuilder Field(String FieldName,Object FieldValue,SqlDataTypes dataType)
    {
       
        if(_fields.length()>0)
        {
            _fields.append(",");
        }
        _fields.append(FieldName);
        _fields.append(" = ");
        _fields.append(SqlHelper.GetSqlValue(FieldValue,dataType));
        return this;
    }

    @Override
    public String toString() {       
        _sql= new StringBuilder();
        _sql.append("UPDATE  ");
        _sql.append(_from);
        _sql.append(" SET ");
        _sql.append(_fields);
       
     
        
        if(_where.length()>0)
        {
            _sql.append(" WHERE ");
            _sql.append(_where);
        }
        return _sql.toString();
}
     public String lastSelect() {
         return _sql.toString();
     }  
}
