package com.example.howtoquery.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PageableLimits {
    int maxSize() default Integer.MAX_VALUE;

    int minSize() default 0;
}