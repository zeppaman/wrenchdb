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
package WrenchDb.MVC.Helpers;

import WrenchDb.MVC.BaseClasses.ActionResult;
import WrenchDb.MVC.BaseClasses.Model.ModelBase;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 *
 * @author d.fontani
 */
public class HtmlHelper {
    
    /**
     * wdb/custom/Views/<view-name>/<region-name>-<view-name>.vm
     */
    public static   String ViewRegionFilePathTemplateCustom="wdb/custom/Views/%s/%s-%s.vm";
     /**
     * wdb/custom/Views/<view-name>/<region-name>-<view-name>.vm
     */
    public static   String ViewRegionFilePathTemplate="wdb/default/Views/%s/%s-%s.vm";
    
    
     /**
     * wdb/custom/Layouts/<layout-name>/<region-name>.vm
     */
    public static   String LayoutRegionFilePathTemplateCustom="wdb/custom/Layouts/%s/%s-%s.vm";
   
    
     /**
     * wdb/default/Layouts/<layout-name>/<region-name>.vm
     */
    public static   String LayoutRegionFilePathTemplate="wdb/default/Layouts/%s/%s-%s.vm";
    
    /**
     * wdb/custom/Views/<layout-name>/<layout-name>.vm
     */
    public static   String LayoutPathTemplateCustom="wdb/custom/Layouts/%s/%s.vm";
     /**
     * wdb/custom/Views/<layout-name>/<layout-name>.vm
     */
    public static   String LayoutPathTemplate="wdb/default/Layouts/%s/%s.vm";
    
    public static void RenderView(String Layout,ModelBase model, PrintWriter writer,VelocityContext context, HttpServletRequest scontext)
    {
      
          try
        {
            
            
            List<String> paths= new ArrayList<String>();
            paths.add(String.format(LayoutPathTemplateCustom,Layout,Layout)); //Custom layout path
            paths.add(String.format(LayoutPathTemplate,Layout,Layout)); //Custom layout path
              Template template =            GetFirstTemplateFound(paths);
           
              
   

            if ( template != null)
                template.merge(context, writer);



        }
        catch( Exception e )
        {
                Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : cannot RENDER VIEW  " 
                        + Layout, e);
      
        }
    }

    
    
    
    public static void RenderPartialView(String RegionName,WdbPageManager pm,VelocityContext context,Writer writer )
    {
      
          try
        {
         
            
           

            List<String> paths= new ArrayList<String>();
            paths.add(String.format(ViewRegionFilePathTemplateCustom,pm.getViewName(),RegionName,pm.getViewName())); //Override in view 
            paths.add(String.format(ViewRegionFilePathTemplate,pm.getViewName(),RegionName,pm.getViewName())); //default in view
            paths.add(String.format(LayoutRegionFilePathTemplateCustom,pm.getLayoutName(),RegionName,pm.getLayoutName())); //Default in layout override
            paths.add(String.format(LayoutRegionFilePathTemplate,pm.getLayoutName(),RegionName,pm.getLayoutName())); //default in layout

            
           Template template =  GetFirstTemplateFound(paths);
            
            if ( template != null)
                template.merge(context, writer);

        }
        catch( Exception e )
        {
                Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : cannot RENDER VIEW  " + pm.getViewName()+" "+ RegionName+ " "+pm.getLayoutName(), e);
      
        }
    }

    public static VelocityContext InitVelocityCtx(WdbPageManager pm) {
        Properties p= new Properties();
        p.setProperty("resource.loader", "file");
        p.setProperty("file.resource.loader.path", pm.getServletContext().getRealPath("/WEB-INF"));
        Velocity.init(p);
        Velocity.setApplicationAttribute("javax.servlet.ServletContext", pm.getServletContext());
        VelocityContext context = new VelocityContext();
        context.put("Model",pm.getModel());
        context.put("Render", pm);
        return context;
    }

    /**
     * Try to load template form "templateFile" into "template". Found=true and template=null means an error occours.
     * @param templateFile
     * @param template
     * @param found  true if file is found
     */
    private static Template GetTemplateIfFound(String templateFile) {
           
           try
            {
                return  Velocity.getTemplate(templateFile);
                // fallback on default template
             
            }
             catch( ParseErrorException pee )
            {
                       Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : cannot PARSE template " + templateFile, pee);
      
            }
            catch( Exception err )
            {  
                
               Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : UNABLE TO FIND template  OR ERRROR: FALLBACK TO DEFAULT EQUIVALENT" + templateFile, err);
            }
           return null;
    }

    private static Template GetFirstTemplateFound(List<String> paths) {
        Template template=null;
         for(String path : paths)
         {
            template= GetTemplateIfFound(path);
             if( template!=null)
                 return template;
         }
         return null;
         
    }
}
