package com.javarush.task.task19.task1904;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/
//C:\Users\tupik\Desktop\monstr566\Test.txt
public class Solution
{

    public static void main(String[] args) throws IOException
    {
        String file = "C:\\Users\\tupik\\Desktop\\monstr566\\Test.txt";
        PersonScannerAdapter scannerAdapter = new PersonScannerAdapter(new Scanner(new File(file)));
        //Scanner scanner = new Scanner(new File(file));
        //System.out.println(scanner.nextLine());
        Person person1 = scannerAdapter.read();
        Person person2 = scannerAdapter.read();
        System.out.println(person1.toString());
        System.out.println(person2.toString());

    }

    public static class PersonScannerAdapter implements PersonScanner
    {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner)
        {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException
        {
            String s = fileScanner.nextLine();
            String[] masS = s.split(" ");
            Date birthday = new Date(Integer.parseInt(masS[5]) - 1900, Integer.parseInt(masS[4]) - 1, Integer.parseInt(masS[3]));
            Person person = new Person(masS[1], masS[2], masS[0], birthday);
            return person;
        }

        @Override
        public void close() throws IOException
        {
            fileScanner.close();
        }
    }
}
