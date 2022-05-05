package com.company.saver;

import com.company.calendar.CalendarFormat;
import com.company.queryExecute.QueryExecute;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Saver {

    public static void saveList(List<User> users, String fileName) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fileName, false));
        for (User user : users) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(user.getLogin());
            stringBuilder.append(",");
            stringBuilder.append(user.getPassword());
            stringBuilder.append(",");
            stringBuilder.append(user.getAge());
            stringBuilder.append(",");
            stringBuilder.append(user.getRole());
            stringBuilder.append(",");
            stringBuilder.append(user.getFollowingStreamers());
            printWriter.println(stringBuilder);
            printWriter.flush();
        }
        printWriter.close();
    }


    public static void saveCalendar(List<CalendarFormat> calendar) {
        QueryExecute.executeQueryCalendar("DELETE FROM public.dates");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH-mm");
        for (CalendarFormat calendarFormat : calendar) {
            String dateString = sdf1.format(calendarFormat.getDate());
            Date dateUtil = null;
            try {
                dateUtil = sdf1.parse(dateString);
            } catch (ParseException e) {
            }
            java.sql.Date date = new java.sql.Date(dateUtil.getTime());
            String hour = sdf2.format(calendarFormat.getDate());
            String event = calendarFormat.getEvent();
            String streamer = calendarFormat.getStreamer();

            QueryExecute.executeQueryCalendar("INSERT INTO public.dates(\"Date\", \"Hour\", \"Event\", \"Streamer\") VALUES ('" + date + "','" + hour + "','" + event + "','" + streamer + "');");
        }
        QueryExecute.executeQueryCalendar("SELECT * FROM public.dates ORDER BY \"Date\" ");
    }
}
