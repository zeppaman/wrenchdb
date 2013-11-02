/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Helpers;

import WrenchDb.MVC.BaseClasses.Model.ModelBase;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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
    
    public static   String ViewFilePathTemplateCustom="wdb/custom/Views/%s.vm";
    public static   String ViewFilePathTemplate="wdb/default/Views/%s.vm";
    
    public static void RenderView(String ViewName,ModelBase model, PrintWriter writer,ServletContext scontext)
    {
      
          try
        {
            WdbPageManager pm= new WdbPageManager(scontext, model);
            Properties p= new Properties();
            p.setProperty("resource.loader", "file");         
            p.setProperty("file.resource.loader.path", scontext.getRealPath("/WEB-INF"));
            Velocity.init(p);
            
          
            Velocity.setApplicationAttribute("javax.servlet.ServletContext", scontext);
            
           

            VelocityContext context = new VelocityContext();
        
            context.put("Model",model);
            context.put("Helper", pm);

           String templateFile=String.format(ViewFilePathTemplateCustom,ViewName);
            
           Template template =  null;

            try
            {
                template = Velocity.getTemplate(templateFile);
                // fallback on default template
              
            }
             catch( ParseErrorException pee )
            {
                       Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : cannot PARSE template " + templateFile, pee);
      
            }
            catch( Exception err )
            {  
               Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : UNABLE TO FIND template  OR ERRROR: FALLBACK TO DEFAULT EQUIVALENT" + templateFile, err);
      
                 try
                {
                     templateFile=String.format(ViewFilePathTemplate,ViewName);
                     template=Velocity.getTemplate(templateFile);
                }
                catch( ResourceNotFoundException rnfe2 )
                {
                    
                       Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : cannot FIND template " + templateFile, rnfe2);
      
                }
                 catch(Exception ex)
                 {
                     throw ex;
                 }
            }
           

            /*
             *  Now have the template engine process your template using the
             *  data placed into the context.  Think of it as a  'merge'
             *  of the template and the data to produce the output stream.
             */

           

            if ( template != null)
                template.merge(context, writer);

            /*
             *  flush and cleanup
             */

        }
        catch( Exception e )
        {
                Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : cannot RENDER VIEW  " + ViewName, e);
      
        }
    }

    
}
