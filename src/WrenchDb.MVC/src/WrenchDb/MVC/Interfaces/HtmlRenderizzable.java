/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Interfaces;

import WrenchDb.Core.Classes.HtmlRenderer;

/**
 *
 * @author d.fontani
 */
public interface  HtmlRenderizzable {
    
    public String RenderAsHtml();
    
    public void RenderAsHtml(HtmlRenderer sb);
}
