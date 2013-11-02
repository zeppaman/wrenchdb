/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Data.Configuration;

import WrenchDb.Core.Configuration.ItemAppendingConfigurationContainer;
import WrenchDb.Data.Model.CrudTable;

/**
 *
 * @author d.fontani
 */
public class CrudTableSet  
extends ItemAppendingConfigurationContainer<CrudTable> {

    @Override
    public String getName() {
        return "CrudTableSet";
    }

    @Override
    public Class GetType() {
        return CrudTable.class;
    }
    
}