/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Deploy.Database.Impl;

import WrenchDb.Deploy.Database.WdbDatabaseDeployer;

/**
 *
 * @author Daniele Fontani
 */

//WrenchDb.Deploy.Database.Impl.WdbDatabasePostgresDeployer
public class WdbDatabasePostgresDeployer extends WdbDatabaseDeployer {

    
    @Override
    public void createTable(String tablename) {
       String sql= String.format("CREATE TABLE %s ();",tablename);
       log(sql);
       execute(sql);
    }

    @Override
    public void addColumn(String tablename, String columnName, String dataType, String defaultValue) {
        //TODO: apply default value;
       String sql= String.format("ALTER TABLE %s ADD COLUMN %s %s ;",tablename,columnName,dataType);
       log(sql);
       execute(sql);
    }

    @Override
    public void addPk(String tablename, String columnName) {
       String sql= String.format("ALTER TABLE %s ADD PRIMARY KEY (%s); ",tablename,columnName);
       log(sql);
       execute(sql);
    }

    @Override
    public void addFk(String tablename, String columnName, String ftableName, String ftableColumn) {
       String fkName="fk_"+tablename+"_"+ftableName;//TODO: ensure unique name
       String sql= String.format("ALTER TABLE %s ADD CONSTRAINT %s FOREIGN KEY (%s) REFERENCES % (%) MATCH FULL;"
               ,tablename,fkName,columnName,ftableColumn,ftableColumn);
       log(sql);
       execute(sql);
    }
    
}
