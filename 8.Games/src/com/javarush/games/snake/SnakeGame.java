package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import java.awt.event.KeyEvent;

public class SnakeGame extends Game
{
    public static final int HEIGHT = 15;
    public static final int WIDTH = 15;

    private final static int GOAL = 28;

    private int score;

    private boolean isGameStopped;

    private int turnDelay;

    private Snake snake;
    private Apple apple;

    @Override
    public void initialize()
    {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame()
    {
        snake = new Snake(WIDTH/2, HEIGHT/2);
        createNewApple();
        isGameStopped = false;
        score = 0;
        turnDelay = 300;
        setTurnTimer(turnDelay);
        setScore(score);
        drawScene();
    }

    private void drawScene()
    {
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                setCellValueEx(x, y, Color.DARKSEAGREEN, "");
            }
        }

        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int x)
    {
        snake.move(apple);
        if(!apple.isAlive)
        {
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
            createNewApple();
        }

        if(snake.isAlive == false)gameOver();
        if(snake.getLength() > GOAL)win();
        //В конце
        drawScene();
    }

    @Override
    public void onKeyPress(Key key)
    {

        switch (key)
        {
            case LEFT: snake.setDirection(Direction.LEFT);
                break;
            case RIGHT: snake.setDirection(Direction.RIGHT);
                break;
            case UP: snake.setDirection(Direction.UP);
                break;
            case DOWN: snake.setDirection(Direction.DOWN);
                break;
            case SPACE:
            {
                if(isGameStopped)createGame();
            }
                break;
        }
    }

    private void createNewApple()
    {
        Apple newApple;
        do
        {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            newApple = new Apple(x, y);
        } while (snake.checkCollision(newApple));
        apple = newApple;

    }

    private void gameOver()
    {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 100);
    }

    private void win()
    {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.FUCHSIA, 100);
    }
}
