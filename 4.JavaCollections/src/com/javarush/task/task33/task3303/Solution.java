package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/* 
Десериализация JSON объекта
*/

public class Solution
{
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException
    {
//        String jsonString;
//        Path path = Paths.get(fileName);
//        List<String> jsonList = Files.readAllLines(path);
//        jsonString = jsonList.toString();
//        ObjectMapper mapper = new ObjectMapper();
////        clazz.getClass();
//        T object = mapper.readValue(jsonString, clazz);
//        return object;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new FileReader(new File(fileName)), clazz);
    }

    public static void main(String[] args)
    {

    }
}
