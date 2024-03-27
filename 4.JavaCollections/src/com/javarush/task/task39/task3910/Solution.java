package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/

public class Solution {
    public static void main(String[] args)
    {

    }

    public static boolean isPowerOfThree(int n)
    {
//        if (n % 3 == 0)
//        {
//            return true;
//        }
//        return false;

        if (n <= 0)
        {
            return false;
        }

        while (n % 3 == 0)
        {
            n /= 3;
        }

        return n == 1;
    }
}
