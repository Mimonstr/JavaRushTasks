package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/

public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    //public static long numberOfPossibleAscents(int n)
    //{
//        // Если n меньше 0, вернем 0
//        if (n < 0)
//        {
//            return 0;
//        }
//
//        // Инициализируем массив для хранения количества способов достичь каждой ступеньки
//        int[] dp = new int[n + 1];
//
//        // Для лестницы из 0 ступенек существует только один способ
//        dp[0] = 1;
//
//        // Вычисляем количество способов для каждой ступеньки от 1 до n
//        for (int i = 1; i <= n; i++)
//        {
//            // Суммируем количество способов для предыдущих трех ступенек
//            dp[i] = dp[i - 1] + (i >= 2 ? dp[i - 2] : 0) + (i >= 3 ? dp[i - 3] : 0);
//        }
//
//        // Возвращаем количество способов для последней ступеньки
//        return dp[n];


        /**
         * Другое решение
         */
        // Если n меньше 0, вернем 0
//        if (n < 0) {
//            return 0;
//        }
//
//        // Если n равно 0 или 1, вернем 1
//        if (n <= 1) {
//            return 1;
//        }
//
//        // Инициализируем переменные для хранения трех последних значений
//        int first = 1;
//        int second = 1;
//        int third = 2;
//
//        // Вычисляем количество способов для каждой ступеньки от 3 до n
//        for (int i = 3; i <= n; i++)
//        {
//            int current = first + second + third;
//            // Обновляем значения для следующей итерации
//            first = second;
//            second = third;
//            third = current;
//        }
//
//        // Возвращаем количество способов для последней ступеньки
//        return third;
//    }


        public static long numberOfPossibleAscents(int n)
        {
            if (n < 0)
            {
                return 0;
            }
            long[] memo = new long[n + 1];
            Arrays.fill(memo, -1);
            return numberOfPossibleAscents(n, memo);
        }

    private static long numberOfPossibleAscents(int n, long[] memo)
    {
        if (n < 0)
        {
            return 0;
        }
        else if (n == 0)
        {
            return 1;
        }
        else if (memo[n] > -1)
        {
            return memo[n];
        }
        else
        {
            memo[n] = numberOfPossibleAscents(n - 1, memo)
                    + numberOfPossibleAscents(n - 2, memo)
                    + numberOfPossibleAscents(n - 3, memo);
            return memo[n];
        }
    }

}

