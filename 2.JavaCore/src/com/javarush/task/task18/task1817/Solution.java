package com.javarush.task.task18.task1817;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* 
Пробелы
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        int Count_Symb = 0;
        int Count_probel = 0;
        try (FileReader reader = new FileReader(args[0]))
        {
            while (reader.ready())
            {
                char sym = (char)reader.read();
                if (sym == ' ')Count_probel++;
                Count_Symb++;
            }
        }
        if (Count_Symb != 0)
        {
            double result = (double) Count_probel / Count_Symb * 100;
            System.out.printf("%.2f", result);
        }

    }
}
