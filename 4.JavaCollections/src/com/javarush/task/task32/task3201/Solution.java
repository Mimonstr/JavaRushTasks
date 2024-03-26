package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Запись в существующий файл
*/

public class Solution
{
    public static void main(String... args)
    {
        String fileName = args[0];
        String text = args[2];

        try (RandomAccessFile file = new RandomAccessFile(fileName, "rw"))
        {
            long number = Long.parseLong(args[1]);
            long length = file.length();
            number = number > length ? length : number;
            file.seek(number);

            file.write(text.getBytes());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
