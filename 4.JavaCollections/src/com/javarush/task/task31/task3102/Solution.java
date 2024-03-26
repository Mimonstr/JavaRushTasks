package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Stack;

/* 
Находим все файлы
*/

public class Solution
{
    public static List<String> getFileTree(String root) throws IOException
    {
        List<String> filePaths = new ArrayList<>();
        Stack<File> stack = new Stack<>();

        File rootDir = new File(root);
        if (rootDir.isDirectory())
        {
            stack.push(rootDir);

            while (!stack.isEmpty())
            {
                File currentDir = stack.pop();
                File[] files = currentDir.listFiles();
                if (files != null)
                {
                    for (File file: files)
                    {
                        if (file.isFile())
                        {
                            filePaths.add(file.getAbsolutePath());
                        }
                        else if (file.isDirectory())
                        {
                            stack.push(file);
                        }
                    }
                }
            }
        }

        return filePaths;

    }

    public static void main(String[] args)
    {

    }
}
