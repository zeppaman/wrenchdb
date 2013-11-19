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

import WrenchDb.Data.Enums.SqlComparators;
import WrenchDb.Data.Helpers.SelectBuilder;
import org.junit.Test;

/**
 *
 * @author d.fontani
 */
public class QueryTest {
    
    @Test
    public void TestSelectBuilder()
    {
        SelectBuilder s= new SelectBuilder();
                s.Field("PROVA")
                .Sum("PROVA1","pROVA2")
                .Count("PROVA1","pROVA2")
                .From("PROVA_TABLE")
                .AndWhere("PROVA", SqlComparators.EQUAL, "23")
                .GroupBy("PROVA")
                .OrderBy("PROVA");
        String query =s.toString();
    }
}
