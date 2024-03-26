package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution
{
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass
    {
    }

    public static Solution[] getTwoSolutions()
    {
        Solution[] masSolution = new Solution[2];
        Solution solution = new Solution();
        solution.innerClasses[0] = solution.new InnerClass();
        solution.innerClasses[1] = solution.new InnerClass();

        Solution solution2 = new Solution();
        solution2.innerClasses[0] = solution.new InnerClass();
        solution2.innerClasses[1] = solution.new InnerClass();

        masSolution[0] = solution;
        masSolution[1] = solution2;
        return masSolution;
    }

    public static void main(String[] args)
    {

    }
}
