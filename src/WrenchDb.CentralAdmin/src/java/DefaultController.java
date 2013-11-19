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
         ControllerName="DefaultController"
        )
public class DefaultController extends ControllerBase {

   
//    @Override
//    public   String getName()
//    {
//        return "DeafultController";
//    }
   
   
    @Action
    public ActionResult Index(ModelBase model)
    {
        ActionResult r= new ActionResult("default","OneColumn",new ModelBase());
        
        r.Model= new ModelBase();
        r.Model.Properties.put("id", "MODIFICATO");
        r.Model.Properties.put("link","/TestNotation/default/Prova/123");
        
        return r;
                
    }
    
    @Action
    public ActionResult Prova(ModelBase model)
    {
         ActionResult r= new ActionResult("ok","OneColumn",new ModelBase());
 
        r.Model= new ModelBase();
        r.Model.Properties.put("id", "MODIFICATO");
        return r;
    }
    
    
   
    
     
}
