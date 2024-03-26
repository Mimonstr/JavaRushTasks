package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

//хранилище рекламных роликов
public class AdvertisementStorage
{
    private static AdvertisementStorage instance;

    private final List<Advertisement> videos = new ArrayList<>();

    // Приватный конструктор, чтобы предотвратить создание экземпляров извне
    private AdvertisementStorage()
    {
        // Инициализация хранилища
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
    }

    // Метод для получения экземпляра класса
    public static AdvertisementStorage getInstance()
    {
        if (instance == null)
        {
            // Создание экземпляра, если он ещё не создан
            instance = new AdvertisementStorage();
        }
        return instance;
    }

    //вернет список всех существующих доступных видео
    public List<Advertisement> list()
    {
        return videos;
    }
    //добавит новое видео в список videos
    public void add(Advertisement advertisement)
    {
        videos.add(advertisement);
    }

}
