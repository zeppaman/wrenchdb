/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.BaseClasses;

import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.MVC.Interfaces.HtmlRenderizzable;


/**
 *
 * @author d.fontani
 */
public class LinkReference implements HtmlRenderizzable{
    
    
//charset 	char_encoding 	Not supported in HTML5. Specifies the character encoding of the linked document
public String charset;
//href 	URL 	Specifies the location of the linked document
public String href;
//hreflang 	language_code 	Specifies the language of the text in the linked document
public String hreflang;
//media 	media_query 	Specifies on what device the linked document will be displayed
public String media;
//rel 	
//alternate
//archives
//author
//bookmark
//external
//first
//help
//icon
//last
//license
//next
//nofollow
//noreferrer
//pingback
//prefetch
//prev
//search
//sidebar
//stylesheet
//tag
//up 	Required. Specifies the relationship between the current document and the linked document
public String rel;

//rev 	reversed relationship 	Not supported in HTML5. Specifies the relationship between the linked document and the current document
public String rev;
//sizesNew 	
//HeightxWidth
//any 	Specifies the size of the linked resource. Only for rel="icon"
public String sizesNew;
//target 	
//_blank
//_self
//_top
//_parent
//frame_name 	Not supported in HTML5. Specifies where the linked document is to be loaded
public String target;
//type 	MIME_type 	Specifies the MIME type of the linked document
public String type;

    @Override
    public String RenderAsHtml() {
        HtmlRenderer sb= new HtmlRenderer();
        RenderAsHtml(sb);
        return sb.toString();    }

    @Override
    public void RenderAsHtml(HtmlRenderer sb) {
       
        sb.AppendAttributeIfHasValue("href", href);
        sb.AppendAttributeIfHasValue("charset", charset);
        sb.AppendAttributeIfHasValue("hreflang", hreflang);
        sb.AppendAttributeIfHasValue("media", media);
        sb.AppendAttributeIfHasValue("rel", rel);
        sb.AppendAttributeIfHasValue("rev", rev);
        sb.AppendAttributeIfHasValue("sizesNew", sizesNew);
        sb.AppendAttributeIfHasValue("target", target);
        sb.AppendAttributeIfHasValue("type", type);
        sb.RenderBeginTag("link",true);
    }
}
