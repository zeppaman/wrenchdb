/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses;

import WrenchDb.MVC.Interfaces.HtmlRenderizzable;
import WrenchDb.Core.Classes.HtmlRenderer;

/**
 *
 * @author d.fontani
 */
public class ScriptReference 
    implements HtmlRenderizzable 
 {
    //asyncNew 	async 	Specifies that the script is executed asynchronously (only for external scripts)
    public Boolean Async=null;
    //charset 	charset 	Specifies the character encoding used in an external script file
    public String  Charset=null;
    //defer 	defer 	Specifies that the script is executed when the page has finished parsing (only for external scripts)
    public Boolean Defer=null;
    //src 	URL 	Specifies the URL of an external script file
    public String  Src=null;
    //type 	MIME-type 	Specifies the MIME type of the script
    public String  Type=null;

    @Override
    public String RenderAsHtml() {
        HtmlRenderer sb= new HtmlRenderer();
        RenderAsHtml(sb);
        return sb.toString();
    }

    @Override
    public void RenderAsHtml(HtmlRenderer sb) {
        if(Boolean.TRUE.equals( Async))
        {
          sb.AppendAttribute("async", null);
        }
        if(Boolean.TRUE.equals( Defer))
        {
           sb.AppendAttributeIfHasValue("defer", null);
        }
        sb.AppendAttributeIfHasValue("charset", Charset);
        sb.AppendAttributeIfHasValue("src", Src);
        sb.AppendAttributeIfHasValue("type", Type);
        sb.RenderBeginTag("script");
        sb.RenderEndTag();
    }
}
