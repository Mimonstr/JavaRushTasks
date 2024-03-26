package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

public class Solution
{
    public static void main(String[] args)
    {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

//    public static int getRectangleCount(byte[][] a)
//    {
//        int count = 0;
//        int rows = a.length;
//        int cols = a[0].length;
//
//        for (int i = 0; i < rows; i++)
//        {
//            for (int j = 0; j < cols; j++)
//            {
//                if (a[i][j] == 1)
//                {
//                    if (i < rows -1 && a[i+1][j] == 1) a[i][j] = 0;
//                    if (j < cols -1 && a[i][j+1] == 1) a[i][j] = 0;
//
//                    if (a[i][j] == 1)count++;
//
//                }
//            }
//        }
//        return count;
//    }

    public static int getRectangleCount(byte[][] a)
    {
        int count = 0;
        int rows = a.length;
        int cols = a[0].length;

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (a[i][j] == 1)
                {
                    count++;
                    dfs(a, i, j);
                }
            }
        }

        return count;
    }

    private static void dfs(byte[][] grid, int row, int col)
    {
        int rows = grid.length;
        int cols = grid[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0)
        {
            return;
        }

        grid[row][col] = 0; // Помечаем ячейку как посещенную (0)

        // Рекурсивный вызов для соседних ячеек
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }
}

