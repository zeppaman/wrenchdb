/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Server;

import WrenchDb.Core.Helpers.ArchiveUnZipper;
import WrenchDb.Core.Helpers.ArchiveZipper;
import WrenchDb.Core.Helpers.PathHelper;
import WrenchDb.DAL.Entities.*;
import WrenchDb.DAL.Helpers.HContext;
import WrenchDb.Deploy.Model.JarPackage;
import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author Daniele Fontani
 */
public class WdbDeployRepository 
{
    private HashMap releasecache= new HashMap();
    private String rootPath="";
    
    public void setReleaseCacheItem( WdbRelease release)
    {
          releasecache.put(release.getWdbReleaseId(), release);
     }
    public String getEmptyWarTemplate()
    {
        // /wdb_version/templates/empty.war
        //TODO: do fallback from configuration to default;
        return  PathHelper.combine(rootPath,"/1.0/templates/empty.war");
    }
    
    public String getReleaseJarPath(long releaseId)
    {
        WdbRelease release= getCachedRelease(releaseId);
        // /wdb_version/templates/empty.war
        //TODO: do fallback from configuration to default;
        return   PathHelper.combine(rootPath,"/1.0/releases/"+release.getWdbApplication().getApplicationId()+"/"+release.getWdbReleaseId()
                +"/jar/wdb_customization_"+release.getReleaseMemocode()+".jar"); 
    }
    
    public String getReleaseWarPath(long releaseId)
    {
        WdbRelease release= getCachedRelease(releaseId);
        // /wdb_version/templates/empty.war
        //TODO: do fallback from configuration to default;
        return  PathHelper.combine(rootPath,"/1.0/releases/"+release.getWdbApplication().getApplicationId()+"/"+release.getWdbReleaseId()
                +"/war/wdb_"+release.getReleaseMemocode()+".war"); 
    }
    public String getReleaseTempPath(long releaseId)
    {
        WdbRelease release= getCachedRelease(releaseId);
        // /wdb_version/templates/empty.war
        //TODO: do fallback from configuration to default;
        return  PathHelper.combine(rootPath,"/1.0/releases/"+release.getWdbApplication().getApplicationId()+"/"+release.getWdbReleaseId()
                +"/tmp"); 
    }
    public String getRootPath()
    {
        return rootPath;
    }
    public WdbDeployRepository(String rootPath)
    {
        this.rootPath=rootPath;   
        String tmpDir=getUploadTempDir();
        PathHelper.createFolderIfNotExists(this.rootPath);
        PathHelper.createFolderIfNotExists(  tmpDir  );
    }
    
   
    public void storeJarCustomization(long releaseId, JarPackage jar)
    {
       String destUri= getReleaseJarPath(releaseId);
       ensureRelease(releaseId);
       PathHelper.copy(jar.getFilePath(),destUri);
        
    }
    public void ensureRelease(long releaseId)
    {
        
       //TODO: create folder for release
        PathHelper.createFolderIfNotExists(getReleaseJarPath(releaseId));
        PathHelper.createFolderIfNotExists(getReleaseWarPath(releaseId));
        PathHelper.createFolderIfNotExists(getReleaseTempPath(releaseId));
        
        
    }
    public void createReleaseWar(long releaseId) throws Exception
    {
        ensureRelease(releaseId);
        String jarUri= getReleaseJarPath(releaseId);
        String destUri= getReleaseWarPath(releaseId);
        String warUritmpl=getEmptyWarTemplate();
        String tmpUri=getReleaseTempPath(releaseId);
        
       //if file jar not existes throw exception
        if(!PathHelper.exists(jarUri)) throw new Exception("Unable to find JAR linked to this release.");
        // extract template path into temp path
        ArchiveUnZipper unz= new ArchiveUnZipper();
        unz.unZip(warUritmpl, tmpUri);
        unz.Dispose();
        //add jato to lib folder
        
        if(PathHelper.copy(jarUri,PathHelper.combine(tmpUri, "WEB-INF/lib/",PathHelper.getFileName(jarUri))))
        {
            //compress file to temp folder
            ArchiveZipper zip= new ArchiveZipper(destUri,tmpUri);
            zip.zip();
            zip.Dispose();
            //delete temp folder+
            PathHelper.delete(tmpUri);
        }
        else
        {
            throw new Exception("unable to copy jar file into tmp path");
        }
        
    }

    private WdbRelease getCachedRelease(long releaseId) {
       if(!releasecache.containsKey(releaseId))
       {
          releasecache.put(releaseId, HContext.Current().Get(WdbRelease.class, releaseId));
       }
       return (WdbRelease)releasecache.get(releaseId);
    }

    public String getUploadTempDir() {
        return PathHelper.combine(rootPath,"temp");
    }

    public String getTempFileaname() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getTempRandomFilePath() {
        
       return PathHelper.combine( getUploadTempDir(), UUID.randomUUID().toString()+".jar");
    }
}
