package com.company.filtration;

import com.company.calendar.CalendarFormat;
import com.company.date.DateEntering;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventsFiltration {

    public static void method(Scanner sc, List<CalendarFormat> calendar) {
        String order;
        int numberOfOrder;
        List<CalendarFormat> resultList = calendar;
        while (true) {
            System.out.println("Filter by: ");
            System.out.println("1. Date ");
            System.out.println("2. Event ");
            System.out.println("3. Streamer ");
            System.out.println("Choose number: ");
            order = sc.nextLine();
            try {
                NullInputExceptionMethod.method(order);
                numberOfOrder = Integer.parseInt(order);
            } catch (NullInputException e) {
                e.printStackTrace();

                return;
            } catch (NumberFormatException e) {
                System.out.println("It is not a number");
                e.printStackTrace();

                return;
            }
            switch (numberOfOrder) {
                case 1:
                    resultList = dateFilter(sc, resultList);
                    sc.nextLine();

                    break;
                case 2:
                    resultList = eventFilter(sc, resultList);

                    break;
                case 3:
                    resultList = streamerFilter(sc, resultList);

                    break;
                default:
                    System.out.println("wrong number");
            }
            Stream<CalendarFormat> outPut = resultList.stream();
            outPut
                    .map(EventsFiltration::isEventNull)
                    .forEach(System.out::println);
            System.out.println("Do you want to add more filters?: (Y/N)");
            order = sc.nextLine();
            if (!order.equals("Y")) {

                break;
            }
        }
    }


    private static List<CalendarFormat> dateFilter(Scanner sc, List<CalendarFormat> resultList) {
        String order;
        int numberOfOrder;
        Date date;
        Date dateAfter;
        System.out.println("Choose option: ");
        System.out.println("1. Before some date");
        System.out.println("2. After some date");
        System.out.println("3. Between dates");
        System.out.println("4. At the exact time");
        order = sc.nextLine();
        try {
            NullInputExceptionMethod.method(order);
            numberOfOrder = Integer.parseInt(order);
        } catch (NullInputException e) {
            e.printStackTrace();

            return null;
        } catch (NumberFormatException e) {
            System.out.println("It is not a number");
            e.printStackTrace();

            return null;
        }

        Stream<CalendarFormat> results = resultList.stream();
        switch (numberOfOrder) {
            case 1:
                date = DateEntering.dateEnteringMethod(sc);
                resultList = results
                        .filter(d -> d.getDate().before(date))
                        .collect(Collectors.toList());

                break;
            case 2:
                date = DateEntering.dateEnteringMethod(sc);
                resultList = results
                        .filter(d -> d.getDate().after(date))
                        .collect(Collectors.toList());

                break;
            case 3:
                System.out.println("First enter a date before which u want to see results");
                date = DateEntering.dateEnteringMethod(sc);
                System.out.println("Now enter a date after which u want to see results");
                dateAfter = DateEntering.dateEnteringMethod(sc);
                resultList = results
                        .filter(d -> d.getDate().before(date) && d.getDate().after(dateAfter))
                        .collect(Collectors.toList());

                break;
            case 4:
                date = DateEntering.dateEnteringMethod(sc);
                resultList = results
                        .filter(d -> d.getDate().equals(date))
                        .collect(Collectors.toList());

                break;
            default:
                System.out.println("wrong number");
        }

        return resultList;
    }


    private static List<CalendarFormat> eventFilter(Scanner sc, List<CalendarFormat> resultList) {
        String event;
        System.out.println("Enter searched event: ");
        event = sc.nextLine();
        try {
            NullInputExceptionMethod.method(event);
        } catch (NullInputException e) {
            e.printStackTrace();

            return null;
        }
        Stream<CalendarFormat> results = resultList.stream();
        resultList = results
                .filter(e -> e.getEvent().equals(event))
                .collect(Collectors.toList());

        return resultList;
    }

    private static List<CalendarFormat> streamerFilter(Scanner sc, List<CalendarFormat> resultList) {
        String streamer;
        System.out.println("Enter searched streamer: ");
        streamer = sc.nextLine();
        try {
            NullInputExceptionMethod.method(streamer);
        } catch (NullInputException e) {
            e.printStackTrace();

            return null;
        }
        Stream<CalendarFormat> results = resultList.stream();
        resultList = results
                .filter(e -> e.getStreamer().equals(streamer))
                .collect(Collectors.toList());

        return resultList;
    }

    private static String isEventNull(CalendarFormat event) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (event.getEvent() == null || event.getEvent().equals("null")) {

            return sdf1.format(event.getDate()) + " no events that day";
        } else {

            return sdf1.format(event.getDate()) + " " + event.getEvent() + " " + event.getStreamer();
        }
    }
}
