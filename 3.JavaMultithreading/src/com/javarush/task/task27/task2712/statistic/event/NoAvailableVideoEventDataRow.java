package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

public class NoAvailableVideoEventDataRow implements EventDataRow
{
    private Date currentDate = new Date();
    private int totalDuration; // - время приготовления заказа в секундах

    public NoAvailableVideoEventDataRow(int totalDuration)
    {
        this.totalDuration = totalDuration;
    }

    @Override
    public EventType getType()
    {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public Date getDate()
    {
        return this.currentDate;
    }

    @Override
    public int getTime()
    {
        return totalDuration;
    }
}
