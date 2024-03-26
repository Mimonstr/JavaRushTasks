package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }
    //Эту функцию и нужно написать
    private static Set<Integer> getRadix(String number)
    {
        Set<Integer> systems = new HashSet<>();
        for (int i = 2; i <= 36; i++)//Идем по СИ
        {
            try
            {
                //Переводим в каждую i-ую СИ
                String newNumber = Integer.toString(Integer.parseInt(number), i);
                StringBuilder sb = new StringBuilder(newNumber);
                if (sb.reverse().toString().equals(newNumber))//То добавляем в множество
                    systems.add(i);
            }
            catch (NumberFormatException e)
            {
                break;
            }
        }
        return systems;
    }
}