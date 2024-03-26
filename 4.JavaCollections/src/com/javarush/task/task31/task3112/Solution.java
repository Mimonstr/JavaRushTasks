package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution
{

    public static void main(String[] args) throws IOException
    {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C:\\Users\\p-mis\\Downloads"));

        for (String line : Files.readAllLines(passwords))
        {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException
    {
//        URL url = new URL(urlString);
//        InputStream inputStream = url.openStream();
//        Path tempFile = Files.createTempFile("temp-",".tmp");
//        if (Files.copy(inputStream, tempFile)>0)
//        {
//            Files.move(tempFile, downloadDirectory);
//        }
//        return tempFile;


                ///****WORK****///
//        // Создаем объект URL из строки
//        URL url = new URL(urlString);
//
//        // Создаем временную директорию
//        Path tempDirectory = Files.createTempDirectory("tempDownload");
//
//        // Имя файла из URL
//        String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
//
//        // Создаем временный путь
//        Path tempFile = tempDirectory.resolve(fileName);
//
//        try
//        {
//            // Открываем поток для чтения из URL и записи во временный файл
//            Files.copy(url.openStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
//        }
//        catch (IOException e)
//        {
//            // Обработка ошибок при загрузке
//            System.err.println("Failed to download file: " + e.getMessage());
//        }
//
//        // Перемещаем файл в пользовательскую директорию
//        Path destination = downloadDirectory.resolve(fileName);
//        Files.move(tempFile, destination, StandardCopyOption.REPLACE_EXISTING);
//
//        return destination;


        //String fileName = urlString.split("/")[urlString.split("/").length - 1];
        String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
        Path downloadPath = downloadDirectory.resolve(fileName);

        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();

        Path tempFile = Files.createTempFile(null, null);
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

        Files.move(tempFile, downloadPath);
        return downloadPath;
    }
}
