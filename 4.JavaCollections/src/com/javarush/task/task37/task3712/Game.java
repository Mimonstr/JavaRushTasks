package com.javarush.task.task37.task3712;

public abstract class Game
{
    public void run()
    {
        prepareForTheGame();
        playGame();
        congratulateWinner();
    }
    protected abstract void congratulateWinner();
    protected abstract void playGame();
    protected abstract void prepareForTheGame();
}
