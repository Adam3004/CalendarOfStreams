package com.company.actions.eventActions;

import com.company.actions.Action;
import com.company.calendar.CalendarFormat;
import com.company.date.DateChecker;
import com.company.date.DateEntering;
import com.company.eventChoiceAccessories.ChoiceOfEvent;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.findInList.FIndSimilarEvent;
import com.company.findInList.FindEventInCalendar;
import com.company.findInList.FindUserInList;
import com.company.message.MessageCreator;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EditEventAction implements Action {
    private Scanner sc;
    private List<CalendarFormat> calendar;
    private List<User> users;
    Date date;
    boolean foundEvent = false;
    int index;
    String order;
    int numberOfOrder;
    CalendarFormat prevoiusEvent;


    public EditEventAction(Scanner sc, List<CalendarFormat> calendar, List<User> users) {
        this.sc = sc;
        this.calendar = calendar;
        this.users = users;
    }


    @Override
    public String getActionName() {
        return "Edit event";
    }

    @Override
    public void execute() throws FileNotFoundException {
        sc.nextLine();
        System.out.println("Enter date and choose event to edit");
        date = DateEntering.dateEnteringMethod(sc);
        if (date == null) {
            System.out.println("There are no events on that date ");

            return;
        }
        foundEvent = ChoiceOfEvent.eventSearching(calendar, date);
        if (!foundEvent) {

            return;
        }
        index = ChoiceOfEvent.choosingEvent(sc, calendar, date);

        if (index > calendar.size()) {
            System.out.println("This event was not found");

            return;
        }
        while (true) {
            System.out.println("What u want to edit: ");
            System.out.println("1. Date");
            System.out.println("2. Event's name");
            System.out.println("3. Streamer");
            System.out.println("4. Exit");
            order = sc.nextLine();
            try {
                NullInputExceptionMethod.method(order);
                numberOfOrder = Integer.parseInt(order);
            } catch (NullInputException e) {

                return;
            } catch (NumberFormatException e) {
                System.out.println("You must enter a number");

                return;
            }
            switch (numberOfOrder) {
                case 1:
                    prevoiusEvent = calendar.get(index);
                    index = editDateMethod(sc, calendar, index);
                    MessageCreator.sendMessageAboutEditionOfEvent(users, prevoiusEvent.getStreamer(),
                            calendar.get(index).getDate(), prevoiusEvent.getEvent(), prevoiusEvent.getDate(),
                            prevoiusEvent.getEvent());

                    break;
                case 2:
                    prevoiusEvent = calendar.get(index);
                    editEventMethod(sc, calendar, index);
                    MessageCreator.sendMessageAboutEditionOfEvent(users, prevoiusEvent.getStreamer(),
                            prevoiusEvent.getDate(), calendar.get(index).getEvent(), prevoiusEvent.getDate(),
                            prevoiusEvent.getEvent());


                    break;
                case 3:
                    editStreamerMethod(sc, calendar, index, users);
                    MessageCreator.sendMessageAboutAdditionOfEvent(users, calendar.get(index).getStreamer(),
                            calendar.get(index).getDate(), calendar.get(index).getEvent());

                    break;
                case 4:

                    return;
                default:
                    System.out.println("Wrong command");
            }
        }
    }

    private static int editDateMethod(Scanner sc, List<CalendarFormat> calendar, int index) {
        CalendarFormat foundEvent = calendar.get(index);
        long oneDayInMillis = 86400000;
        Date date = DateEntering.method(sc);
        if (date == null) {

            return index;
        }
        boolean isEventInGoodTime = AddEventAction.diffBetweenDatesChecker(date, oneDayInMillis);
        if (!isEventInGoodTime) {

            return index;
        }
        int foundSimilarEvent = FIndSimilarEvent.method(calendar, foundEvent.getEvent(), foundEvent.getStreamer(), date, sc, index);
        if (foundSimilarEvent > calendar.size()) {

            return index;
        }
        RemoveEventAction.removingEvent(index, calendar, foundEvent.getDate());
        index = DateChecker.findPlace(date, calendar);
        foundEvent.setDate(date);
        AddEventAction.additionOfEvent(calendar, date, foundEvent, index);
        System.out.println("Date is updated");
        sc.nextLine();
        index = FindEventInCalendar.method(calendar, foundEvent.getEvent(), foundEvent.getStreamer(), date);
        return index;
    }


    private static void editEventMethod(Scanner sc, List<CalendarFormat> calendar, int index) {
        String eventsName;
        CalendarFormat foundEvent = calendar.get(index);
        System.out.println("Enter new name of event: ");
        eventsName = sc.nextLine();
        try {
            NullInputExceptionMethod.method(eventsName);
        } catch (NullInputException e) {
            e.printStackTrace();
        }
        foundEvent.setEvent(eventsName);
        calendar.set(index, foundEvent);
        System.out.println("Event it updated");
    }


    private static void editStreamerMethod(Scanner sc, List<CalendarFormat> calendar, int index, List<User> users) {
        String streamersName;
        CalendarFormat foundEvent = calendar.get(index);
        System.out.println("Enter new name of event: ");
        streamersName = sc.nextLine();
        try {
            NullInputExceptionMethod.method(streamersName);
        } catch (NullInputException e) {
            e.printStackTrace();
        }
        FindUserInList findUserInList = new FindUserInList(users, streamersName, "");
        if (!findUserInList.isStreamerInList()) {
            System.out.println("This is not a streamer");

            return;
        }
        foundEvent.setStreamer(streamersName);
        calendar.set(index, foundEvent);
        System.out.println("Streamer is updated");
    }
}
