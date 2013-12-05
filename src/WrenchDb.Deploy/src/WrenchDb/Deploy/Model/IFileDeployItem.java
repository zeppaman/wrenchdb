/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Model;

/**
 *
 * @author Daniele Fontani
 */
public interface IFileDeployItem {
     String getFilePath();
     String setName();
     
     public void LoadFromFile(String path);
}
