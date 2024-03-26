package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Длинные слова
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
//        try (FileReader reader = new FileReader(args[0]);
//            FileWriter writer = new FileWriter(args[1]))
//            {
//                String str;
//                int length = 0;
//                while (reader.ready())
//                {
//                    while ()
//                    //int sym = reader.read();
//
//                }
//
//            }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            FileWriter writer = new FileWriter(args[1]))
        {
            String str;
            List<String> words = new ArrayList<>();
            while ((str = reader.readLine()) != null)
            {
                String[] masStr = str.split(" ");
                for (int i = 0; i < masStr.length; i++)
                {
                    if (masStr[i].length() > 6)
                    {
                       words.add(masStr[i]);
                    }
                }
            }
            for (int i = 0 ; i < words.size(); i++)
            {
                if (i != words.size()-1)
                {
                    writer.write(words.get(i) + ",");
                }
                else writer.write(words.get(i));
            }
        }


    }
}
