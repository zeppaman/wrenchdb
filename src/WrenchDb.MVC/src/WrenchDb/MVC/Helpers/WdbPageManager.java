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
package WrenchDb.MVC.Helpers;

import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.MVC.BaseClasses.ActionResult;
import WrenchDb.MVC.BaseClasses.CssReference;
import WrenchDb.MVC.BaseClasses.LinkReference;
import WrenchDb.MVC.BaseClasses.Model.ModelBase;
import WrenchDb.MVC.BaseClasses.ScriptReference;
import WrenchDb.MVC.Context.WdbAppContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.VelocityContext;

/**
 *
 * @author d.fontani
 */
public class WdbPageManager {

    private HttpServletRequest _servletReqCtx = null;
    private HttpServletResponse _servletRespCtx = null;
    private ActionResult _ActionContext  = null;
    HtmlRenderer _renderer= new HtmlRenderer();
    private VelocityContext _velocityCtx= null;
    public WdbPageManager(HttpServletRequest Reqctx, ActionResult ActionContext,HttpServletResponse RespCtx) {
        this._servletReqCtx = Reqctx;
        this._servletRespCtx=RespCtx;
        this._ActionContext=ActionContext;
        this._velocityCtx=HtmlHelper.InitVelocityCtx(this);
       
    }

    public String RenderScripts() 
    {
       ArrayList<ScriptReference> scripts= WdbAppContext.Current().getScriptSet().getItems();
      _renderer.Clear();
       for(ScriptReference s:  scripts )
       {
          s.RenderAsHtml(_renderer); 
       }
       return _renderer.GetHtml();
    }
    
     public String RenderCss() 
    {
       ArrayList<LinkReference> css= WdbAppContext.Current().getLinkSet().getItems();
      _renderer.Clear();
       for(LinkReference s:  css )
       {
          s.RenderAsHtml(_renderer); 
       }
       return _renderer.GetHtml();
    }

    /**
     * @return the _servletCtx
     */
    public HttpServletRequest getServletRequest() {
        return _servletReqCtx;
    }
     public HttpServletResponse getServletResponse() {
        return _servletRespCtx;
    }

    /**
     * @return the _model
     */
    public ModelBase getModel() {
        return _ActionContext.Model;
    }

    public String getLayoutName()
    {
        return _ActionContext.LayoutName;
    }
    public String getViewName() {
        return _ActionContext.ViewName;
    }
    
    public void RenderView() throws IOException
    {
        try
        {
         HtmlHelper.RenderView(getLayoutName(), getModel(), _servletRespCtx.getWriter(), _velocityCtx, _servletReqCtx);
        }
        catch(Exception err)
        {
            _servletRespCtx.getWriter().append("<!--");
            _servletRespCtx.getWriter().append(err.getMessage());
            _servletRespCtx.getWriter().append("-->");
                 Logger.getLogger(HtmlHelper.class.getName())
                         .log(Level.SEVERE, "RenderView ERROR", err);
    
        }
                
    }
    public String RenderRegion(String regionName)
    {
       StringWriter writer = new StringWriter();
       HtmlHelper.RenderPartialView(regionName, this, _velocityCtx, writer);
       return writer.toString();
        
    }

    /**
     *
     * @return
     */
    public ServletContext getServletContext() {
            return this._servletReqCtx.getServletContext();
    }

}
