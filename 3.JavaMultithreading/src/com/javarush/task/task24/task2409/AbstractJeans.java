package com.javarush.task.task24.task2409;

public abstract class AbstractJeans implements Jeans
{
    private int id;
    private int length;
    private int size;
    private double price;

    protected AbstractJeans(int id, int length, int size, double price) {
        this.id = id;
        this.length = length;
        this.size = size;
        this.price = price;
    }

    public abstract String getTM();

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", length=" + length +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
