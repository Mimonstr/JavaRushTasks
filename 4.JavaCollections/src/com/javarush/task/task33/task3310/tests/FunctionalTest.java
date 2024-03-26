package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

public class FunctionalTest
{

    public void testStorage(Shortener shortener)
    {
        String text1 = "Hello";
        String text2 = "World";
        String text3 = "Hello";

        // Получаем и сохраняем идентификаторы для всех трех строк
        Long id1 = shortener.getId(text1);
        Long id2 = shortener.getId(text2);
        Long id3 = shortener.getId(text3);

        // Проверяем, что идентификатор для второй строки не равен идентификаторам для первой и третьей строк
        assertNotEquals(id2, id1);
        assertNotEquals(id2, id3);

        assertEquals(id1, id3);

        // Получаем строки по идентификаторам
        String obtainedText1 = shortener.getString(id1);
        String obtainedText2 = shortener.getString(id2);
        String obtainedText3 = shortener.getString(id3);

        assertEquals(obtainedText1, text1);
        assertEquals(obtainedText2, text2);
        assertEquals(obtainedText3, text3);
    }

    @Test
    public void testHashMapStorageStrategy()
    {
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(hashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy()
    {
        OurHashMapStorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(ourHashMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy()
    {
        FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(fileStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy()
    {
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(hashBiMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy()
    {
        DualHashBidiMapStorageStrategy dualHashBidiMapStorageStrategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(dualHashBidiMapStorageStrategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy()
    {
        OurHashBiMapStorageStrategy ourHashBiMapStorageStrategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(ourHashBiMapStorageStrategy);
        testStorage(shortener);
    }
}
