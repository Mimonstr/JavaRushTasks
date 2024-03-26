package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome
{
    private List<Horse> horses;
    public static Hippodrome game;

    public Hippodrome(List<Horse> horses)
    {
        this.horses = horses;
    }

    public List<Horse> getHorses()
    {
        return horses;
    }

    public void run()
    {
        for (int i = 1; i <= 50; i++)
        {
            move();
            print();
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        getWinner();

    }
    public void move()
    {
        for (Horse horse: horses) horse.move();
    }
    public void print()
    {
        for (Horse horse: horses) horse.print();
        for (int i = 0; i < 10; i++) System.out.println();

    }
    public Horse getWinner()
    {
        horses.sort((x1,x2)-> (int) (x2.distance - x1.distance));
        return horses.get(0);
    }
    public void printWinner()
    {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
    public static void main(String[] args)
    {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse-1",3,0));
        horses.add(new Horse("Horse-2",3,0));
        horses.add(new Horse("Horse-3",3,0));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
