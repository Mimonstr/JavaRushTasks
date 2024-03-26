package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args)
    {
        Integer[] arr = {13, 8, 15, 5, 17};
        sort(arr);
        //System.out.println(Arrays.toString(arr));

    }

    public static Integer[] sort(Integer[] array)
    {
        // Метод для обмена значениями двух элементов в массиве
        if (array.length <= 1)
        {
            return array; // Массив уже отсортирован или пуст
        }
        // Найдем медиану массива
        int median;
        Arrays.sort(array); // Сортируем массив
        int middle = array.length / 2;
        if (array.length % 2 == 0)
        {
            // Если массив имеет четную длину, медианой будет среднее двух средних элементов
            median =  (array[middle - 1] + array[middle]) / 2;
        }
        else
        {
            // Если массив имеет нечетную длину, медианой будет средний элемент
            median =  array[middle];
        }
        // Создадим массив для хранения расстояний до медианы
        Integer[] distances = new Integer[array.length];

        // Вычислим расстояния от каждого элемента до медианы
        for (int i = 0; i < array.length; i++)
        {
            distances[i] = Math.abs(array[i] - median);
        }
        // Отсортируем массив по расстояниям
        for (int i = 0; i < array.length - 1; i++)
        {
            for (int j = i + 1; j < array.length; j++)
            {
                if (distances[i] > distances[j])
                {
                    // Обмен значениями элементов в исходном массиве и массиве расстояний
                    swap(array, i, j);
                    swap(distances, i, j);
                }
            }
        }
        return array;
    }
    // Метод для обмена значениями двух элементов в массиве
    private static void swap(Integer[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
