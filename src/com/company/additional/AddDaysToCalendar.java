package com.company.additional;


import com.company.calendar.CalendarFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddDaysToCalendar {
    public static void method(List<CalendarFormat> calendar) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
        long diff;
        int numberOfDays;
        long oneDayTimeInMilis = 86400000;
        long oneHourTimeInMilis = 3600000;
        Date dailyDate = new Date();
        Date lastDate;
        if (calendar.size() > 0) {
            lastDate = calendar.get(calendar.size() - 1).getDate();
        } else {
            lastDate = new Date();
        }
        diff = lastDate.getTime() - dailyDate.getTime();
        if (!sdf2.format(lastDate).equals("00")) {
            String stringDate = sdf4.format(lastDate) + " 00:00";
            try {
                lastDate = sdf1.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (diff <= 0) {
            numberOfDays = 30;
            CalendarFormat cf = new CalendarFormat(lastDate, null, null);
            calendar.add(cf);
        } else if (diff > oneDayTimeInMilis * 29) {
            numberOfDays = 0;
        } else {
            numberOfDays = Math.toIntExact((30 * oneDayTimeInMilis - diff) / oneDayTimeInMilis);
        }


        for (int i = 0; i < numberOfDays; i++) {
            Date date1 = new Date(lastDate.getTime() + oneDayTimeInMilis);
            if (!sdf2.format(date1).equals("00") && sdf3.format(date1).equals("03")) {
                date1.setTime(date1.getTime() - oneHourTimeInMilis);
            } else if (!sdf2.format(date1).equals("00") && sdf3.format(date1).equals("10")) {
                date1.setTime(date1.getTime() + oneHourTimeInMilis);
            }
            CalendarFormat cf = new CalendarFormat(date1, null, null);
            calendar.add(cf);
            lastDate = date1;
        }
    }
}
