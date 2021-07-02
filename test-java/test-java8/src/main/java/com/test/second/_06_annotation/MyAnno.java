package com.test.second._06_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE_PARAMETER;

@Target({ TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno{

}