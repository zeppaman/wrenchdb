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
package WrenchDb.MVC.BaseClasses;

import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.Core.Interfaces.HtmlRenderizzable;


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
