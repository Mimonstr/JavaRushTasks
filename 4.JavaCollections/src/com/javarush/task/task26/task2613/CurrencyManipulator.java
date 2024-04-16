package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator
{
    //код валюты, например, USD. Состоит из трех букв.
    private String currencyCode;
    //Map<номинал, количество>.
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
        this.denominations = new TreeMap<>();
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if(denominations.containsKey(denomination))
        {
            denominations.put(denomination, denominations.get(denomination)+count);
        }
        else denominations.put(denomination, count);
    }

    public int getTotalAmount()
    {
        int result = 0;
        for(int denomination: denominations.keySet())
        {
            result += denomination * denominations.get(denomination);
        }
        return result;
    }
    public boolean hasMoney()
    {
        return getTotalAmount() != 0 ? true : false;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        return getTotalAmount() >= expectedAmount;
    }
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        TreeMap<Integer, Integer> sortedDenominations = new TreeMap<>(Collections.reverseOrder()); // Для сортировки по убыванию
        sortedDenominations.putAll(this.denominations);

        Map<Integer, Integer> withdrawMap = new HashMap<>();
        int remainingAmount = expectedAmount;
        for (Map.Entry<Integer, Integer> entry : sortedDenominations.entrySet())
        {
            int denomination = entry.getKey();
            int availableNotes = entry.getValue();

            // Определяем максимальное количество банкнот данного номинала, которое можно использовать
            int usableNotes = Math.min(remainingAmount / denomination, availableNotes);
            if (usableNotes > 0)
            {
                // Вычитаем сумму, покрытую данным номиналом, из оставшейся суммы
                remainingAmount -= denomination * usableNotes;
                // Записываем количество использованных банкнот в карту выдачи
                withdrawMap.put(denomination, usableNotes);
            }

            // Если оставшаяся сумма стала нулевой, значит, мы успешно собрали запрошенную сумму
            if (remainingAmount == 0)
            {
                // Удаляем из манипулятора использованные банкноты
                for (Map.Entry<Integer, Integer> entryToRemove : withdrawMap.entrySet())
                {
                    int notesToRemove = entryToRemove.getValue();
                    this.denominations.put(entryToRemove.getKey(), this.denominations.get(entryToRemove.getKey()) - notesToRemove);
                }
                return withdrawMap;
            }
        }

        // Если оставшаяся сумма не нулевая, значит, невозможно собрать запрошенную сумму
        throw new NotEnoughMoneyException();
    }
}
