package com.wuzhiaite.javaweb.base.annonations;

import java.lang.annotation.*;

/**
 * @author lpf
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamciDb {
    String name() default "";

}
