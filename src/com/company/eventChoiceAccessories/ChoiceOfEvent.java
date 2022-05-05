package com.company.eventChoiceAccessories;

import com.company.calendar.CalendarFormat;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.findInList.FindEventInCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ChoiceOfEvent {

    public static boolean eventSearching(List<CalendarFormat> calendar, Date date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        boolean foundEvent = false;
        for (CalendarFormat thisDate : calendar) {
            if (sdf1.format(thisDate.getDate()).equals(sdf1.format(date)) &&
                    (!thisDate.getEvent().isEmpty() && !thisDate.getStreamer().isEmpty())) {
                foundEvent = true;

                break;
            }
        }

        if (foundEvent) {
            System.out.println(sdf1.format(date) + " is announced: ");
            calendar.stream()
                    .filter(c -> sdf1.format(c.getDate()).equals(sdf1.format(date)))
                    .filter(c -> !c.getEvent().isEmpty() || !c.getStreamer().isEmpty())
                    .map(c -> "Event: " + c.getEvent() + " organised by: " + c.getStreamer())
                    .forEach(System.out::println);

            return true;
        } else {
            System.out.println("There are no events on that date ");

            return false;
        }
    }


    public static int choosingEvent(Scanner sc, List<CalendarFormat> calendar, Date date) {
        String eventsName;
        String streamersName;
        int index;
        sc.nextLine();
        System.out.println("Enter event's name: ");
        try {
            eventsName = sc.nextLine();
            NullInputExceptionMethod.method(eventsName);
        } catch (NullInputException e) {

            return calendar.size() + 1;
        }
        System.out.println("Enter streamer's name: ");
        try {
            streamersName = sc.nextLine();
            NullInputExceptionMethod.method(streamersName);
        } catch (NullInputException e) {

            return calendar.size() + 1;
        }
        index = FindEventInCalendar.method(calendar, eventsName, streamersName, date);

        return index;
    }
}
