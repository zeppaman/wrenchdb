/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Interfaces;

import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.Core.Helpers.JSONRenderer;

/**
 *
 * @author d.fontani
 */
public interface JSONRenderizzable {
    
    public String RenderAsJSON();
    
    public void RenderAsJSON(JSONRenderer sb);
}
