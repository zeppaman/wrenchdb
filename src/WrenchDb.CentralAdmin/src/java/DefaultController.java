
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
