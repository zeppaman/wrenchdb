
import WrenchDb.MVC.Annotations.Action;
import WrenchDb.MVC.Annotations.Controller;
import WrenchDb.MVC.BaseClasses.ActionResult;
import WrenchDb.MVC.BaseClasses.ControllerBase;
import WrenchDb.MVC.BaseClasses.ModelBase;

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
        ActionResult r= new ActionResult();
        r.ViewName="default";
        r.Model= new ModelBase();
        r.Model.Properties.put("id", "MODIFICATO");
        r.Model.Properties.put("link","/TestNotation/default/Prova/123");
        return r;
                
    }
    
    @Action
    public ActionResult Prova(ModelBase model)
    {
        ActionResult r= new ActionResult();
        r.ViewName="ok";
        r.Model= new ModelBase();
        r.Model.Properties.put("id", "MODIFICATO");
        return r;
    }
}
