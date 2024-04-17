package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake
{
    private List<GameObject> snakeParts = new ArrayList<>();

    private final static String HEAD_SIGN = "X";
    private final static String BODY_SIGN = ".";

//    private final static String HEAD_SIGN = "\uD83D\uDC3E";
//    private final static String BODY_SIGN = "\u26AB";

    public boolean isAlive = true;

    private Direction direction = Direction.LEFT;


    public Snake(int x, int y)
    {
        GameObject gameObject1 = new GameObject(x, y);
        GameObject gameObject2 = new GameObject(x+1, y);
        GameObject gameObject3 = new GameObject(x+2, y);

        snakeParts.add(gameObject1);
        snakeParts.add(gameObject2);
        snakeParts.add(gameObject3);
    }

    public void draw(Game game)
    {
        Color color = isAlive ? Color.BLACK : Color.RED;
        for (int i = 0; i < snakeParts.size(); i++)
        {
            GameObject part = snakeParts.get(i);
            String sign = (i != 0) ? BODY_SIGN : HEAD_SIGN;
            game.setCellValueEx(part.x, part.y, Color.NONE, sign, color, 75);
        }

    }

    public void setDirection(Direction direction)
    {
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT)
                && snakeParts.get(0).x == snakeParts.get(1).x)
        {
            return;
        }
        if ((this.direction == Direction.UP || this.direction == Direction.DOWN)
                && snakeParts.get(0).y == snakeParts.get(1).y)
        {
            return;
        }

        if (direction == Direction.UP && this.direction == Direction.DOWN)
        {
            return;
        } else if (direction == Direction.LEFT && this.direction == Direction.RIGHT)
        {
            return;
        } else if (direction == Direction.RIGHT && this.direction == Direction.LEFT)
        {
            return;
        } else if (direction == Direction.DOWN && this.direction == Direction.UP)
        {
            return;
        }
        this.direction = direction;
    }

    public void move(Apple apple)
    {
        GameObject head = createNewHead();
        if (checkCollision(head))
        {
            this.isAlive = false;

            return;
        }
        if(head.x == apple.x && head.y == apple.y)
        {
            apple.isAlive = false;
            snakeParts.add(0, head);
            return;
        }
        else if(head.x >= SnakeGame.WIDTH || head.y >= SnakeGame.HEIGHT
           || head.x < 0 || head.y < 0)
        {
            isAlive = false;
            return;
        }
        removeTail();
        snakeParts.add(0, head);

    }
    public GameObject createNewHead()
    {
        GameObject gameObject = null;
        GameObject head = snakeParts.get(0);
        switch (direction)
        {
            case LEFT: gameObject = new GameObject(head.x - 1, head.y);
            break;
            case DOWN: gameObject = new GameObject(head.x, head.y+1);
                break;
            case UP: gameObject = new GameObject(head.x, head.y-1);
                break;
            case RIGHT: gameObject = new GameObject(head.x + 1, head.y);
                break;
        }
        return gameObject;
    }
    public void removeTail()
    {
        snakeParts.remove(snakeParts.get(snakeParts.size()-1));
    }

    public boolean checkCollision(GameObject gameObject)
    {
        for(GameObject Object: snakeParts)
        {
            if(gameObject.x == Object.x && gameObject.y == Object.y) return true;
        }
        return false;
    }

    public int getLength()
    {
        return snakeParts.size();
    }
}
