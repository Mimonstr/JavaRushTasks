package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VeryComplexClass
{
    public void veryComplexMethod() throws Exception
    {
        //напишите тут ваш код
        new FileInputStream("");
    }

    public static void main(String[] args)
    {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        try
        {
            veryComplexClass.veryComplexMethod();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
