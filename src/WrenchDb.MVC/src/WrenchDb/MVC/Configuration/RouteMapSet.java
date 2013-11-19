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
package WrenchDb.MVC.Configuration;

import WrenchDb.Core.Configuration.*;
import WrenchDb.Core.Helpers.WdbStringHelper;
import WrenchDb.MVC.BaseClasses.RuleMatchingResult;
import org.apache.commons.lang.StringUtils;
import org.reflections.Configuration;


public class RouteMapSet 
    extends ItemAppendingConfigurationContainer<RouteMap> 
{
    
    @Override
    public String getName() {
       return "RouteMapSet";
    }
    
     @Override
    public Class<?> GetType()
    {
        return  RouteMapSet.class ;
    }
    
    public RouteMap GetRouteForUrl(String url)
    {
        RuleMatchingResult result=null;
        for(RouteMap  r: this.getItems() )
        {
            result=IsMatchingUrl( r,  url);
            if(result.IsMatching)
            {
              return r;
            }
        }
        return null;
    }
    
    
     public RuleMatchingResult GetRouteResult(String url)
    {
        RuleMatchingResult result=new RuleMatchingResult();
        result.IsMatching=false;
        for(RouteMap  r: this.getItems() )
        {
            result=IsMatchingUrl( r,  url);
            if(result.IsMatching)
            {
              return result;
            }
        }
        return result;
    }
    
    public RouteMap GetRouteByName(String name)
    {
        for(RouteMap  r: this.getItems() )
        {
            if(r.getName().equalsIgnoreCase(name))
            {
              return r;
            }
        }
        return null;
    }

    
    private RuleMatchingResult IsMatchingUrl(RouteMap r, String url) {        
       
        RuleMatchingResult result=new RuleMatchingResult();
        result.IsMatching=true;
        if(r==null) return result;
        
       String[] tokensUrl=WdbStringHelper.split(url,"/");
       String[] tokensRule=WdbStringHelper.split(r.getUrl(),"/");
       
       if(tokensUrl.length!=tokensRule.length)
       {
           result.IsMatching=false;
           return result;
       
       }
           
       boolean perfectMatch=true;
       String tmpKey="";
       for(int i=0;i<tokensUrl.length;i++)
       {
           if(tokensRule.length>i)
           {
               if(tokensRule[i].startsWith("{") && tokensRule[i].endsWith("}"))
               {
                   tmpKey=tokensRule[i].substring(1,tokensRule[i].length()-1);
                   result.ModelData.put(tmpKey,tokensUrl[i]);
               }
               else
               {
                   perfectMatch &= tokensRule[i].equalsIgnoreCase(tokensUrl[i]);
               }
           }
           else
           {
               perfectMatch=false;
           }
       }
       if(result.ModelData.containsKey("controller"))
       {
           result.ControllerName=(String)result.ModelData.get("controller");
       }
       
       
       if(result.ModelData.containsKey("action"))
       {
           result.ActionName=(String)result.ModelData.get("action");
       }
      
       
       //apply default settings
       if( result.IsMatching)
       {
           if(StringUtils.isBlank(result.ActionName) || result.ActionName.equalsIgnoreCase("default"))
               result.ActionName=r.getDefaultActionName();
            if(StringUtils.isBlank(result.ControllerName) || result.ControllerName.equalsIgnoreCase("default"))
               result.ControllerName=r.getDefaultControllerName();
       }
       
  
       return result;
    }

    
    
}
