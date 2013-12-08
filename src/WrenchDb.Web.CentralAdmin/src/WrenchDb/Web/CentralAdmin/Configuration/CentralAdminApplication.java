package WrenchDb.Web.CentralAdmin.Configuration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import WrenchDb.MVC.Annotations.MainApplication;
import WrenchDb.MVC.BaseClasses.Model.ApplicationInfo;

/**
 *
 * @author Daniele Fontani
 */
@MainApplication
public class CentralAdminApplication
extends  ApplicationInfo
{

    @Override
    public String getApplicationName() {
        return "WrenchDb.Web.CentralAdmin";
    }

    @Override
    public String getClassPathRoot() {
         return "/WrenchDb/Web/CentralAdmin/";
    }
    
    
}
