package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args)
    {
        //запоминаем настоящий PrintStream в специальную переменную
        PrintStream consoleStream = System.out;

        //Создаем динамический массив
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out
        System.setOut(stream);

        //Вызываем функцию, которая ничего не знает о наших манипуляциях
        testString.printSomething();

        //Преобразовываем записанные в наш ByteArray данные в строку
        String result = outputStream.toString();
        StringBuilder sb = new StringBuilder(result);
        //Возвращаем все как было
        System.setOut(consoleStream);

        String[]mas = result.split(" ");
        Integer a = Integer.parseInt(mas[0]);
        Integer b = Integer.parseInt(mas[2]);
        char znak = mas[1].charAt(0);

        switch (znak)
        {
            case '+': sb.append(a+b);
            break;
            case '-': sb.append(a-b);
            break;
            case '*': sb.append(a*b);
                break;
        }
        System.out.print(sb.toString());

    }

    public static class TestString {
        public void printSomething() {
            System.out.print("3 + 6 = ");
        }
    }
}

