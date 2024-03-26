package com.javarush.task.task18.task1824;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        String file;
        while (true)
        {
            try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in)))
            {
               file = console.readLine();
               try (FileInputStream fileInputStream = new FileInputStream(file))
               {
               }
               catch (FileNotFoundException e)
               {
                   System.out.println(file);
                   break;
               }
            }
        }
    }
}
