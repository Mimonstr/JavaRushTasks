package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution
{
    public static void main(String[] args)
    {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\p-mis\\Desktop\\monstr566\\Java_Projects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.LOGIN, null, new Date()));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Misha",null, null));
        //System.out.println(logParser.execute("get ip"));
        //System.out.println(logParser.execute("get status"));
        System.out.println(logParser.execute("get ip for user = \"Amigo\""));
        System.out.println(logParser.execute("get event for date = \"30.01.2014 12:56:22\""));
    }
}