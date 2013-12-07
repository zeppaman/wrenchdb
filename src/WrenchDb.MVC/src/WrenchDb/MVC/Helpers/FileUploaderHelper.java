/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 *
 * @author Daniele Fontani
 */
public class FileUploaderHelper {
    
    
     public static boolean UploadFile(HttpServletRequest request,String fileName,String path) 
    {
         try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts){
                    if( fileName.equals(item.getFieldName())){                       
                        item.write( new File(path));
                    }
                }
           
               
             
            } catch (Exception ex) {
               Logger.getLogger("FileUpload").log(Level.SEVERE,"UploadFiles",ex);
            }          
         return false;
    }

    /**
     * NOT WORKING
     *//*
    public static boolean UploadFile(HttpServletRequest request,String PartName,String path) 
            
    {
        try
        {
     // Create path components to save the file

     Part filePart = request.getPart(PartName);
     String fileName = getFileName(filePart);

    OutputStream out = null;
    InputStream filecontent = null;
  

    try {
        out = new FileOutputStream(new File(path + File.separator
                + fileName));
        filecontent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        
        
    } catch (FileNotFoundException fne) {
        //TODO: Add log here
        return false;
    } finally {
        if (out != null) {
            out.close();
        }
        if (filecontent != null) {
            filecontent.close();
        }
       
    }
    return true;
        }
        catch(Exception err)
        {
        }
        return false;
  }

   public static String  getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }*/
}
