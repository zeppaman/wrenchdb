/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WrenchDb.MVC.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Daniele Fontani
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MainApplication {
    
}