package com.company.reader;

import com.company.calendar.CalendarFormat;
import com.company.convert.ConvertStringToRole;
import com.company.queryExecute.QueryExecute;
import com.company.role.Role;
import com.company.user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Reader {

    public static List<User> readListOfUsers(List<User> users, String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            int userAge = 0;
            String line = in.nextLine();
            String[] userBuilder;
            userBuilder = line.split(",");
            String userLogin = userBuilder[0];
            String userPassword = userBuilder[1];
            String userAgeString = userBuilder[2];
            try {
                userAge = Integer.parseInt(userAgeString);
            } catch (Exception e) {
                System.out.println("Wrong format of age");
            }
            String userRoleString = userBuilder[3];
            Role userRole = ConvertStringToRole.convert(userRoleString);
            String followingStreamers;
            try {
                followingStreamers = userBuilder[4];
            } catch (ArrayIndexOutOfBoundsException e) {
                followingStreamers = "";
            }

            User user = new User(userLogin, userPassword, userAge, userRole, followingStreamers);
            users.add(user);
        }

        return users;
    }

    public static List<CalendarFormat> readCalendarFromDatebase(List<CalendarFormat> calendar) {
        try {
            ResultSet resultSet = QueryExecute.executeSelectCalendar("SELECT * FROM public.dates ORDER BY \"Date\", \"Hour\", \"Event\"");

            while (!resultSet.isLast()) {
                resultSet.next();
                Date getDate = resultSet.getDate("Date");
                String getHour = resultSet.getString("Hour");
                if (getHour == null) {
                    getHour = "00:00";
                }
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH-mm");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf2.format(getDate) + " " + getHour;
                java.util.Date date = sdf1.parse(dateString);
                String getEvent = resultSet.getString("Event");
                String getStreamer = resultSet.getString("Streamer");
                CalendarFormat calendarLine = new CalendarFormat(date, getEvent, getStreamer);
                calendar.add(calendarLine);
            }

            return calendar;
        } catch (SQLException | ParseException e) {
        }

        return null;
    }

    public static List<String> readListOfStreamers(List<String> listsOfStreamers, List<User> users){
        listsOfStreamers = users.stream()
                .filter(u->u.getRole()==Role.STREAMER)
                .map(User::getLogin)
                .collect(Collectors.toList());

        return listsOfStreamers;
    }


}
