package com.javarush.task.task24.task2409;

public class Levis extends AbstractJeans
{
    @Override
    public String getTM()
    {
        return "Levis";
    }

    public Levis(int id, int length, int size, double price)
    {
        super(id, length, size, price);
    }
}
