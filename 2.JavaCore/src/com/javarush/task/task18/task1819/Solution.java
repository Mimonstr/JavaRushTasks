package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        String file1,file2;
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in)))
        {
            file1 = console.readLine();
            file2 = console.readLine();
        }
        String s;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file1)))
        {
            while ((s = reader1.readLine()) != null) sb.append(s);//Считали все из 1-ого файла
            //Считываем из второго и записываем в первый
            while ((s = reader2.readLine()) != null) writer.write(s);
            writer.write(sb.toString());
        }

    }
}
