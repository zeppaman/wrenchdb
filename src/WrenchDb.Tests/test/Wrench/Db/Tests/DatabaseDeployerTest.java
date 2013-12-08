/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrench.Db.Tests;

import Wrench.Db.Tests.Helpers.Utils;
import WrenchDb.Deploy.Database.WdbDatabaseDeployer;
import org.junit.Test;

/**
 *
 * @author Daniele Fontani
 */
public class DatabaseDeployerTest {
    
    @Test
    public void CreateTable()
    {
        Utils.InitHContext();
        //Create Table
           WdbDatabaseDeployer db= WdbDatabaseDeployer.getDatabaseDeployer(24);
           db.logOnly=true;
         
           String tableName="test_table";
           String pkColumnName="test_table_id";
          
          db.createTable(tableName);
           //Add pkcolumn
          db.addColumn(tableName,pkColumnName,"SERIAL",null);
           //Add relation
          db.addPk(tableName,pkColumnName);
          String sql= db.getScript();
    }
    
    
  
}
