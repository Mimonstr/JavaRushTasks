package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution
{
//    public static void main(String[] args) throws IOException
//    {
//        if (args.length != 2)
//        {
//            System.out.println("Usage: java AddFileToZip fileName archivePath");
//            return;
//        }
//
//        String fileName = args[0];
//        String archivePath = args[1];
//
//        Path archive = Paths.get(archivePath);
//        Path tempDirectory = Files.createTempDirectory("temp");
//        Path tempFile = tempDirectory.resolve("temp.zip");
//
//        try (ZipOutputStream tempZipOutputStream = new ZipOutputStream(new FileOutputStream(tempFile.toFile()));
//             ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(archive.toFile())))
//        {
//
//            ZipEntry entry;
//            while ((entry = zipInputStream.getNextEntry()) != null)
//            {
//                tempZipOutputStream.putNextEntry(new ZipEntry(entry.getName()));
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = zipInputStream.read(buffer)) != -1)
//                {
//                    tempZipOutputStream.write(buffer, 0, bytesRead);
//                }
//                tempZipOutputStream.closeEntry();
//            }
//
//            // Добавляем новый файл в директорию 'new'
//            String newDirectoryName = "new/";
//            tempZipOutputStream.putNextEntry(new ZipEntry(newDirectoryName + fileName.substring(fileName.lastIndexOf('\\')+1)));
//            byte[] fileBytes = Files.readAllBytes(Paths.get(fileName));
//            tempZipOutputStream.write(fileBytes);
//            tempZipOutputStream.closeEntry();
//        }
//
//        // Заменяем старый архив новым
//        Files.move(tempFile, archive, StandardCopyOption.REPLACE_EXISTING);
//    }


     public static void main(String[] args) throws IOException
     {
        List<Content> entries = getContents(args[1]);

        FileOutputStream zipFile = new FileOutputStream(args[1]);
        ZipOutputStream zip = new ZipOutputStream(zipFile);

        //кладем в него  ZipEntry –«архивный объект»
        File file = new File(args[0]);
        zip.putNextEntry(new ZipEntry("new/" + file.getName()));

        //копируем файл «document-for-archive.txt» в архив под именем «document.txt»
        Files.copy(file.toPath(), zip);

        //копируем все остальные файлы
        for (Content content : entries)
        {
            if (!content.getFileName().equals("new/" + file.getName())) content.saveToZip(zip);
        }

        // закрываем архив
        zip.close();
    }

    private static List<Content> getContents(String arg) throws IOException
    {
        List<Content> entries = new ArrayList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(arg)))
        {
            ZipEntry currentEntry;
            byte[] buffer = new byte[1024];
            while ((currentEntry = zipInputStream.getNextEntry()) != null)
            {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                int length = 0;
                while ((length = zipInputStream.read(buffer)) > 0)
                {
                    outputStream.write(buffer, 0, length);
                }
                entries.add(new Content(currentEntry.getName(), outputStream.toByteArray()));
            }
        }
        return entries;
    }

    static class Content
    {
        String fileName;
        byte[] body;

        Content(String fileName, byte[] body)
        {
            this.fileName = fileName;
            this.body = body;
        }

        public String getFileName()
        {
            return fileName;
        }

        void saveToZip(ZipOutputStream zip) throws IOException
        {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zip.putNextEntry(zipEntry);
            zip.write(body);
            zip.closeEntry();
        }
    }
}
