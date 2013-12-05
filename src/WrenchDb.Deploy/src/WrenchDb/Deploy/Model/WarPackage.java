/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Model;

import WrenchDb.Core.Helpers.PathHelper;

/**
 *
 * @author Daniele Fontani
 */
public class WarPackage 
extends IApplicationScopedEntity
implements IFileDeployItem{

    private String filepath="";
    private String name;
    @Override
    public String getFilePath() {
        return filepath;
    }

    @Override
    public String setName() {
        return name;
    }
     @Override
    public void LoadFromFile(String path) {
       this.filepath=path;
       this.name=PathHelper.getFileName(path);
    }
    
    /*** PUT ADDITIONAL INFO METHOD HERE **/
}
