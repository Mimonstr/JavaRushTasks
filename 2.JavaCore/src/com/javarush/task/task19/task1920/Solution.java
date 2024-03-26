package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* 
Самый богатый
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
        double max = 0;
        for (String s: people.keySet())
        {
            if (people.get(s) > max) max = people.get(s);
        }
        for (String s: people.keySet())
        {
            if (people.get(s) == max) System.out.println(s);
        }


    }
}
