/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Helpers;

import WrenchDb.Core.Classes.HtmlRenderer;
import WrenchDb.MVC.BaseClasses.CssReference;
import WrenchDb.MVC.BaseClasses.LinkReference;
import WrenchDb.MVC.BaseClasses.Model.ModelBase;
import WrenchDb.MVC.BaseClasses.ScriptReference;
import WrenchDb.MVC.Context.WdbAppContext;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author d.fontani
 */
public class WdbPageManager {

    private ServletContext _servletCtx = null;
    private ModelBase _model  = null;
    HtmlRenderer _renderer= new HtmlRenderer();
    public WdbPageManager(ServletContext ctx, ModelBase Model) {
        this._servletCtx = ctx;
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
    public ServletContext getServletCtx() {
        return _servletCtx;
    }

    /**
     * @return the _model
     */
    public ModelBase getModel() {
        return _model;
    }
}
