package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path>
{
    private String partOfName;
    private String partOfContent;
    private int minSize, maxSize;

    private List<Path> foundFiles = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
    {
        // check if file name contains search string
        if (partOfName != null && !file.getFileName().toString().contains(partOfName)) return FileVisitResult.CONTINUE;

        // read file content
        byte[] content = Files.readAllBytes(file);

        //check size of file
        if ((maxSize > 0 && content.length > maxSize) || (minSize > 0 && content.length < minSize)) return FileVisitResult.CONTINUE;

        // check contents of file
        if (partOfContent != null && !partOfContent.isEmpty())
        {
            String contentString = new String(content);
            if (!contentString.contains(partOfContent)) return FileVisitResult.CONTINUE;
        }

        // if all checks are passed, add file to result list
        foundFiles.add(file);
        return super.visitFile(file, attrs);
    }
//    private boolean checkFileCriteria(Path file, BasicFileAttributes attrs) throws IOException
//    {
//        return (partOfName == null || file.getFileName().toString().contains(partOfName))
//                &&
//                (partOfContent == null || new String(Files.readAllBytes(file)).contains(partOfContent))
//                &&
//                (attrs.size() >= minSize && attrs.size() <= maxSize);
//    }

    public void setPartOfName(String partOfName)
    {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent)
    {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize)
    {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles()
    {
        return this.foundFiles;
    }
}
