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
