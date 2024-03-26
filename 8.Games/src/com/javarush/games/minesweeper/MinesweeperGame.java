package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private int score;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private int countClosedTiles = SIDE*SIDE;
    private  boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame()
    {
        for (int y = 0; y < SIDE; y++)
        {
            for (int x = 0; x < SIDE; x++)
            {
                setCellValue(x, y, "");
            }
        }
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }
    private void countMineNeighbors()
    {
        for (int y = 0; y < SIDE; y++)
        {
            for (int x = 0; x < SIDE; x++)
            {
                if(!gameField[x][y].isMine)
                for(GameObject gameObject: getNeighbors(gameField[x][y]))
                {
                    if(gameObject.isMine)gameField[x][y].countMineNeighbors++;
                }
            }
        }
    }
    private void openTile(int x, int y)
    {

        GameObject gameObject = gameField[y][x];
        if(gameObject.isOpen || gameObject.isFlag || isGameStopped)
        {

        }
        else
        {
            gameObject.isOpen = true;
            countClosedTiles--;
            setCellColor(x, y, Color.GREEN);
            if (gameObject.isMine)
            {
                setCellValueEx(gameObject.x, gameObject.y, Color.RED, MINE);
                gameOver();
            }
            else if (gameObject.countMineNeighbors == 0)
            {
                setCellValue(gameObject.x, gameObject.y, "");
                List<GameObject> neighbors = getNeighbors(gameObject);
                for (GameObject neighbor : neighbors)
                {
                    if (!neighbor.isOpen)
                    {
                        openTile(neighbor.x, neighbor.y);
                    }
                }
            }
            else
            {
                setCellNumber(x, y, gameObject.countMineNeighbors);
                score = score + 5;
            }
        }
        if ( (countClosedTiles == countMinesOnField) && (!gameObject.isMine))
        {
            win();
        }
        setScore(score);
    }

    private void markTile(int x, int y)
    {
        if(!isGameStopped)
        {
            GameObject gameObject = gameField[x][y];
            if (((countFlags == 0) && (!gameObject.isFlag)) || gameObject.isOpen)
            {
                // Ничего не делаем
            }
            else
            {
                if (!gameObject.isFlag)
                {
                    gameObject.isFlag = true;
                    countFlags--;
                    setCellValue(gameObject.x, gameObject.y, FLAG);
                    setCellColor(gameObject.x, gameObject.y, Color.YELLOW);
                }
                else
                {
                    gameObject.isFlag = false;
                    countFlags++;
                    setCellValue(gameObject.x, gameObject.y, "");
                    setCellColor(gameObject.x, gameObject.y, Color.ORANGE);
                }

            }
        }
    }
    private void gameOver()
    {
        isGameStopped = true;
        showMessageDialog(Color.RED, "Вы проиграли!))", Color.RED, 5);
    }
    private void win()
    {
        isGameStopped = true;
        showMessageDialog(Color.RED, "Вы выиграли!))", Color.RED, 5);
    }
    private void restart()
    {
        isGameStopped = false;
        countClosedTiles = SIDE*SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(score);
        createGame();
    }
    @Override
    public void onMouseLeftClick(int x, int y)
    {
        if (isGameStopped) {
            restart();
            return;
        }
        openTile(x,y);
    }

    @Override
    public void onMouseRightClick(int x, int y)
    {
        markTile(x,y);
    }
}