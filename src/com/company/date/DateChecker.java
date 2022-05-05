package com.company.date;

import com.company.calendar.CalendarFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class DateChecker {

    public static int findPlace(Date date, List<CalendarFormat> calendar) {
        if (calendar.size() > 1) {

            return findPlace(calendar, date);
        } else if (calendar.size() == 1) {
            if (calendar.get(0).getDate().before(date)) {

                return 1;
            } else {

                return 0;
            }
        } else {

            return 0;
        }
    }

    private static int findMiddle(int left, int right) {
        return (left + right) / 2;
    }

    private static int findPlace(List<CalendarFormat> calendar, Date date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int i = calendar.size() - 1;
        int left = 0;
        int right = i;
        int middle;
        while (true) {
            if (sdf1.format(calendar.get(i).getDate()).equals(sdf1.format(date))) {

                return i + 1;
            } else if (calendar.get(i).getDate().after(date) && i == 0) {

                return 0;
            } else if (calendar.get(i - 1).getDate().before(date) && calendar.get(i).getDate().after(date)) {

                return i;
            } else if (calendar.get(i).getDate().before(date) && i == calendar.size() - 1) {

                return calendar.size();
            } else if (calendar.get(i).getDate().after(date)) {
                right = i;
                middle = findMiddle(left, right);
                i = middle;
            } else if (calendar.get(i).getDate().before(date)) {
                left = i;
                middle = findMiddle(left, right);
                i = middle;
            }
        }

    }


}
