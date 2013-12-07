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
package WrenchDb.MVC.BaseClasses.Model;

import WrenchDb.MVC.Annotations.*;
import WrenchDb.MVC.Enums.RequestMethod;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author d.fontani
 */

@Model(ModelName = "Request Model")
public class RequestModel extends ModelBase
{
    
    public RequestMethod Method= RequestMethod.GET;
    public Collection<Part> Parts=null;
    public HttpServletRequest OriginalRequest;
    public static RequestModel CreateRequestModel(HashMap map,HttpServletRequest context )
    {
        RequestModel rm= new RequestModel();
        rm.Properties=map;
        rm.OriginalRequest=context;
        
        Enumeration<String> enu= context.getParameterNames();
        String key="";
        while(enu.hasMoreElements())
        {
            key=enu.nextElement();
            rm.Properties.put(key,context.getParameter(key));
        }
        try
        {
                rm.Parts=context.getParts();
        }
        catch(Exception er)
        {
            //TODO: LOG Here
            //TODO Add Error processing system 
        }
        if("post".equals(context.getMethod().toLowerCase()))
            rm.Method= RequestMethod.POST;
        return rm;
        
    }
}
