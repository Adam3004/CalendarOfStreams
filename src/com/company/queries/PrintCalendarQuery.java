package com.company.queries;

import com.company.actions.Action;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.queryExecute.QueryExecute;


import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PrintCalendarQuery implements Action {
    String order;
    int number;
    ResultSet resultSet;
    private Scanner sc;

    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    public PrintCalendarQuery(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public String getActionName() {
        return "Calendar in datebase";
    }

    @Override
    public void execute() throws FileNotFoundException {
        try {
            resultSet = QueryExecute.executeSelectCalendar("SELECT * FROM public.dates");
            if (resultSet.isLast()) {
                return;
            } else {
                for (int i = 0; i < 10; i++) {
                    if (resultSet.isLast()) {
                        return;
                    } else {
                        resultSet.next();
                        Date getDate = resultSet.getDate("Date");
                        String getHour = resultSet.getString("Hour");
                        String getEvent = resultSet.getString("Event");
                        String getStreamer = resultSet.getString("Streamer");
                        if (getEvent == null) {
                            System.out.println("Date: " + getDate + " off-stream day");
                        } else {
                            System.out.println("Date: " + sdf1.format(getDate) + " Hour: " + getHour + " Event: " + getEvent + " Streamer: " + getStreamer);
                        }
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Do you want to see more days? Write number of the next days or press enter to end operation");
        try {
            sc.nextLine();
            order = sc.nextLine();
            NullInputExceptionMethod.method(order);
            number = Integer.parseInt(order);
        } catch (NullInputException e) {
            return;
        } catch (NumberFormatException e){
            e.printStackTrace();
            return;
        }
        if (number > 0) {
            try {
                for (int i = 0; i < number; i++) {
                    resultSet.next();
                    Date getDate = resultSet.getDate("Date");
                    String getHour = resultSet.getString("Hour");
                    String getEvent = resultSet.getString("Event");
                    String getStreamer = resultSet.getString("Streamer");
                    if (getEvent == null) {
                        System.out.println("Date: " + getDate + " off-stream day");
                    } else {
                        System.out.println("Date: " + getDate + " Hour: " + getHour + " Event: " + getEvent + " Streamer: " + getStreamer);
                    }
                    if (resultSet.isLast()) {
                        return;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

