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
package Wrench.Db.Tests;

import WrenchDb.DAL.Entities.WdbApplication;
import WrenchDb.DAL.Helpers.HContext;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.xml.ws.spi.http.HttpContext;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author d.fontani
 */
public class DALTests {
    
    public DALTests() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    
     @Test
    public void InitHContext()
    {
        //relative path match WtrenchDb.Test folder. Config files are inside test folder
        HContext.Init("test/wdb_db.properties");
        assertTrue( HContext.Current().getProperties().size()>0);
       
    }
     
     
     @Test
     public void DoSelect()
     {
          InitHContext();
        List l= HContext
                .Current()
                .ExecuteSelectQuery(
                "SELECT application_id,application_name,application_hostname FROM wdb_application ORDER BY application_name ASC");   
     
     }
     
    @Test
    public void OrmInit()
    {
       InitHContext();
        WdbApplication app= new WdbApplication();
        
        app.setApplicationHostname("app.host.loc2");
        app.setApplicationName("testApp2"+(new Date()).toString());
  
         HContext.Current().SaveOrUpdate(app);
         
       // Session s= HContext.Current().SaveOrUpdate()
       WdbApplication appedit=  HContext.Current().<WdbApplication>Get(WdbApplication.class,app.getApplicationId());
        appedit.setApplicationHostname(app.getApplicationHostname()+"_MOD");
        appedit.setApplicationName(app.getApplicationName()+"_MOD");
        HContext.Current().SaveOrUpdate(appedit);
     
    }
    
     @Test
    public void QueryInit()
    {
         InitHContext();
         Session s=HContext.Current().getSessionFactory().openSession();
         WdbApplication app=(WdbApplication)s.createCriteria(WdbApplication.class).uniqueResult();
         s.close();
     
    }
    
    
}