package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Формируем WHERE
*/

public class Solution
{
    public static void main(String[] args)
    {
        //name=Ivanov, country=Ukraine, city=Kiev, age=null
        Map<String, String> params = new LinkedHashMap<>();
        params.put("name", "Ivanov");
        params.put("country","Russia");
        params.put("city", "Moscow");
        params.put("age", null);
        System.out.println(getQuery(params));

    }

    public static String getQuery(Map<String, String> params)
    {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String key: params.keySet())
        {
            if (params.get(key) != null)
            {
               count++;
            }
        }
        for (String key: params.keySet())
        {
            if (params.get(key) != null && count > 1)
            {
                count--;
                sb.append(key).append(" = '").append(params.get(key)).append("' ").append("and ");
            }
            else if (params.get(key) != null) sb.append(key).append(" = '").append(params.get(key)).append("'");
        }
        return sb.toString();
    }
}
