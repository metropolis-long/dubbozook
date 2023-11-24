package org.bamboo.plugins.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@Inherited
@Documented
public @interface Operator {
    String value();
}
