package WrenchDb.Web.ProjectTemplate.Configuration;

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
public class ProjectTemplateApplication
extends  ApplicationInfo
{

    @Override
    public String getApplicationName() {
        return "WrenchDb.Web.ProjectTemplate";
    }

    @Override
    public String getClassPathRoot() {
         return "/WrenchDb/Web/ProjectTemplate/";
    }
    
    
}
