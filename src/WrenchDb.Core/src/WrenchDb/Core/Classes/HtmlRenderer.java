/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.Core.Classes;

import WrenchDb.Core.Helpers.WdbStringHelper;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author d.fontani
 */
public class HtmlRenderer {
    private StringBuilder sb= null;
    
    private Hashtable<String,String> _tempAttrs= new Hashtable<String,String>();
    private List<String> _tagsStack= new ArrayList<String>();
    
    public HtmlRenderer()
    {
        sb= new StringBuilder();
    }
    
    public HtmlRenderer(StringBuilder sb)
    {
        this.sb=sb;
    }
    
    
        public void AppendAttributeIfHasValue(String AttrName,String AttrValue)
        {
            if(!WdbStringHelper.isBlank(AttrValue))
            {
            _tempAttrs.put(AttrName, AttrValue);
            }
            
        }
        public void AppendAttribute(String AttrName,String AttrValue)
        {
            
            _tempAttrs.put(AttrName, (AttrValue!=null)? AttrValue: "");
            
        }
        public void RenderBeginTag(String TagName)
        {
            RenderBeginTag(TagName,false);
        }
        
        public void RenderBeginTag(String TagName,boolean endTag)
        {
            if(!endTag)
            {
                _tagsStack.add(TagName);
            }
            sb.append("<");
            sb.append(TagName);
            
            Enumeration<String> enumKey = _tempAttrs.keys();
            String key ;
            String val;
            
            while(enumKey.hasMoreElements()) 
            {
               key= enumKey.nextElement();
               val = _tempAttrs.get(key);
               sb.append(" ");
               sb.append(key);
               if(val!=null)
               {
                sb.append("=\"");
                sb.append(val);
                sb.append("\" ");
               }
            }
            if(endTag)
            {
                sb.append("/");
            }
             sb.append(">");
            _tempAttrs.clear();
        }
        
        public void RenderEndTag()
        {
            sb.append("</");
            sb.append(_tagsStack.get(_tagsStack.size()-1));
            sb.append(">");
            _tagsStack.remove(_tagsStack.size()-1);
        }
        
        public void WriteHtml(String Html)
        {
             sb.append(Html);
        }
        public String GetHtml()
        {
            return sb.toString();
        }

    public void Clear() {
       sb= new StringBuilder();
    }

   
 }
    
    

