package org.rone.study.java.grammar.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * 自定义注解
 */
public class AnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException {
        AnnotationTest annotationTest = new AnnotationTest();
        Method method = annotationTest.getClass().getMethod("test", null);
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println(method.getAnnotation(MyAnnotation.class));
        }
    }

    @MyAnnotation
    public void test() {}
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {}
