/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Helpers;

import WrenchDb.MVC.BaseClasses.ModelBase;
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
    
    
    
    public static void RenderView(String ViewName,ModelBase model, PrintWriter writer,ServletContext scontext)
    {
      
          try
        {
            Properties p= new Properties();
            p.setProperty("resource.loader", "file");
            p.setProperty("webapp.resource.loader.class", "org.apache.velocity.tools.view.WebappResourceLoader");
            p.setProperty("webapp.resource.loader.path", "/WEB-INF/Views/");
             p.setProperty("file.resource.loader.path", scontext.getRealPath("/WEB-INF"));
            Velocity.init(p);
            
          
            Velocity.setApplicationAttribute("javax.servlet.ServletContext", scontext);
            
           

            VelocityContext context = new VelocityContext();
        
            context.put("Model",model);

           String templateFile="Views/"+ViewName+".vm";
            
           Template template =  null;

            try
            {
                template = Velocity.getTemplate(templateFile);
            }
            catch( ResourceNotFoundException rnfe )
            {
              
                  Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : cannot find template " + templateFile, rnfe);
            }
            catch( ParseErrorException pee )
            {
                       Logger.getLogger(HtmlHelper.class.getName()).log(Level.SEVERE, "Example : error : cannot PARSE template " + templateFile, pee);
      
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
            System.out.println(e);
        }
    }

    
}
