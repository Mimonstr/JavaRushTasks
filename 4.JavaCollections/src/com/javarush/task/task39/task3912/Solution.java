package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static int maxSquare(int[][] matrix)
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSquareSize = 0;

        // Создаем массив для хранения размеров наибольших квадратов
        int[][] dp = new int[rows][cols];

        // Копируем первую строку и первый столбец из matrix в dp
        for (int i = 0; i < rows; i++)
        {
            dp[i][0] = matrix[i][0];
            maxSquareSize = Math.max(maxSquareSize, dp[i][0]);
        }
        for (int j = 0; j < cols; j++)
        {
            dp[0][j] = matrix[0][j];
            maxSquareSize = Math.max(maxSquareSize, dp[0][j]);
        }

        // Заполняем dp и находим максимальную площадь квадрата
        for (int i = 1; i < rows; i++)
        {
            for (int j = 1; j < cols; j++)
            {
                if (matrix[i][j] == 1)
                {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxSquareSize = Math.max(maxSquareSize, dp[i][j]);
                }
            }
        }

        // Возвращаем площадь самого большого квадрата
        return maxSquareSize * maxSquareSize;
    }
}
