package com.company.findInList;

import com.company.calendar.CalendarFormat;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FIndSimilarEvent {

    public static int method(List<CalendarFormat> calendar, String eventsName, String streamersName, Date date, Scanner sc, int index) {
        long diff;
        long twoHoursInMilis = 7200000;
        String order;
        CalendarFormat event;
        for (int i = 0; i < calendar.size(); i++) {
            event = calendar.get(i);
            if (isSimilarly(event, eventsName, streamersName)) {
                diff = Math.abs(calendar.get(i).getDate().getTime() - date.getTime());
                if (diff < twoHoursInMilis) {
                    System.out.println("There is the same event in similar time. Are u sure about adding yours event too? (Y/N): ");
                    order = sc.nextLine();
                    if (order.equals("N")) {

                        return calendar.size() + 1;
                    } else if (order.equals("Y")) {

                        return index;
                    } else {
                        System.out.println("Wrong command ");

                        return calendar.size() + 1;
                    }
                }
            }
        }

        return index;
    }

    private static boolean isSimilarly(CalendarFormat event, String eventsName, String streamersName) {
        try {
            if ((event.getEvent().equals(eventsName) && event.getStreamer().equals(streamersName)) ||
                    (event.getStreamer().equals(streamersName))) {

                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }
}
