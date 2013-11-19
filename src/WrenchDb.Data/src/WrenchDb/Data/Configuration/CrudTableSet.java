/* 
 * Copyright (C) 2013 d.fontani
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