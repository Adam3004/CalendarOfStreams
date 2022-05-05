package com.company.date;

import com.company.exceptionMethods.WrongDateMethod;
import com.company.exceptionMethods.WrongHourMethod;
import com.company.exceptions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateEntering {
    // add if(date==null){return;) after use this class

    public static Date method(Scanner sc) {
        Date date;
        Date dailyDate = new Date();
        date = dateEnteringMethod(sc);
        if (date.before(dailyDate)) {
            System.out.println("You cannot add event in the past");

            return null;
        }

        return date;

    }

    public static Date dateEnteringMethod(Scanner sc) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int day;
        int month;
        int year;
        int hour;
        int minutes;
        String dateString;
        Date date = null;
        System.out.println("Enter a day: ");
        day = sc.nextInt();
        System.out.println("Enter a month: ");
        month = sc.nextInt();
        System.out.println("Enter a year: ");
        year = sc.nextInt();
        try {
            WrongDateMethod.method(day, month, year);
        } catch (WrongDayException | WrongMonthException | WrongYearException | ShorterMonthsException e) {
            e.printStackTrace();

            return null;
        }
        System.out.println("Enter hour: ");
        hour = sc.nextInt();
        if (hour == 24) {
            hour = 0;
        }
        System.out.println(hour + ":?? Enter minutes");
        minutes = sc.nextInt();
        try {
            WrongHourMethod.method(hour, minutes);
        } catch (WrongHourException e) {
            e.printStackTrace();

            return null;
        }
        dateString = year + "-" + month + "-" + day + " " + hour + ":" + minutes;
        try {
            date = sdf1.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }



    public static Date onlyDateMethod(Scanner sc) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        int day;
        int month;
        int year;
        String dateString;
        Date date = null;
        System.out.println("Enter a day: ");
        day = sc.nextInt();
        System.out.println("Enter a month: ");
        month = sc.nextInt();
        System.out.println("Enter a year: ");
        year = sc.nextInt();
        try {
            WrongDateMethod.method(day, month, year);
        } catch (WrongDayException | WrongMonthException | WrongYearException | ShorterMonthsException e) {
            e.printStackTrace();

            return null;
        }
        dateString = year + "-" + month + "-" + day;
        try {
            date = sdf1.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String onlyHourMethod(Scanner sc) {
        String finalHour;
        int hour;
        int minutes;
        System.out.println("Enter hour: ");
        hour = sc.nextInt();
        if (hour == 24) {
            hour = 0;
        }
        System.out.println(hour + ":?? Enter minutes");
        minutes = sc.nextInt();
        try {
            WrongHourMethod.method(hour, minutes);
        } catch (WrongHourException e) {
            e.printStackTrace();

            return null;
        }
        finalHour = hour + ":" + minutes;

        return finalHour;
    }

}
