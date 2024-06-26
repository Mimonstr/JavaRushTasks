package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/

public class Solution
{

    public static void main(String[] args)
    {
    }

    public static <T> ArrayList<T> newArrayList(T... elements)
    {
        //напишите тут ваш код
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < elements.length; i++)
        {
            list.add(elements[i]);
        }
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements)
    {
        //напишите тут ваш код
        HashSet<T> list = new HashSet<>();
        for (int i = 0; i < elements.length; i++)
        {
            list.add(elements[i]);
        }
        return list;
    }

    public static <K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values)
    {
        //напишите тут ваш код
        if(keys.size() != values.size())
        {
            throw new IllegalArgumentException();
        }
        HashMap<K,V> hashMap = new HashMap<>();
        for (int i = 0; i < keys.size(); i++)
        {
            hashMap.put(keys.get(i), values.get(i));
        }
        return hashMap;
    }
}
