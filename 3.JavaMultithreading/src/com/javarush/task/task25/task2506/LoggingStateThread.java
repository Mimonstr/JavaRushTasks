package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread
{
    private Thread target;
    public LoggingStateThread(Thread target)
    {
        this.target = target;

    }
    @Override
    public void run()
    {
        State currentState = target.getState();
        System.out.println(currentState);

        State newState;
        do
        {
            if ((newState = target.getState()) != currentState)
            {
                currentState = newState;
                System.out.println(newState);
            }
        }
        while (!currentState.equals(State.TERMINATED));
    }
}
