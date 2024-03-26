package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Контекстная реклама
*/

public class Solution
{
    public static TestString testString = new TestString();

    public static void main(String[] args)
    {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        testString.printSomething();
        String result = outputStream.toString();
        System.setOut(consoleStream);

        int count = 0;
        String[] masResult = result.split("\n");
        for (int i = 0; i < masResult.length; i++)
        {
            System.out.println(masResult[i]);
            count++;
            if (count == 2)
            {
                System.out.println("JavaRush - курсы Java онлайн");
                count = 0;
            }
        }
    }

    public static class TestString
    {
        public void printSomething()
        {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
