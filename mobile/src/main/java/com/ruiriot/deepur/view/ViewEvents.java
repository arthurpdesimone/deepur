package com.ruiriot.deepur.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ruiri on 09-May-17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)

public @interface ViewEvents {
    boolean clickable() default false;
}
