/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Helpers;

/**
 *
 * @author d.fontani
 */
public class JSONRenderer {
    
    StringBuilder sb;
    private boolean HasPropety=false;
    public JSONRenderer()
    {
        sb=new StringBuilder();
    }
    public void StartObjectProperty(String key)
    {
           if(HasPropety)
            {
                  sb.append(",");
            }
        RenderPropertyName(key);
            sb.append("{");
            HasPropety=false;
    }
    
   public void EndObjectProperty()
    {
        sb.append("}");
        HasPropety=true;
    }
       
    public void StartArrayProperty(String key)
    {
       if(HasPropety)
        {
              sb.append(",");
        }
        RenderPropertyName(key);
        sb.append("[");
        HasPropety=false;
        
    }
    
     public void EndArrayProperty()
    {
        sb.append("]");
        HasPropety=true;
    }
      public void AppendPropertyIfValued(String key, Object Value)
    {
        AppendProperty( key,  Value,false);
    }
      public void AppendProperty(String key, Object Value)
    {
        AppendProperty( key,  Value,true);
    }
    public void AppendProperty(String key, Object Value,boolean allowNull)
    {
        if(!allowNull && Value==null) return;
        
        if(HasPropety)
        {
              sb.append(",");
        }
        RenderPropertyName(key);
        AppendValue(Value);
      
        HasPropety= true;
    }
    
    public  String GetJSON()
    {
       
        return "{"+sb.toString()+"}";
    }

    public void RenderPropertyName(String key) {
        if(!WdbStringHelper.isBlank(key))
        {
             sb.append("\"");
             sb.append(key);
             sb.append("\":");
        }
    }

    public void AppendValue(Object Value) {
         sb.append( JSONHelper.ConvertBaseType(Value));
    }
}
