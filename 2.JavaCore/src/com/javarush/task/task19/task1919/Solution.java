package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        Map<String, Double> people = new TreeMap<>();
        String file = args[0];
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String str;
            while ((str = reader.readLine()) != null)
            {
                String[] s = str.split(" ");
                if(people.containsKey(s[0]))
                {
                    people.replace(s[0], people.get(s[0])+Double.parseDouble(s[1]));
                }
                else people.put(s[0], Double.parseDouble(s[1]));
            }

        }
        for (String s: people.keySet())
        {
            System.out.println(s + " " + people.get(s));
        }
        //System.out.println(people);
    }
}
