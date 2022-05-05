package com.company.findInList;

import com.company.calendar.CalendarFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//Zwraca numer o 1 wiekszy od dlugosci listy!!!
public class FindEventInCalendar {
    public static int method(List<CalendarFormat> calendar, String eventsName, String streamersName, Date date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (int i = 0; i < calendar.size(); i++) {
            if (calendar.get(i).getEvent().equals(eventsName) && calendar.get(i).getStreamer().equals(streamersName)
                    && sdf1.format(date).equals(sdf1.format(calendar.get(i).getDate()))) {

                return i;
            }
        }

        return calendar.size() + 1;
    }

}
