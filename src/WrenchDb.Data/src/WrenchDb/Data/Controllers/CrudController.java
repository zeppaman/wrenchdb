package WrenchDb.Data.Controllers;

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
import WrenchDb.Data.Configuration.CrudTableSet;
import WrenchDb.Data.Model.CrudTable;
import WrenchDb.MVC.Annotations.Action;
import WrenchDb.MVC.Annotations.Controller;
import WrenchDb.MVC.BaseClasses.ActionResult;
import WrenchDb.MVC.BaseClasses.ControllerBase;
import WrenchDb.MVC.BaseClasses.Model.ModelBase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */





@Controller ( 
         ControllerName="CrudController"
        )
public class CrudController extends ControllerBase {


   
    @Action
    public ActionResult ViewGrid(ModelBase model)
    {
         ActionResult r= new ActionResult("DataGrid","OneColumn",new ModelBase());
 
         
         CrudTableSet cs= new CrudTableSet();
         cs.LoadItems();
        r.Model= new ModelBase();
        CrudTable ct=cs.GetByName(model.get("id"));
        r.Model.Properties.put("ctable",ct );
        r.Model.Properties.put("title", ct.Title);
       
        return r;
    }
    
  
    
}

