package com.javarush.task.task20.task2024;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/

public class Solution implements Serializable
{
    int node;
    List<Solution> edges = new LinkedList<>();

    public static void main(String[] args)
    {
        Solution graph = new Solution();

        // Добавьте какие-то данные в граф для демонстрации
        graph.node = 1;
        Solution child1 = new Solution();
        child1.node = 2;
        Solution child2 = new Solution();
        child2.node = 3;
        graph.edges.add(child1);
        graph.edges.add(child2);

        // Сериализация в файл
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\tupik\\Desktop\\monstr566\\Graf.gif")))
        {
            oos.writeObject(graph);
            System.out.println("Граф был успешно сериализован.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
