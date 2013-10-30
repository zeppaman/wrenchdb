/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses;

import WrenchDb.MVC.Annotations.*;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author d.fontani
 */

@Model(ModelName = "Request Model")
public class RequestModel extends ModelBase
{
    public static RequestModel CreateRequestModel(HashMap map,HttpServletRequest context )
    {
        RequestModel rm= new RequestModel();
        rm.Properties=map;
        Enumeration<String> enu= context.getParameterNames();
        String key="";
        while(enu.hasMoreElements())
        {
            key=enu.nextElement();
            rm.Properties.put(key,context.getParameter(key));
        }
        return rm;
        
    }
}
