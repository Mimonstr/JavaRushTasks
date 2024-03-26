package com.javarush.task.task31.task3104;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Поиск скрытых файлов
*/

public class Solution extends SimpleFileVisitor<Path>
{
    public static void main(String[] args) throws IOException
    {
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        final Solution solution = new Solution();
        Files.walkFileTree(Paths.get("D:/"), options, 20, solution);

        List<String> result = solution.getArchived();
        System.out.println("All archived files:");
        for (String path : result)
        {
            System.out.println("\t" + path);
        }

        List<String> failed = solution.getFailed();
        System.out.println("All failed files:");
        for (String path : failed)
        {
            System.out.println("\t" + path);
        }
    }

    private List<String> archived = new ArrayList<>();
    private List<String> failed = new ArrayList<>();

    public List<String> getArchived()
    {
        return archived;
    }

    public List<String> getFailed()
    {
        return failed;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
    {
//        FileInputStream zipFile = new FileInputStream(String.valueOf(file));
//        ZipInputStream zip = new ZipInputStream(zipFile);
//        while(zip.available() != 0) archived.add(zip.getNextEntry().getName());
//        return super.visitFile(file, attrs);

        if (file.toString().toLowerCase().endsWith(".zip")
                || file.toString().toLowerCase().endsWith(".rar"))
        {
            archived.add(file.toString());
        }

        // Ваша логика для visitFile

        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
    {
        failed.add(file.toString());
        return FileVisitResult.SKIP_SUBTREE;
    }
}
