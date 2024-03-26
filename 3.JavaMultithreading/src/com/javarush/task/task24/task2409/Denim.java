package com.javarush.task.task24.task2409;

public class Denim extends AbstractJeans
{

    @Override
    public String getTM()
    {
        return "Denim";
    }

    public Denim(int id, int length, int size, double price)
    {
        super(id, length, size, price);
    }
}
