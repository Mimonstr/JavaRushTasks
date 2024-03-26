package com.javarush.task.task27.task2712.ad;


import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();;
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager()
    {

    }
    public static StatisticAdvertisementManager getInstance()
    {
        if (instance == null)
        {
            instance = new StatisticAdvertisementManager();
        }
        return instance;
    }

    public List<Advertisement> getVideoSet(boolean isActive)
    {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : storage.list())
        {
            if (!isActive ^ advertisement.isActive())
            {
                result.add(advertisement);
            }
        }
        return result;
    }
}
