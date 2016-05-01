package code.controller.shared;

import java.lang.annotation.*;

/**
 * Created by PC-Alyaksei on 24.04.2016.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorize {
    String[] value() default {};
}
