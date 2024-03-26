package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

//интерфейс-маркер
public interface EventDataRow
{
    EventType getType();
    Date getDate();// реализация которого вернет дату создания записи;
    int getTime(); // реализация которого вернет время или продолжительность.
}
