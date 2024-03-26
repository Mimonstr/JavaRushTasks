package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.Random;

/* 
Генератор паролей
*/

public class Solution
{
    public static void main(String[] args)
    {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()
    {
        //48-57 - цифры все включительно
        //65-90 - буквы включительно болльшие
        //97-122 - буквы маленькие
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Random random = new Random();
        int chislo;
        int first;
        int second;
        int third;
        char sym;
        outputStream.write((char)(random.nextInt(10) + 48));//число
        outputStream.write((char)(random.nextInt(26) + 65));//Буква большая
        outputStream.write((char)(random.nextInt(26) + 97));//Буква маленькая
        while (outputStream.size() < 8)
        {
            chislo = random.nextInt(3);//число от 0-2
            if (chislo == 0)
            {
                //48-57 - цифры все включительно
                //first = random.nextInt(10) + 48;
                sym = (char)(random.nextInt(10) + 48);
            }
            else if (chislo == 1)
            {
                //65-90 - буквы включительно болльшие
                //second = random.nextInt(26) + 65;
                sym = (char)(random.nextInt(26) + 65);
            }
            else
            {
                //97-122 - буквы маленькие
                //third = random.nextInt(26) + 97;
                sym = (char)(random.nextInt(26) + 97);
            }
            outputStream.write(sym);
        }
        return outputStream;
    }
}
