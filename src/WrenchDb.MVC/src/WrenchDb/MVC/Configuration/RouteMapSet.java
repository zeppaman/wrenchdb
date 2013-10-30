/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Configuration;

import WrenchDb.Core.Configuration.*;
import WrenchDb.MVC.BaseClasses.RuleMatchingResult;
import org.reflections.Configuration;


public class RouteMapSet extends ReflectionBasedConfiguration<RouteMap> 
{
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
        
       String[] tokensUrl=url.split("/",-1);
       String[] tokensRule=r.getUrl().split("/",-1);
       
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
                   perfectMatch &= tokensRule[i].equalsIgnoreCase(tokensUrl[0]);
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
       else
       {
           result.IsMatching=false;
       }
       
       if(result.ModelData.containsKey("action"))
       {
           result.ActionName=(String)result.ModelData.get("action");
       }
       else
       {
           result.IsMatching=false;
       }
       
       //apply default settings
       if( result.IsMatching)
       {
           if(result.ActionName.equalsIgnoreCase("default"))
               result.ActionName=r.getDefaultActionName();
            if(result.ControllerName.equalsIgnoreCase("default"))
               result.ControllerName=r.getDefaultControllerName();
       }
       
  
       return result;
    }
    
}
