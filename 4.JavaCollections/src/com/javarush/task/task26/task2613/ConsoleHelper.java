package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");
    private final static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }
    public static void printExitMessage()
    {
        writeMessage("Exiting...");
    }

    public static String readString() throws InterruptOperationException
    {
        try
        {
            String text = bis.readLine();
            if ("exit".equals(text.toLowerCase()))
            {
                throw new InterruptOperationException();
            }

            return text;
        } catch (IOException ignored)
        { //suppose it will never occur
        }
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        String currencyCode = "";
        do
        {
            //writeMessage("Введите, пожалуйста, код валюты (3 символа)");
            writeMessage(res.getString("choose.currency.code"));
            currencyCode = readString();
        }while (currencyCode.length() != 3);

        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        String[] result = new String[2];
        while (true)
        {
            //writeMessage("Введите номинал и количество банкнот через пробел для валюты " + currencyCode + ": ");
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            try
            {
                String input = readString().trim();
                String[] tokens = input.split("\\s+");
                if (tokens.length != 2)
                {
                    writeMessage("Некорректный ввод. Введите два целых числа через пробел.");
                    continue;
                }
                int nominal = Integer.parseInt(tokens[0]);
                int count = Integer.parseInt(tokens[1]);
                if (nominal <= 0 || count <= 0)
                {
                    writeMessage("Номинал и количество банкнот должны быть положительными числами.");
                    continue;
                }
                result[0] = String.valueOf(nominal);
                result[1] = String.valueOf(count);
                break;
            }
            catch (NumberFormatException | InterruptOperationException e)
            {
                writeMessage("Ошибка парсинга чисел. Введите два целых числа через пробел.");
            }
        }
        writeMessage(res.getString("the.end"));
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        Operation operation = null;
        int choice = 0;
        while (operation == null)
        {
            try
            {
                writeMessage(res.getString("choose.operation") + "\n");
                writeMessage("1 - " + res.getString("operation.INFO") + "\n");
                writeMessage("2 - " + res.getString("operation.DEPOSIT") + "\n");
                writeMessage("3 - " + res.getString("operation.WITHDRAW") + "\n");
                writeMessage("4 - " + res.getString("operation.EXIT") + "\n");
                //writeMessage("Какую операцию хотите выполнить?\n 1 - INFO\n 2 - DEPOSIT\n 3 - WITHDRAW\n 4 - EXIT\n Введите соответсвующую цифру:");
                choice = Integer.parseInt(readString());
                operation = Operation.getAllowableOperationByOrdinal(choice);
            }
            catch (IllegalArgumentException e)
            {
                //writeMessage("Invalid input. Please try again.");
                writeMessage(res.getString("invalid.data"));
            }
        }
        return operation;

    }
}
