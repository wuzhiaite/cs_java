package com.wuzhiaite.javaweb.base.multidatabase;

import java.lang.annotation.*;

/**
 * @author lpf
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String name() default "";

}
