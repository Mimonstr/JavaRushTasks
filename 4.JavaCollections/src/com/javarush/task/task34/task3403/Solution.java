package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/

public class Solution
{
    public void recurse(int n)
    {
        int divider = 2;

        while (divider <= n)
        {

            if ((n % divider) == 0)
            {
                if (divider != n)
                {
                    System.out.print(divider + " ");
                    recurse(n / divider);
                }
                else
                {
                    System.out.print(divider);
                }
                return;
            }
            divider++;
        }
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        solution.recurse(25);
        solution.recurse(132);
    }
}
