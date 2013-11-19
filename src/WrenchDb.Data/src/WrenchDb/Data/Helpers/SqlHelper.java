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
import java.util.Date;
import org.apache.commons.lang3.text.FormattableUtils;


/**
 *
 * @author d.fontani
 */
public class SqlHelper {

    public static String GetFieldName(String fieldName )
    {
           return ("\""+fieldName+"\"");
    }
    
    public static String GetWhereSqlTemplate(SqlComparators comp )
    {
        switch(comp)
        {
            case BETWEEN: return " %s BETWEEN %s AND %s" ;
            case CONTAINS: return " %s LIKE %s'"  ;
            case EQUAL: return " %s = %s"  ;
            default:
                return null;
        }
        
    }
    
    public static String GetWhereSql(String FieldName, SqlComparators comp, Object Value) {

        String literalValue="NULL";
        
        if(Value!=null)
        {
             switch(Value.getClass().getName())
            {
                 case "java.lang.String":literalValue= "'"+Value.toString()+"'" ;
                 break;
                 case "java.lang.Double":               
                     literalValue=Value.toString() ;
                 break;
                 case "java.lang.Float": literalValue=Value.toString() ;
                 break;
                 case "java.lang.Integer": literalValue=Value.toString() ;
                 break;
                 case "java.lang.Long": literalValue=Value.toString() ;
                  break;
                 case "java.lang.Boolean": literalValue=Value.toString() ;
                 break;
                 case "java.util.Date": 
                     literalValue="CONVERT('"+((Date)Value).toString()+"')";
                     break;

                default:
                    literalValue=Value.toString();
           
                
        }
        
       
    }
         return String.format(GetWhereSqlTemplate(comp), 
                GetFieldName(FieldName),literalValue);
    }
    
}
