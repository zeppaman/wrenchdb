/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Servelets;

import WrenchDb.MVC.BaseClasses.Model.RequestModel;
import WrenchDb.MVC.BaseClasses.Model.ModelBase;
import WrenchDb.MVC.BaseClasses.*;
import WrenchDb.MVC.Context.WdbAppContext;
import WrenchDb.MVC.Helpers.HtmlHelper;
import WrenchDb.MVC.Helpers.WdbPageManager;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author d.fontani
 */
//WrenchDb.MVC.Servelets.MVCRoutingServlet
public class MVCRoutingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        
        ActionResult ar= null;
        
        try {
            super.service(req, resp); 

            String url = req.getRequestURI();
            //resp.getWriter().print(url);
            RuleMatchingResult result = WdbAppContext.Current().GetRouteResult(url);
            RequestModel m = RequestModel.CreateRequestModel(result.ModelData, req);
           
            
            if (!result.IsMatching) {
                ar= new ActionResult("404","OneColumn",m);
            }
            else
            {
                // ModelBase model=result.
                ControllerBase cb = WdbAppContext.Current().getController(result.ControllerName);
                Class<?>[] args = new Class<?>[1];
                args[0] = ModelBase.class;

                Method control = cb.getClass().getMethod(result.ActionName, args);
                 ar = (ActionResult) control.invoke(cb, m);
                if (ar == null || ar.ViewName == null || ar.ViewName.equals("")) {
                  ar= new ActionResult("500","OneColumn",m);
                } 
            }
            
           


        } catch (Exception ex) {
            Logger.getLogger(MVCRoutingServlet.class.getName()).log(Level.SEVERE, null, ex);
            HashMap hm = new HashMap();
            hm.put("error", ex.getClass() + " " + ex.getMessage());
            String s = "";
            StackTraceElement[] ele = ex.getStackTrace();
            if (ele != null) {
                for (int i = 0; i < ele.length; i++) {
                    s += ele[i].toString() + "\n";
                }
            }
            hm.put("stacktrace", s);
            
            
            ar= new ActionResult("500","OneColumn",RequestModel.CreateRequestModel(hm, req));
  
         }
         WdbPageManager pm= new WdbPageManager(req, ar,resp);
         pm.RenderView();
    }
}
