package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.security.SecureRandom;
import java.util.*;

import static com.javarush.task.task33.task3310.Helper.printMessage;

public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
        Set<Long> ids = new HashSet<>(strings.size());
        for (String str: strings)
        {
            ids.add(shortener.getId(str));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
    {
        Set<String> strings = new HashSet<>(keys.size());
        for (Long key: keys)
        {
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        //Выводить имя класса стратегии. Имя не должно включать имя пакета.
        // Выводим имя класса стратегии
        String strategyName = strategy.getClass().getSimpleName();
        Helper.printMessage(strategyName);
        //Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber.
        Set<String> strings = new HashSet<>((int)elementsNumber);
        for(int i = 0; i < elementsNumber; i++)
        {
            strings.add(Helper.generateRandomString());
        }
        //Создавать объект типа Shortener, используя переданную стратегию.
        Shortener shortener = new Shortener(strategy);
        // Замеряем время работы метода getIds
        Date start = new Date();
        Set<Long> ids = getIds(shortener,strings);
        Date end = new Date();

        // Вычисляем время выполнения в миллисекундах
        long timeElapsed = end.getTime() - start.getTime();

        // Выводим время выполнения
        printMessage("Time elapsed for " + strategyName + ": " + timeElapsed + " milliseconds");


        //Замерять и выводить время необходимое для отработки метода getStrings
        // для заданной стратегии и полученного в предыдущем пункте множества идентификаторов.
        Date start1 = new Date();
        Set<String> stringSet = getStrings(shortener, ids);
        Date end1 = new Date();

        // Вычисляем время выполнения в миллисекундах
        long timeElapsed1 = end1.getTime() - start1.getTime();

        // Выводим время выполнения
        printMessage("Time elapsed for " + strategyName + ": " + timeElapsed1 + " milliseconds");

        if(stringSet.containsAll(strings))
        {
            printMessage("Тест пройден.");
        }
        else
        {
            printMessage("Тест не пройден.");
        }

    }
    public static void main(String[] args)
    {
        //testStrategy(new HashMapStorageStrategy(),10000);
        //Map<String, String> map = new HashMap<>();

        long elementsNumber = 1000;

        testStrategy(new HashMapStorageStrategy(), elementsNumber);

        testStrategy(new FileStorageStrategy(), elementsNumber);

        testStrategy(new OurHashBiMapStorageStrategy(), elementsNumber);

        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);


    }
}
