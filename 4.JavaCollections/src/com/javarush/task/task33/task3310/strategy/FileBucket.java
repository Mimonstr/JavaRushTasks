package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            path.toFile().deleteOnExit();

            Files.deleteIfExists(path);
            Files.createFile(path);
        }
        catch (IOException e) {}
    }

    public long getFileSize()
    {
        try
        {
            return Files.size(path);
        }
        catch (IOException e) {}
        return 0;
    }

    public void putEntry(Entry entry)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path)))
        {
            oos.writeObject(entry);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Entry getEntry()
    {
        if (getFileSize() > 0)
        {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path)))
            {
                return (Entry) ois.readObject();
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void remove()
    {
        try
        {
            Files.delete(path);
        }
        catch (IOException e) {}
    }
}
