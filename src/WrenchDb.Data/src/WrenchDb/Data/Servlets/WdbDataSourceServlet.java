/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Data.Servlets;

import WrenchDb.Data.Configuration.CrudTableSet;
import WrenchDb.Data.Model.CrudTable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author d.fontani
 */
public class WdbDataSourceServlet 
extends HttpServlet {
    
   @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        try 
        {
            CrudTableSet cs= new CrudTableSet();
            CrudTable ct= cs.GetByName("");
            
        }
        catch(Exception err)
        {
        
        }
    }
}