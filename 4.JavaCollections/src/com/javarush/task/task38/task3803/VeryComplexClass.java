package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass
{
    public void methodThrowsClassCastException()
    {
        Object obj = "Hello"; // Попытаемся привести строку к типу Integer
        Integer number = (Integer) obj; // Здесь будет вызвано ClassCastException
    }

    public void methodThrowsNullPointerException()
    {
        String s = null;
        s.length();
    }

    public static void main(String[] args)
    {

    }
}
