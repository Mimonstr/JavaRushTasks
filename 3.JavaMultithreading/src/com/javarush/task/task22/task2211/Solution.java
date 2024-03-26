package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        String file1 = args[0];
        String file2 = args[1];
        Charset utf8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), windows1251));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)))
        {
            String s;
            while ((s = reader.readLine()) != null)
            {
                writer.write(s);
            }


        }
    }
}
