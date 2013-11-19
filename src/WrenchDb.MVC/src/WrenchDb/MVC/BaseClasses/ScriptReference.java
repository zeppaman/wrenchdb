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

import WrenchDb.Core.Interfaces.HtmlRenderizzable;
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
