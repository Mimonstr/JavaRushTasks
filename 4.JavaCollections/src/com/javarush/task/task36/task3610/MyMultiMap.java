package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable
{
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount)
    {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size()
    {
        //напишите тут ваш код
        int size = 0;
        for (List<V> values : map.values())
        {
            size += values.size();
        }
        return size;
        //return map.size();

    }

    @Override
    public V put(K key, V value)
    {
        //напишите тут ваш код
//        List<V> values = map.computeIfAbsent(key, k -> new ArrayList<>());
//        V oldValue = null;
//        if (values.size() < repeatCount)
//        {
//            values.add(value);
//        }
//        else
//        {
//            oldValue = values.remove(0);
//            values.add(value);
//        }
//        return map.get(key).get(map.get(key).size() - 2);

        if (map.containsKey(key))
        {
            if (map.get(key).size() < repeatCount)
            {
                map.get(key).add(value);
                return map.get(key).get(map.get(key).size() - 2);
            }
            else
            {
                List<V> list = map.get(key);
                list.add(value);
                list.remove(0);
                return list.get(repeatCount - 2);
            }
        }
        else
        {
            map.put(key, new ArrayList<V>()
            {{
                add(value);
            }});
            return null;
        }
    }

    @Override
    public V remove(Object key)
    {
        if (map.get(key) != null)
        {
            List<V> list = map.get(key);
            V temp = list.remove(0);
            if (list.isEmpty())
            {
                map.remove(key);
            }
            return temp;
        }
        else
        {
            return null;
        }

    }

    @Override
    public Set<K> keySet()
    {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values()
    {
        //напишите тут ваш код
        List<V> values = new ArrayList<>();
        for (List<V> list: map.values())
        {
            for(V value: list)
            {
                values.add(value);
            }
        }
        return values;
    }

    @Override
    public boolean containsKey(Object key)
    {
        //напишите тут ваш код
        return map.containsKey(key);

    }

    @Override
    public boolean containsValue(Object value)
    {
        //напишите тут ваш код
        return values().contains(value);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}