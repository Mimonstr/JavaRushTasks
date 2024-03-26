package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.util.Date;
import java.util.List;

import static com.javarush.task.task27.task2712.statistic.event.EventType.COOKED_ORDER;

public class CookedOrderEventDataRow implements EventDataRow
{
    private Date currentDate = new Date();
    private String tabletName; // - имя планшета
    private String cookName; // - имя повара
    private int cookingTimeSeconds; // - время приготовления заказа в секундах
    private List<Dish> cookingDishes; // - список блюд для приготовления

    public CookedOrderEventDataRow(String tabletName, String cookName, int cookingTimeSeconds, List<Dish> cookingDishes)
    {
        this.tabletName = tabletName;
        this.cookName = cookName;
        this.cookingTimeSeconds = cookingTimeSeconds;
        this.cookingDishes = cookingDishes;
    }

    @Override
    public EventType getType()
    {
        return EventType.COOKED_ORDER;
    }

    @Override
    public Date getDate()
    {
        return this.currentDate;
    }

    @Override
    public int getTime()
    {
        return this.cookingTimeSeconds;
    }

    public String getCookName()
    {
        return cookName;
    }
}
