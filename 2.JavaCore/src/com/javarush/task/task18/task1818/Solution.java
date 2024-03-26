package com.javarush.task.task18.task1818;

import java.io.*;

/* 
Два в одном
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        String file1,file2,file3;
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in)))
        {
            file1 = console.readLine();
            file2 = console.readLine();
            file3 = console.readLine();
        }
        try (BufferedReader reader2 = new BufferedReader(new FileReader(file2));
             BufferedReader reader3 = new BufferedReader(new FileReader(file3));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file1,true)))
        {
            String s;
            while ((s = reader2.readLine()) != null) writer.write(s);
            while ((s = reader3.readLine()) != null) writer.write(s);
        }

    }
}
