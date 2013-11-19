/* 
 * Copyright (C) 2013 Daniele Fontani
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
