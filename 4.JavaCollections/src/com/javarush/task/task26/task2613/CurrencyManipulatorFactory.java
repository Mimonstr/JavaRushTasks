package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CurrencyManipulatorFactory
{
    private CurrencyManipulatorFactory(){}
    private static Map<String, CurrencyManipulator> map = new HashMap<>();
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        String code = currencyCode.toUpperCase();
        if(!map.containsKey(code))
        {
            CurrencyManipulator manipulator = new CurrencyManipulator(code);
            map.put(manipulator.getCurrencyCode(), manipulator);

        }
        return map.get(code);

    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return map.values();
    }
}
