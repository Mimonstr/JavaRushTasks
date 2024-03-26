package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution
{
    public static List<String> words = new ArrayList<String>();

    static
    {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException
    {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new FileReader(console.readLine())))
        {
            String s;
            int flag = 0;
            while ((s = reader.readLine()) != null)
            {
                String[] word = s.split(" ");
                for (String str: word)
                {
                    if (words.contains(str)) flag++;
                }
                if (flag == 2) System.out.println(s);
                flag = 0;
            }
        }

    }
}
