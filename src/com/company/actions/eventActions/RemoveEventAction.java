package com.company.actions.eventActions;

import com.company.actions.Action;
import com.company.calendar.CalendarFormat;
import com.company.date.DateEntering;
import com.company.eventChoiceAccessories.ChoiceOfEvent;
import com.company.message.MessageCreator;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RemoveEventAction implements Action {
    private Scanner sc;
    private List<CalendarFormat> calendar;
    private List<User> users;
    Date date;
    boolean foundEvent = false;
    int index;
    CalendarFormat removingEvent;

    public RemoveEventAction(Scanner sc, List<CalendarFormat> calendar, List<User> users) {
        this.sc = sc;
        this.calendar = calendar;
        this.users = users;
    }

    @Override
    public String getActionName() {
        return "Remove event";
    }

    @Override
    public void execute() throws FileNotFoundException {
        sc.nextLine();
        System.out.println("Enter date and choose event to remove");
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
        removingEvent = calendar.get(index);
        removingEvent(index, calendar, date);
        MessageCreator.sendMessageAboutRemovalOfEvent(users, removingEvent.getStreamer(), removingEvent.getDate(),
                removingEvent.getEvent());
        System.out.println("Event was removed");
    }


    public static void removingEvent(int index, List<CalendarFormat> calendar, Date date) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        if ((index == calendar.size() - 1 && !sdf2.format(calendar.get(index - 1).getDate()).equals(sdf2.format(date))) ||
                (index == 0 && !sdf2.format(calendar.get(index + 1).getDate()).equals(sdf2.format(date))) || (index > 0 &&
                index < calendar.size() - 1 && !sdf2.format(calendar.get(index - 1).getDate()).equals(sdf2.format(date)) &&
                !sdf2.format(calendar.get(index + 1).getDate()).equals(sdf2.format(date)))) {
            String dateString = sdf2.format(date) + " 00:00";
            Date newDate = null;
            try {
                newDate = sdf2.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.set(index, new CalendarFormat(newDate, "null", "null"));
        } else {
            calendar.remove(index);
        }
    }
}
