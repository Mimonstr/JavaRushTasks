package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Сериализация под запретом
*/

public class Solution implements Serializable {
    public static class SubSolution extends Solution
    {
        private void readObject(ObjectInputStream outputStream) throws NotSerializableException
        {
            throw new NotSerializableException();
        }
        private void writeObject( ObjectOutputStream objectInputStream) throws NotSerializableException
        {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) throws IOException
    {
        FileOutputStream fileOutput = new FileOutputStream("your.file.name");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        SubSolution solution = new SubSolution();
        try
        {
            outputStream.writeObject(solution);
            throw new NotSerializableException();
        }
        catch (NotSerializableException e)
        {
            System.out.println(e.getMessage());
        }
        fileOutput.close();
        outputStream.close();
    }
}
