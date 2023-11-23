package org.bamboo.anno;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(OperatorComponentRegister.class)
@Inherited
@Documented
public @interface EnableOperator {

    String[] value() default {};
}