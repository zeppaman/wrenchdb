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
public class SelectBuilder {
    StringBuilder _sql;
    StringBuilder _select= new StringBuilder();
    StringBuilder _from= new StringBuilder();
    StringBuilder _where= new StringBuilder();
    StringBuilder _groupby= new StringBuilder();
    StringBuilder _orderBy=new StringBuilder();
    
    public SelectBuilder GroupBy(String FieldName)
    { 
        if(_groupby.length()>0)
        {
            _groupby.append(",");
        }
        _groupby.append(FieldName);
        return this;
    }
    
    public SelectBuilder OrderBy(String FieldName)
    {
    return OrderBy(FieldName,SqlOrderDirection.ASC);
    }
    
    public SelectBuilder OrderBy(String FieldName,SqlOrderDirection dir)
    {
        if(_orderBy
                .length()>0)
        {
            _orderBy.append(" ");
        }
       _orderBy.append(FieldName);
       _orderBy.append(" ");
       _orderBy.append(dir.toString());
        return this;
    }
     public SelectBuilder OrWhere(String FieldName,SqlComparators comp, Object value)
    {
        return OrWhere(FieldName, comp, value,SqlHelper.GetSqlDataTypeByValue(value));
    }
     
    public SelectBuilder OrWhere(String FieldName,SqlComparators comp, Object value,SqlDataTypes dt)
    {
         if(_where.length()>0)
        {
            _where.append( " OR ");
        }
        
        _where.append(SqlHelper.GetWhereSql(FieldName,comp,value,dt));
        return this;
    }
    public SelectBuilder AndWhere(String FieldName,SqlComparators comp, Object value)
    {
        return AndWhere(FieldName, comp, value,SqlHelper.GetSqlDataTypeByValue(value));
    }
    public SelectBuilder AndWhere(String FieldName,SqlComparators comp, Object value,SqlDataTypes dt)
    {
        if(_where.length()>0)
        {
            _where.append( " AND ");
        }
        
        _where.append(SqlHelper.GetWhereSql(FieldName,comp,value,dt));
        return this;
    }
    public SelectBuilder From(String From)
    {
         _from.append(From);
         return this;
    }
    
    public SelectBuilder Field(String FieldName)
    {
        return Field(FieldName,null);
    }
    public SelectBuilder Field(String FieldName,String FieldAlias)
    {
       
        if(_select.length()>0)
        {
            _select.append(",");
        }
        _select.append(FieldName);
        
        if(! WdbStringHelper.isBlank(FieldAlias))
        { 
          _select.append(" AS ");
          _select.append(FieldAlias);
        
        }
        return this;
    }

    @Override
    public String toString() {       
        _sql= new StringBuilder();
        _sql.append("SELECT ");
        
        if(_select.length()>0)
        {    
            _sql.append(_select);
        
        }
        else
        {
            _sql.append(" * ");
        }
        _sql.append(" FROM ");
        _sql.append(_from);
        
        if(_where.length()>0)
        {
             _sql.append(" WHERE ");
            _sql.append(_where);
        }
         if(_groupby.length()>0)
         {
            _sql.append(" GROUP BY ");
            _sql.append(_groupby);
         }
         if(_orderBy.length()>0)
         {
             _sql.append(" ORDER BY ");
            _sql.append(_orderBy);
         }
        return _sql.toString();
}
     public String lastSelect() {
         return _sql.toString();
     }  
  public SelectBuilder Sum(String FieldName) {
      return Sum(FieldName,null);
  }
    public SelectBuilder Sum(String FieldName, String FieldAlias) {
      return this.Field(" SUM("+FieldName+")",FieldAlias);
      
    }
      public SelectBuilder Count(String FieldName) {
      return this.Field(" COUNT("+FieldName+")");
      
    }
      
      public SelectBuilder Count(String FieldName, String FieldAlias) {
      return this.Field(" COUNT("+FieldName+")",FieldAlias);
      
    }
}
