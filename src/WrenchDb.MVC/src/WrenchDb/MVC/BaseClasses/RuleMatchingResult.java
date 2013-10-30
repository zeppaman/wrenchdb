/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses;

import java.util.HashMap;

/**
 *
 * @author d.fontani
 */
public class RuleMatchingResult {
    
    public boolean IsMatching=false;
    public HashMap ModelData= new HashMap();
    public String ControllerName=null;
    public String ActionName=null;
}
