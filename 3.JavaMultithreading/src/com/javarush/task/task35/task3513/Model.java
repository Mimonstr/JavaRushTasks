package com.javarush.task.task35.task3513;

import java.util.*;

public class Model
{
    private static final int FIELD_WIDTH = 4; //ширина игрового поля.
    int score;
    int maxTile;
    private Tile[][] gameTiles;

    private boolean isSaveNeeded = true;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();


    public Model()
    {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        this.score = 0;
        this.maxTile = 0;
        resetGameTiles();
    }

    public Tile[][] getGameTiles()
    {
        return gameTiles;
    }

    private int getEmptyTilesCount()
    {
        return getEmptyTiles().size();
    }

    private boolean isFull()
    {
        return getEmptyTilesCount() == 0;
    }
    boolean canMove()
    {
        if (!isFull())
        {
            return true;
        }

        for (int x = 0; x < FIELD_WIDTH; x++) {
            for (int y = 0; y < FIELD_WIDTH; y++) {
                Tile t = gameTiles[x][y];
                if ((x < FIELD_WIDTH - 1 && t.value == gameTiles[x + 1][y].value)
                        || ((y < FIELD_WIDTH - 1) && t.value == gameTiles[x][y + 1].value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addTile()
    {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty())
        {
            int randomIndex = (int) (Math.random() * emptyTiles.size());
            Tile randomTile = emptyTiles.get(randomIndex);
            randomTile.value = (Math.random() < 0.9 ? 2 : 4);
        }
    }
    private List getEmptyTiles()
    {
        List<Tile> emptyCells = new ArrayList<>();

        for (int x = 0; x < FIELD_WIDTH; x++)
        {
            for(int y = 0; y < FIELD_WIDTH; y++)
            {
                if(gameTiles[x][y].isEmpty())
                {
                    //Добавляем в список
                    emptyCells.add(gameTiles[x][y]);
                }
            }
        }
        return emptyCells;
    }
    public void resetGameTiles()
    {
        for (int x = 0; x < FIELD_WIDTH; x++)
        {
            for(int y = 0; y < FIELD_WIDTH; y++)
            {
                gameTiles[x][y] = new Tile();
            }
        }
        addTile();
        addTile();
    }
    /**
     Сжатие плиток влево, чтобы все пустые плитки были справа
     **/
//    private boolean compressTiles(Tile[] tiles)
//    {
//        boolean result = false;
//        int insertIndex = 0;
//        for (Tile tile : tiles)
//        {
//            if (!tile.isEmpty())
//            {
//                tiles[insertIndex++] = tile;
//            }
//        }
//        for (int i = insertIndex; i < tiles.length; i++)
//        {
//            tiles[i] = new Tile();
//            result = true;
//        }
//        return result;
//    }

    private boolean compressTiles(Tile[] tiles)
    {
        int insertPosition = 0;
        boolean result = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (!tiles[i].isEmpty()) {
                if (i != insertPosition) {
                    tiles[insertPosition] = tiles[i];
                    tiles[i] = new Tile();
                    result = true;
                }
                insertPosition++;
            }
        }
        return result;
    }

    /**
     Слияние плиток одного номинала
     */
    private boolean mergeTiles(Tile[] tiles)
    {
        boolean changes = false;
        for (int i = 0; i < tiles.length - 1; i++)
        {
            if (!tiles[i].isEmpty() && tiles[i].value == tiles[i + 1].value)
            {
                int newValue = tiles[i].value * 2;
                tiles[i].value = newValue;
                tiles[i + 1].value = 0;
                changes = true;
                // Проверка на максимальное значение плитки
                if (newValue > maxTile)
                {
                    maxTile = newValue;
                }

                // Увеличение счета
                score += newValue;
            }
        }

        // Сжатие плиток после слияния
        compressTiles(tiles);
        return changes;
    }

    /**
     * для каждой строки массива gameTiles
     * вызывать методы compressTiles и mergeTiles
     * и добавлять одну плитку с помощью метода addTile
     * в том случае, если это необходимо.
     */
    public void left()
    {
        if (isSaveNeeded) 
        {
            saveState(gameTiles);
        }
        boolean moveFlag = false;
        for (int i = 0; i < FIELD_WIDTH; i++)
        {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
            {
                moveFlag = true;
            }
        }
        if (moveFlag)
        {
            addTile();
        }   
    }
    private Tile[][] rotateClockwise(Tile[][] tiles)//Поворот вправо
    {
        final int N = tiles.length;
        Tile[][] result = new Tile[N][N];
        for (int r = 0; r < N; r++)
        {
            for (int c = 0; c < N; c++)
            {
                result[c][N - 1 - r] = tiles[r][c];
            }
        }
        return result;
    }
    public void up()
    {
        saveState(gameTiles);
        gameTiles = rotateClockwise(gameTiles);
        gameTiles = rotateClockwise(gameTiles);
        gameTiles = rotateClockwise(gameTiles);
        left();
        gameTiles = rotateClockwise(gameTiles);
    }
    public void down()
    {
        saveState(gameTiles);
    gameTiles = rotateClockwise(gameTiles);
    left();
    gameTiles = rotateClockwise(gameTiles);
    gameTiles = rotateClockwise(gameTiles);
    gameTiles = rotateClockwise(gameTiles);
    }
    public void right()
    {
        saveState(gameTiles);
        gameTiles = rotateClockwise(gameTiles);
        gameTiles = rotateClockwise(gameTiles);
        left();
        gameTiles = rotateClockwise(gameTiles);
        gameTiles = rotateClockwise(gameTiles);
    }

    private void saveState(Tile[][] tiles)
    {
        Tile[][] tempTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++)
        {
            for (int j = 0; j < FIELD_WIDTH; j++)
            {
                tempTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(tempTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback()
    {
        if (!previousStates.isEmpty() && !previousScores.isEmpty())
        {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }
    void randomMove()
    {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n)
        {
            case 0:
                left();
                break;
            case 1:
                up();
                break;
            case 2:
                down();
                break;
            case 3:
                right();
                break;
        }
    }

    private MoveEfficiency getMoveEfficiency(Move move)
    {
        MoveEfficiency moveEfficiency = new MoveEfficiency(-1, 0, move);
        move.move();
        if (hasBoardChanged())
        {
            moveEfficiency = new MoveEfficiency(getEmptyTilesCount(), score, move);
        }
        rollback();
        return moveEfficiency;
    }
    private boolean hasBoardChanged()
    {
        for (int i = 0; i < FIELD_WIDTH; i++)
        {
            for (int j = 0; j < FIELD_WIDTH; j++)
            {
                if (gameTiles[i][j].value != previousStates.peek()[i][j].value)
                {
                    return true;
                }
            }
        }
        return false;
    }

    void autoMove()
    {
        PriorityQueue<MoveEfficiency> moveEfficiencies = new PriorityQueue<>(4, Collections.reverseOrder());

        moveEfficiencies.offer(getMoveEfficiency(this::left));
        moveEfficiencies.offer(getMoveEfficiency(this::up));
        moveEfficiencies.offer(getMoveEfficiency(this::right));
        moveEfficiencies.offer(getMoveEfficiency(this::down));

        moveEfficiencies.peek().getMove().move();
    }
}
