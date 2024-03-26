package com.javarush.task.task38.task3810;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Revision
{
    //напиши свой код
    int revision();
    Date date();
    String comment() default "";
    Author[] authors() default {};
}
