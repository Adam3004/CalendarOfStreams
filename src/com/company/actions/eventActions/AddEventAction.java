package com.company.actions.eventActions;

import com.company.actions.Action;
import com.company.calendar.CalendarFormat;
import com.company.date.DateChecker;
import com.company.date.DateEntering;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.findInList.FIndSimilarEvent;
import com.company.findInList.FindUserInList;
import com.company.message.MessageCreator;
import com.company.role.Role;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AddEventAction implements Action {
    private List<CalendarFormat> calendar;
    private List<User> users;
    private Scanner sc;
    private Session session;
    Date date;
    String event;
    String streamer;
    String order;
    long oneDayInMillis = 86400000;
    int foundSimilarEvent = 0;
    boolean isEventInGoodTime = false;

    public AddEventAction(List<CalendarFormat> calendar, List<User> users, Scanner sc, Session session) {
        this.calendar = calendar;
        this.users = users;
        this.sc = sc;
        this.session = session;
    }


    @Override
    public String getActionName() {
        return "Add event";
    }

    @Override
    public void execute() throws FileNotFoundException {
        sc.nextLine();
        if (session.getSessionUser().getRole() == Role.VIEWER) {
            System.out.println("You have no permission to do it");
        } else {
            while (true) {
                date = DateEntering.method(sc);
                if (date == null) {

                    break;
                }
                isEventInGoodTime = diffBetweenDatesChecker(date, oneDayInMillis);
                if (!isEventInGoodTime) {

                    break;
                }

                sc.nextLine();
                System.out.println("Enter event's name: ");
                try {
                    event = sc.nextLine();
                    NullInputExceptionMethod.method(event);
                } catch (NullInputException e) {

                    continue;
                }
                System.out.println("Enter streamer's name: ");
                try {
                    streamer = sc.nextLine();
                    NullInputExceptionMethod.method(streamer);
                } catch (NullInputException e) {

                    continue;
                }
                FindUserInList findUserInList = new FindUserInList(users, streamer, "");
                if (!findUserInList.isStreamerInList()) {
                    System.out.println("This is not a streamer");

                    continue;
                }
                int index = DateChecker.findPlace(date, calendar);
                CalendarFormat calendarFormat = new CalendarFormat(date, event, streamer);
                foundSimilarEvent = FIndSimilarEvent.method(calendar, event, streamer, date, sc, index);
                if (foundSimilarEvent > calendar.size()) {

                    break;
                }
                additionOfEvent(calendar, date, calendarFormat, index);
                if (session.getSessionUser().getRole() != Role.STREAMER) {
                    MessageCreator.sendMessageToStreamerAboutCreatingEventWithHim(streamer, date, event);
                }
                MessageCreator.sendMessageAboutAdditionOfEvent(users, streamer, date, event);
                System.out.println("Event is added");
                System.out.println("Do you want to add more events?: (Enter \"yes\" if u want)");
                order = sc.nextLine();
                if (!(order.equals("yes") || order.equals("YES") || order.equals("Yes"))) {

                    break;
                }

            }
        }
    }

    public static boolean diffBetweenDatesChecker(Date date, long oneDayInMillis) {
        Date dailyDate = new Date();
        long dateDiff = Math.abs(date.getTime() - dailyDate.getTime());
        if (dateDiff > oneDayInMillis * 30) {
            System.out.println("You can add event only 30 days ahead");

            return false;
        }

        return true;
    }


    public static void additionOfEvent(List<CalendarFormat> calendar, Date date, CalendarFormat calendarFormat, int index) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        if (index > 0) {
            if ((calendar.get(index - 1) == null &&
                    sdf1.format(calendar.get(index - 1).getDate()).equals(sdf1.format(date)) &&
                    sdf2.format(calendar.get(index - 1).getDate()).equals("00:00")) ||
                    (calendar.get(index - 1).getEvent().equals("null") &&
                            sdf1.format(calendar.get(index - 1).getDate()).equals(sdf1.format(date)) &&
                            sdf2.format(calendar.get(index - 1).getDate()).equals("00:00"))) {
                calendar.set(index - 1, calendarFormat);
            } else {
                calendar.add(index, calendarFormat);
            }
        } else {
            calendar.add(index, calendarFormat);
        }

    }


}
