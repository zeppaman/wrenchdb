/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests;

import Wrench.Db.Tests.Helpers.Utils;
import WrenchDb.Core.Helpers.ArchiveUnZipper;
import WrenchDb.Core.Helpers.ArchiveZipper;
import WrenchDb.Core.Helpers.PathHelper;
import WrenchDb.Deploy.Model.JarPackage;
import WrenchDb.Deploy.Server.WdbDeployRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Daniele Fontani
 */
public class DeployRepositoryTests {
    String repoRoot="";
     String tmpRoot="";
    @Before
    public void setUp() {
    
        Utils.InitHContext();
       tmpRoot="C:\\temp\\";
       repoRoot=tmpRoot+"\\reporoot\\";
    }
    @Test
    public void repositoryTestEnsureRelease()
    {
       
        WdbDeployRepository repo= new WdbDeployRepository(repoRoot);
        repo.ensureRelease(1);
        
    }
    
    
    @Test
    public void testZipFolder()
    {
        String zipFile=PathHelper.combine(tmpRoot,"test.zip");
        ArchiveZipper zip= new ArchiveZipper(zipFile,repoRoot);
        zip.zip();
        zip.Dispose();
    }
    
    @Test
    public void testUnZipFolder()
    {
        String zipFile=PathHelper.combine(tmpRoot,"test.zip");
        ArchiveUnZipper zip= new ArchiveUnZipper();
        zip.unZip(zipFile, PathHelper.combine(tmpRoot,"out"));
        zip.Dispose();
    }
    
    
    @Test
    public void repositoryTestCreateWar() throws Exception
    {
       
        WdbDeployRepository repo= new WdbDeployRepository(repoRoot);
        repo.ensureRelease(1);
        String warpath= repo.getReleaseWarPath(1);
        String jarpath= repo.getReleaseJarPath(1);
        String tmplPath= repo.getEmptyWarTemplate();
        String tmpUri=repo.getReleaseTempPath(1);
     
        repo.createReleaseWar(1);
    }
    
    
    @Test
    public void combineTest()
    {
        String s=PathHelper.combine("C:\\temp\\", "WEB-INF/lib/",PathHelper.getFileName("C:\\prova\\test.zip"));
        Assert.assertEquals(s, "C:\\temp\\WEB-INF\\lib\\test.zip");
    }
    
    
    @Test
    public void ImportJar() throws Exception
    {
       
        WdbDeployRepository repo= new WdbDeployRepository(repoRoot);
        repo.ensureRelease(1);
        String warpath= repo.getReleaseWarPath(1);
        String jarpath= repo.getReleaseJarPath(1);
        String tmplPath= repo.getEmptyWarTemplate();
        String tmpUri=repo.getReleaseTempPath(1);
     
        JarPackage jr= new JarPackage();
        jr.LoadFromFile(PathHelper.combine(tmpRoot,"test.jar"));
        
        repo.storeJarCustomization(1,jr );
    }
}
