package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Перевертыши
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new FileReader(console.readLine())))
        {
            String str;
            while ((str=reader.readLine()) != null)
            {
                StringBuilder sb = new StringBuilder(str);
                System.out.println(sb.reverse());
            }
        }
    }
}
