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
        try {
            super.service(req, resp); //To change body of generated methods, choose Tools | Templates.

            String url = req.getRequestURI();
            //resp.getWriter().print(url);
            RuleMatchingResult result = WdbAppContext.Current().GetRouteResult(url);
            RequestModel m = RequestModel.CreateRequestModel(result.ModelData, req);
            if (!result.IsMatching) {
                HtmlHelper.RenderView("404", m, resp.getWriter(), req.getServletContext());
                return;
            }
            // ModelBase model=result.
            ControllerBase cb = WdbAppContext.Current().getController(result.ControllerName);
            Class<?>[] args = new Class<?>[1];
            args[0] = ModelBase.class;

            Method control = cb.getClass().getMethod(result.ActionName, args);
            ActionResult ar = (ActionResult) control.invoke(cb, m);
            if (ar == null || ar.ViewName == null || ar.ViewName.equals("")) {
                HtmlHelper.RenderView("500", m, resp.getWriter(), req.getServletContext());
            } else {
                HtmlHelper.RenderView(ar.ViewName, ar.Model, resp.getWriter(), req.getServletContext());
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
            HtmlHelper.RenderView("500", RequestModel.CreateRequestModel(hm, req), resp.getWriter(), req.getServletContext());
        }

    }
}
