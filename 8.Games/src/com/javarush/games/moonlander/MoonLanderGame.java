package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

import java.awt.event.KeyEvent;

public class MoonLanderGame extends Game
{
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;

    private GameObject platform;

    private Rocket rocket;
    private GameObject landscape;

    @Override
    public void initialize()
    {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    private void createGame()
    {
        setTurnTimer(50);
        createGameObjects();
        isUpPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
        drawScene();
    }

    private void drawScene()
    {
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                setCellColor(x, y, Color.ORANGE);
            }

        }

        rocket.draw(this);
        landscape.draw(this);
    }

    @Override
    public void onTurn(int step)
    {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color)
    {
        if(x < 0 || x > WIDTH-1 || y < 0 || y > HEIGHT-1)return;
        super.setCellColor(x, y, color);
    }
    private void createGameObjects()
    {
        rocket = new Rocket(WIDTH/2, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
    }

    @Override
    public void onKeyPress(Key key)
    {
        switch (key)
        {
            case LEFT:
            {
                isLeftPressed = true;
                isRightPressed = false;
            }
            break;
            case RIGHT:
            {
                isLeftPressed = false;
                isRightPressed = true;
            }
            break;
            case UP: isUpPressed = true;
            break;
        }
    }

    @Override
    public void onKeyReleased(Key key)
    {
        switch (key)
        {
            case UP: isUpPressed = false;
            break;
            case LEFT: isLeftPressed = false;
            break;
            case RIGHT: isRightPressed = false;
            break;
        }
    }

    //будет проверять пересечение координат ракеты и ландшафта.
    private void check()
    {
        if(rocket.isCollision(landscape) && !(rocket.isCollision(platform) && rocket.isStopped())) gameOver();
        if(rocket.isCollision(platform) && rocket.isStopped()) win();
    }

    private void win()
    {

    }
    private void gameOver()
    {

    }

}
