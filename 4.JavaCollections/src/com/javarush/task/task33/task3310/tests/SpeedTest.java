package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SpeedTest
{
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids)
    {
        long time_start = System.currentTimeMillis();
        for (String str : strings)
        {
            ids.add(shortener.getId(str));
        }
        long time_end = System.currentTimeMillis();
        return time_end - time_start;
    }
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
    {
        long time_start = System.currentTimeMillis();
        for (Long id : ids)
        {
            strings.add(shortener.getString(id));
        }
        long time_end = System.currentTimeMillis();
        return time_end - time_start;
    }

    @Test
    public void testHashMapStorage()
    {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++)
        {
            origStrings.add(Helper.generateRandomString());
        }
        long timeHashMap = getTimeToGetIds(shortener1, origStrings, new HashSet<>());
        long timeHashBiMap = getTimeToGetIds(shortener2, origStrings, new HashSet<>());

        //Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 больше, чем для shortener2.
        Assert.assertTrue(timeHashMap > timeHashBiMap);

        //Получать время получения строк (вызывать метод getTimeToGetStrings для shortener1 и shortener2).
        Set<Long> ids = new HashSet<>();
        for (String str : origStrings)
        {
            ids.add(shortener1.getId(str));
        }

        long timeForStrings1 = getTimeToGetStrings(shortener1, ids, new HashSet<>());
        long timeForStrings2 = getTimeToGetStrings(shortener2, ids, new HashSet<>());

        assertEquals(timeForStrings1, timeForStrings2, 30);
    }
}
