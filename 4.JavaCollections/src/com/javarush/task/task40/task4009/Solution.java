package com.javarush.task.task40.task4009;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution
{
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year)
    {
//        LocalDate date = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("d.MM.yyyy"));
//        Year targetYear = Year.parse(year);
//        LocalDate birthdayInYear = date.withYear(targetYear.getValue());
//
//        DayOfWeek dayOfWeek = birthdayInYear.getDayOfWeek();
//        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ITALIAN);

        DateTimeFormatter birthdayDateFormat = DateTimeFormatter.ofPattern("d.M.y");
        LocalDate birthdayDate = LocalDate.parse(birthday, birthdayDateFormat);

        DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("y");
        Year yearDate = Year.parse(year, yearFormat);

        return birthdayDate.withYear(yearDate.getValue()).format(DateTimeFormatter.ofPattern("EEEE").withLocale(Locale.ITALIAN));

    }
}
