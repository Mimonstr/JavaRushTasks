package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String>
{
    private final int decimalNumber;
    public BinaryRepresentationTask(int i)
    {
        this.decimalNumber = i;
    }

    @Override
    protected String compute()
    {
        int a = decimalNumber % 2;
        int b = decimalNumber / 2;
        if (b > 0)
        {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            return task.join() + a;
        }
        return String.valueOf(a);

    }
}
