package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        if (args.length == 0)
        {
            System.out.println("Usage: java Main <file>");
            return;
        }

        String fileName = args[0];

        TreeSet<Character> letters = new TreeSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                for (char ch : line.toLowerCase().toCharArray())
                {
                    if (Character.isLetter(ch))
                    {
                        letters.add(ch);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int count = 0;
        for (char ch : letters)
        {
            if (count >= 5)
            {
                break;
            }
            System.out.print(ch);
            count++;
        }
    }
}
