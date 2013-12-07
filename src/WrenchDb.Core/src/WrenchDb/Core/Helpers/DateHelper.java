/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Daniele Fontani
 */
public class DateHelper {
    private static DateFormat IsoDateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat ItalianDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    
    public static Date getNow()
    {
        return new Date();      
    }
    
    public static String toString(Date d)
    {
        return IsoDateTimeFormatter.format(d);
    }
    
    public static String format(String format,Date d)
    {
            DateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(d);
    }
    
    public static String parse(String format,Date d)
    {
            DateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(d);
    }
    
    public static String parse(Date d)
    {
        return IsoDateTimeFormatter.format(d);
    }
}
