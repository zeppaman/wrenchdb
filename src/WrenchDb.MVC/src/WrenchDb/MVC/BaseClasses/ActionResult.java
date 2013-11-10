/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses;

import WrenchDb.MVC.BaseClasses.Model.ModelBase;

/**
 *
 * @author d.fontani
 */
public class ActionResult {
    public ModelBase Model=null;
    public String ViewName=null;
    public String LayoutName=null;
    
    public ActionResult(String ViewName,String LayoutName,ModelBase Model)
    {
        this.ViewName=ViewName;
        this.LayoutName=LayoutName;
        this.Model=Model;
    }
}
