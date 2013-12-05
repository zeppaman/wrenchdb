/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ArchiveUnZipper  {
       List<String> fileList;
 
    
    	
 
    public void unZip(String zipFile, String outputFolder){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	//create output directory is not exists
    	File folder = new File(outputFolder);
    	if(!folder.exists()){
    		folder.mkdirs();
    	}
 
    	//get the zip file content
    	ZipInputStream zis = 
    		new ZipInputStream(new FileInputStream(zipFile));
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
 
    	while(ze!=null){
 
    	   String fileName = ze.getName();
           
           File newFile = new File(outputFolder + File.separator + fileName);
 
       
            if (ze.isDirectory()){
                   newFile.mkdirs();
               }else{
                  
            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();
 
            FileOutputStream fos = new FileOutputStream(newFile);             
 
            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }
 
            fos.close();   
      }
            ze = zis.getNextEntry();
    	}
 
        zis.closeEntry();
    	zis.close();
 
    
 
    }catch(IOException ex){
       ex.printStackTrace(); 
    }
   }    

    public void Dispose() {
       
    }

}
