/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses.Model;

import WrenchDb.Core.Helpers.PathHelper;
import java.io.IOException;

/**
 *
 * @author Daniele Fontani
 */
public abstract class ApplicationInfo {
    public abstract String getApplicationName();
    /**
     * Project prefix 
     * ie. WrinchDb.Web.CentralAdmin
     * @return 
     */
    public abstract String getClassPathRoot();
    
    public  String getCustomWebPath()
    {
        return getClassPathRoot()+"/web/";
    }
    
//    public  String getCustomWebInfPath()
//    {
//        return getClassPathRoot()+"/web/";
//    }
//    
    public void CopyToWeb(String webInf)
    {
        try
        {
        String url = this.getClass().getResource("").getPath();
        int idx=url.lastIndexOf(getClassPathRoot());
        String basePath=url.substring(0,idx);
        basePath=PathHelper.combine(basePath,getCustomWebPath());
        String wdbcustom=PathHelper.combine(webInf,"wdb","custom");
        PathHelper.copyFolder(basePath, wdbcustom);
        }
        catch(Exception ex)
        {
            
        }
        
    }

}
