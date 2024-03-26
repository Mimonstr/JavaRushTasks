package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/* 
Проход по дереву файлов
*/

//public class Solution
//{
//    //C:\Users\p-mis\Downloads
//    //C:\Users\p-mis\Downloads\resultFileAbsolutePath.txt
//    public static void main(String[] args)
//    {
//        File path = new File(args[0]);//путь к директории
//        File resultFileAbsolutePath = new File(args[1]);// имя (полный путь) существующего файла, который будет содержать результат.
//
//        //System.out.println(resultFileAbsolutePath.getParent()+"\\allFilesContent.txt");
//        File allFilesContent = new File(resultFileAbsolutePath.getParent()+"\\allFilesContent.txt");
//        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
//
//        recursiveFileRename(path, allFilesContent);
//
//
//    }
//    private static void recursiveFileRename(File path, File outputFilePath)
//    {
//        // Получаем список файлов и поддиректорий в текущей директории
//        File[] files = path.listFiles();
//        // Проверяем, не является ли массив файлов пустым
//        if (files != null)
//        {
//            // Создаем объект для записи в файл
//            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, true)))
//            {
//                // Обходим каждый файл и поддиректорию
//                for (File file: files)
//                {
//                    // Если это файл и его длина в байтах НЕ больше 50
//                    if (file.isFile() && file.length() <= 50)
//                    {
//                        // Читаем содержимое файла и записываем в allFilesContent.txt
//                        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
//                        {
//                            String line;
//                            while ((line = reader.readLine()) != null)
//                            {
//                                writer.println(line);
//                            }
//                        }
//                        // Добавляем новую строку после каждого файла
//                        writer.println();
//                    }
//                    else if (file.isDirectory())
//                    {
//                        // Если это поддиректория, рекурсивно вызываем этот метод для нее
//                        recursiveFileRename(file, outputFilePath);
//                    }
//                }
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//}


public class Solution
{
    public static void main(String[] args)
    {
        String path = args[0];
        String resultFileAbsolutePath = args[1];
        try
        {
            File resultFile = new File(resultFileAbsolutePath);
            File dest = new File(resultFile.getParentFile() + "/allFilesContent.txt");
            if (FileUtils.isExist(dest))
            {
                FileUtils.deleteFile(dest);
            }
            FileUtils.renameFile(resultFile, dest);

            Map<String, byte[]> fileTree = getFileTree(path);
            try (FileOutputStream fileOutputStream = new FileOutputStream(dest))
            {
                for (byte[] bytes : fileTree.values())
                {
                    fileOutputStream.write(bytes);
                    fileOutputStream.write("\n".getBytes());
                }
            }
        } catch (IOException ignored)
        {
        }
    }

    public static Map<String, byte[]> getFileTree(String root) throws IOException
    {
        Map<String, byte[]> result = new TreeMap<>();

        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(Paths.get(root), options, 20, new GetFiles(result));

        return result;
    }

    private static class GetFiles extends SimpleFileVisitor<Path>
    {
        private Map<String, byte[]> result;

        public GetFiles(Map<String, byte[]> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException
        {
            File file = path.toFile();
            if (file.isFile())
            {
                if (file.length() <= 50)
                {
                    result.put(path.getFileName().toString(), Files.readAllBytes(path));
                }
            }
            return super.visitFile(path, basicFileAttributes);
        }
    }
}
