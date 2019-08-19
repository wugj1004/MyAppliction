package mydex.wugj.com.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/8/19
 * </br>
 * version:
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface MyAnnotation {
    String value() default "";
}
