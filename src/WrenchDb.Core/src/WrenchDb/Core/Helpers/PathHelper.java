/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Helpers;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Daniele Fontani
 */
public class PathHelper {
    public static String getFileName(String path)
    {
        if(path==null || path.length()==0) return "";
        int idx= Math.max(path.lastIndexOf("\\"),path.lastIndexOf("/"));
        if(idx>0 && idx<path.length()) //folder exluded
        {
            return path.substring(idx+1,path.length());
        }
        return path;
    }

    public static String combine(String... paths) {
        
        String path=paths[0];
        if(paths.length>1)
        {
        for(int i=1; i<paths.length;i++)
        {
            path+=File.separator+paths[i];
        }}
        return path.replace("/",File.separator).replace("\\",File.separator).replace(File.separator+File.separator, File.separator);
    }

      public static String getDirectoryName(String path) {
            File f= new File(path);
            if(f.isDirectory()) return path;
            return f.getParentFile().getAbsolutePath();
      }
      
      public static String getParentDirectoryName(String path) {
            File f= new File(path);            
            return f.getParentFile().getAbsolutePath();
      }
    public static void createFolderIfNotExists(String path) {
        String parentDirOrItself=getDirectoryName(path);
      if(!PathHelper.exists(parentDirOrItself))
      {
          PathHelper.createDirectory(parentDirOrItself);
      }
    }

    public static boolean exists(String path) {
       File f= new File(path);
       return f.exists();
       
    }

    public static void createDirectory(String path) {
       File f= new File(path);
       f.mkdirs();
    }
    
      public static void isDirectory(String path) {
       File f= new File(path);
       f.isDirectory();
    }
      
      public static void isFile(String path) {
       File f= new File(path);
       f.isFile();
    }

    public static boolean copy(String source, String dest)  {
        try
        {
            
                  Files.copy(new File(source),new File(dest));
                  return true;
        }
        catch(Exception err)
        {
            Logger.getLogger(PathHelper.class.getName()).log(Level.SEVERE,"copy error",err);
        }
        return false;
    }

    public static boolean delete(String tmpUri) {
          try
        {
           FileUtils.deleteDirectory(new File(tmpUri));
           return true;
         }
        catch(Exception err)
        {
            Logger.getLogger(PathHelper.class.getName()).log(Level.SEVERE,"delete error",err);
        }
        return false;
    }
}
