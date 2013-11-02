/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests;



import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.Core.Helpers.WdbStringHelper;
import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Helpers.HContext;
import java.io.IOException;
import java.util.Date;
import javax.xml.ws.spi.http.HttpContext;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author d.fontani
 */
public class HtmlHelperTests {
    
    @Test
    public void RenderEmptyTag()
    {
        HtmlRenderer renderer= new HtmlRenderer();
        
        renderer.RenderBeginTag("script");
        renderer.RenderEndTag();
        String result= renderer.GetHtml();
        assertEquals(result, "<script></script>");
    }
    
    @Test
    public void RenderEmptyTag2()
    {
        HtmlRenderer renderer= new HtmlRenderer();
        
        renderer.RenderBeginTag("script",true);
        String result= renderer.GetHtml();
        assertEquals(result, "<script/>");
    }
    
    @Test
    public void RenderAttribute()
    {
        HtmlRenderer renderer= new HtmlRenderer();
        renderer.AppendAttribute("a", "b");
        renderer.RenderBeginTag("script",true);
        String result= renderer.GetHtml();
        assertEquals(result, "<script a=\"b\" />");
    }
    
    @Test
    public void RenderEmptyAttribute()
    {
        HtmlRenderer renderer= new HtmlRenderer();
        renderer.AppendAttribute("a", "");
        renderer.RenderBeginTag("script",true);
        String result= renderer.GetHtml();
        assertEquals(result, "<script a=\"b\" />");
    }
    
     @Test
    public void RenderEmptyAttributeIfNotNull()
    {
        HtmlRenderer renderer= new HtmlRenderer();
        renderer.AppendAttributeIfHasValue("a", null);
        renderer.RenderBeginTag("script",true);
        String result= renderer.GetHtml();
        assertEquals(result, "<script/>");
    }
}
