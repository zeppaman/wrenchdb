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

import WrenchDb.Data.Enums.SqlComparators;
import static WrenchDb.Data.Enums.SqlComparators.BETWEEN;
import static WrenchDb.Data.Enums.SqlComparators.CONTAINS;
import static WrenchDb.Data.Enums.SqlComparators.EQUAL;
import WrenchDb.Data.Enums.SqlDataTypes;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.text.FormattableUtils;

/**
 *
 * @author d.fontani
 */
public class SqlHelper {

    public static String GetFieldName(String fieldName) {
        return ("\"" + fieldName + "\"");
    }

    public static String GetWhereSqlTemplate(SqlComparators comp) {
        switch (comp) {
            case BETWEEN:
                return " %s BETWEEN %s AND %s";
            case CONTAINS:
                return " %s LIKE %s'";
            case EQUAL:
                return " %s = %s";
            default:
                return null;
        }

    }
public static String GetWhereSql(String FieldName, SqlComparators comp, Object Value) {
  SqlDataTypes dt= GetSqlDataTypeByValue(Value);
  return GetWhereSql(FieldName,comp,Value,dt);
}
    public static String GetWhereSql(String FieldName, SqlComparators comp, Object Value,  SqlDataTypes dt) {
        String literalValue = GetSqlValue(Value,dt);
        return String.format(GetWhereSqlTemplate(comp),
                GetFieldName(FieldName), literalValue);
    }
 public static String GetSqlValue(Object Value) {
     if (Value == null) {
            return "NULL";
        }
    SqlDataTypes dt = GetSqlDataTypeByValue(Value);
    return GetSqlValue(Value,dt);
 }
 
    public static String GetSqlValue(Object Value,SqlDataTypes dt) {
        if (Value == null) {
            return "NULL";
        }

        String literalValue = "NULL";
        

        switch (dt) {
            case Boolean:                
                    literalValue = Value.toString();                
                break;
            case Date:
                if(Value instanceof String)
                {
                    literalValue = "CONVERT('" +Value + "')";
                }
                else
                {
                    literalValue = "CONVERT('" + ((Date) Value).toString() + "')";
                }
                break;
            case Integer:
                literalValue = Value.toString();
                break;
            case Number:
                literalValue = Value.toString();
                break;
            case Text:
                literalValue = "'" + Value.toString() + "'";
                break;
            case Time: literalValue =  "CONVERT('" + ((Date) Value).toString() + "')";
                break;
        }
        return literalValue;
    }

    public static SqlDataTypes GetSqlDataTypeByValue(Object Value) {


        switch (Value.getClass().getName()) {
            case "java.lang.String":
                return SqlDataTypes.Text;
            case "java.lang.Double":
                return SqlDataTypes.Number;
            case "java.lang.Float":
                return SqlDataTypes.Number;
            case "java.lang.Integer":
                return SqlDataTypes.Integer;
            case "java.lang.Long":
                return SqlDataTypes.Integer;
            case "java.lang.Boolean":
                return SqlDataTypes.Boolean;
            case "java.util.Date":
                return SqlDataTypes.DateTime;
            default:
                Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, "GetSqlDataTypeByValue: type unhandled:" + Value.getClass().getName());
                return SqlDataTypes.Text;
        }

    }
}
