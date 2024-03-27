package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.HashSet;
import java.util.Set;

public class Solution
{
    public static void main(String[] args)
    {
        String s = "Рвал дед лавр";
        System.out.println(isPalindromePermutation(s));
    }

    public static boolean isPalindromePermutation(String s)
    {
//        if (s == null || s.isEmpty())
//        {
//            return false;
//        }
//        // Приводим строку к нижнему регистру и удаляем все пробелы
//        s = s.toLowerCase().replaceAll(" ", "");
//        StringBuilder sb = new StringBuilder(s);
//        if(sb.reverse().toString().equals(s)) return true;
//        else return false;


        if (s == null || s.isEmpty()) {
            return false;
        }

        // Приводим строку к нижнему регистру и удаляем все пробелы
        s = s.toLowerCase().replaceAll("\\s", "");

        // Создаем множество для отслеживания четности встречаемости символов
        Set<Character> set = new HashSet<>();

        // Проходим по всем символам строки
        for (char c : s.toCharArray())
        {
            // Если символ уже есть в множестве, удаляем его
            if (set.contains(c))
            {
                set.remove(c);
            }
            else
            { // Иначе добавляем его в множество
                set.add(c);
            }
        }

        // Если количество символов с нечетной частотой не превышает одного, возвращаем true, иначе false
        return set.size() <= 1;
    }

}
