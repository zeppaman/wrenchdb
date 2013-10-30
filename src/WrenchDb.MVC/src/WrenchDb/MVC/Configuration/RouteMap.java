/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Configuration;

/**
 *
 * @author d.fontani
 */
public class RouteMap {
    
    private String _Url="";
    private String _DefaultControllerName="";
    private String _DefaultActionName="";
    private String _Name="";

    /**
     * @return the _Url
     */
    public String getUrl() {
        return _Url;
    }

    /**
     * @param Url the _Url to set
     */
    public void setUrl(String Url) {
        this._Url = Url;
    }

    /**
     * @return the _DefaultControllerName
     */
    public String getDefaultControllerName() {
        return _DefaultControllerName;
    }

    /**
     * @param DefaultControllerName the _DefaultControllerName to set
     */
    public void setDefaultControllerName(String DefaultControllerName) {
        this._DefaultControllerName = DefaultControllerName;
    }

    /**
     * @return the _DefaultActionName
     */
    public String getDefaultActionName() {
        return _DefaultActionName;
    }

    /**
     * @param DefaultActionName the _DefaultActionName to set
     */
    public void setDefaultActionName(String DefaultActionName) {
        this._DefaultActionName = DefaultActionName;
    }

    /**
     * @return the _Name
     */
    public String getName() {
        return _Name;
    }

    /**
     * @param Name the _Name to set
     */
    public void setName(String Name) {
        this._Name = Name;
    }
}
