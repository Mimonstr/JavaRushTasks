package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(getTokens("level22.lesson13.task01", ".")[0]);
    }

    public static String[] getTokens(String query, String delimiter)
    {
        //String[] mas = query.split("\\.");
        StringTokenizer mas = new StringTokenizer(query, delimiter);
        List<String> list = new ArrayList<>();
        while (mas.hasMoreTokens())
        {
            list.add(mas.nextToken());
        }
        return list.toArray(new String[list.size()]);
    }
}
