package com.javarush.task.task31.task3103;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* 
Своя реализация
*/

public class Solution
{
    public static byte[] readBytes(String fileName) throws IOException
    {
        return Files.readAllBytes(Paths.get(fileName));

    }

    public static List<String> readLines(String fileName) throws IOException
    {

        return Files.readAllLines(Paths.get(fileName));
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException
    {
        Files.write(Paths.get(fileName),bytes);
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException
    {
        Files.copy(Paths.get(resourceFileName), Paths.get(destinationFileName));
    }

    //C:\Users\p-mis\Downloads\input.txt
    public static void main(String[] args)
    {
        try
        {
            // Пример использования методов
            byte[] bytes = readBytes("C:\\Users\\p-mis\\Downloads\\input.txt");
            System.out.println("Read bytes: " + new String(bytes));

            List<String> lines = readLines("C:\\Users\\p-mis\\Downloads\\input.txt");
            System.out.println("Read lines: " + lines);

            writeBytes("C:\\Users\\p-mis\\Downloads\\input2.txt", "Hello, World!".getBytes());

            copy("C:\\Users\\p-mis\\Downloads\\input.txt", "C:\\Users\\p-mis\\Downloads\\input1.txt");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
