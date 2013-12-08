/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Helpers;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
   /**
    * 
    * @param path
    * @return 
    */
      public static String getDirectoryName(String path) {
            File f= new File(path);
            if(f.exists() && f.isDirectory() ) return path;            
            if(f.exists() && f.isFile()) return f.getParent();
            // not existing file or directory
            // no way do know if it si a no extension file or folder
            // by default return path
            if(path.contains("."))
            {
                return f.getParent();
            }
            return path;
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
    
    
    public static void copyFolder(String src, String dest) throws IOException
    {
       File fsrc= new File(src);
       File fdest =new File(dest);
       copyFolder(fsrc, fdest);
    }
    
    public static void copyFolder(File src, File dest)
    	throws IOException{
 
    	if(src.isDirectory()){
 
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		  
    		}
 
    		//list all the directory contents
    		String files[] = src.list();
 
    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
 
    	}else{
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	       
    	}
    }
}
