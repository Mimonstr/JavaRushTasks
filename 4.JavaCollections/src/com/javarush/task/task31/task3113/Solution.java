package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution
{
//C:\Users\p-mis\Downloads\secretPasswords.txt
//C:\Users\p-mis\Downloads\
private static Path directory;
    public static void main(String[] args) throws IOException
    {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String dirName = console.readLine();
        directory = Paths.get(dirName);
        if(!Files.isDirectory(Paths.get(dirName)))
        {
            System.out.println(directory.toString() + " - не папка");
            return;
        }
        else
        {
            FolderInfoVisitor visitor = new FolderInfoVisitor();
            Files.walkFileTree(directory, visitor);

            System.out.println("Всего папок - " + visitor.getFolderCount());
            System.out.println("Всего файлов - " + visitor.getFileCount());
            System.out.println("Общий размер - " + visitor.getTotalSize());
        }

    }
    private static class FolderInfoVisitor extends SimpleFileVisitor<Path>
    {
        private int folderCount = 0;
        private int fileCount = 0;
        private long totalSize = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        {
            if (Files.isRegularFile(file))
            {
                fileCount++;
                totalSize += attrs.size();
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
        {
            if (Files.isDirectory(dir) && !dir.equals(directory))
            {
                folderCount++;
            }
            return FileVisitResult.CONTINUE;
        }

        public int getFolderCount()
        {
            return folderCount;
        }

        public int getFileCount()
        {
            return fileCount;
        }

        public long getTotalSize()
        {
            return totalSize;
        }
    }
}
