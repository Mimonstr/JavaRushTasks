package com.javarush.task.task30.task3010;

import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution
{
    public static void main(String[] args)
 //   {
//        //напишите тут ваш код
//        try
//        {
//            if (args.length == 1)
//            {
//                String input = args[0];
//                String result = checkNumberBase(input);
//                System.out.println(result);
//            } else
//            {
//                //System.out.println("Usage: java BaseConverter <input>");
//            }
//        }
//        catch (NumberFormatException e)
//        {
//
//        }
//        //String number = args[0];
//    }
//
//    private static String checkNumberBase(String input)
//    {
//        for (int base = 2; base <= 36; base++)
//        {
//            try
//            {
//                Long.parseLong(input, base);
//                return String.valueOf(base);
//            }
//            catch (NumberFormatException e)
//            {
//                // Пропускаем итерацию, если парсинг не удался
//            }
//        }
//        return "incorrect";
//    }
    {
        String line = null;
        try
        {
            line = args[0];
            Pattern pattern = Pattern.compile("[^0-9A-Za-z]");
            if (pattern.matcher(line).find())
            {
                System.out.println("incorrect");
            }
            else
            {
                char max = 0;
                char[] chars = line.toUpperCase().toCharArray();
                for (char current : chars)
                {
                    if (current > max)
                    {
                        max = current;
                    }
                }
                int radix;
                if (max <= '9')
                {
                    radix = max - 47;
                }
                else
                {
                    radix = max - 54;
                }
                if (radix < 2)
                {
                    radix = 2;
                }
                System.out.println(radix);
            }
        }
        catch (Exception e)
        {
        }
    }
}