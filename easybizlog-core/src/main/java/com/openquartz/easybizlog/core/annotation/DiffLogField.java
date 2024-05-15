package com.openquartz.easybizlog.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DiffLogField {

    String name();

    String function() default "";

    //   String dateFormat() default "";
}
