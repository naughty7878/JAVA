package com.test.second._06_annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;


public class AnnotationTest {
    
    // 重复注解与类型注解

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public  void show(@MyAnnotation("abc") String str) {

    }



    @Test
    public void test1() throws NoSuchMethodException {
        Class<AnnotationTest> clazz = AnnotationTest.class;
//        clazz.getDeclaredMethod("show");
        Method[] declaredMethods = clazz.getDeclaredMethods();
//        Method showMethod = clazz.getDeclaredMethod("show");
        Method showMethod = declaredMethods[0];

        MyAnnotation[] myAnnotations = showMethod.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : myAnnotations) {
            System.out.println("myAnnotation.value() = " + myAnnotation.value());
        }

        Annotation[][] parameterAnnotations = showMethod.getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                System.out.println("annotation.getClass() = " + annotation.getClass());
                System.out.println("annotation.annotationType() = " + annotation.annotationType());
                if (annotation instanceof MyAnnotation ) {
                    MyAnnotation myAnnotation = (MyAnnotation) annotation;
                    System.out.println("myAnnotation.value() = " + myAnnotation.value());
                }

            }
        }
    }
}
