/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

 
public class ArchiveZipper {

  List<String> fileList;
    public   String outputFileName = null;
    public  String sourceFolder =null;
 
    public ArchiveZipper(String outputFileName,String sourceFolder){
	fileList = new ArrayList<String>();
        this.outputFileName=(new File(outputFileName)).getAbsolutePath();
        this.sourceFolder=(new File(sourceFolder)).getAbsolutePath();
    }
 
   
 

    public void zip( ){
 
     byte[] buffer = new byte[1024];
 
     try{
 
    	FileOutputStream fos = new FileOutputStream(outputFileName);
    	ZipOutputStream zos = new ZipOutputStream(fos);
 
            this.generateFileList(new File(sourceFolder));
 
    	for(String file : this.fileList){
 
    		
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);
 
        	FileInputStream in = 
                       new FileInputStream(sourceFolder + File.separator + file);
 
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
 
        	in.close();
    	}
 
    	zos.closeEntry();
    	//remember close it
    	zos.close();
 
    	
    }catch(IOException ex){
       ex.printStackTrace();   
    }
   }
 

    public void generateFileList(File node){
 
    	//add file only
	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
	}
 
	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename));
		}
	}
 
    }
 

    private String generateZipEntry(String file){
    	return file.substring(sourceFolder.length()+1, file.length());
    }

    public void Dispose() {
       
    }
}
