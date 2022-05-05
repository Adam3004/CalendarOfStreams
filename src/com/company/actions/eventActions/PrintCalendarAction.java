package com.company.actions.eventActions;

import com.company.actions.Action;
import com.company.calendar.CalendarFormat;
import com.company.filtration.EventsFiltration;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class PrintCalendarAction implements Action {
    private List<CalendarFormat> calendar;
    String order;
    private Scanner sc;

    public PrintCalendarAction(List<CalendarFormat> calendar, Scanner sc) {
        this.calendar = calendar;
        this.sc = sc;
    }

    @Override
    public String getActionName() {
        return "Print calendar";
    }

    @Override
    public void execute() throws FileNotFoundException {
        if (calendar.size() == 0) {
            System.out.println("Calendar is empty");
        } else {
            calendar.stream()
                    .map(PrintCalendarAction::isEventNull)
                    .forEach(System.out::println);
            sc.nextLine();
            System.out.println("Do you want to filter results?: (Y/N)");
            order = sc.nextLine();
            if (order.equals("Y")) {
                EventsFiltration.method(sc, calendar);
            }
        }
    }

    public static String isEventNull(CalendarFormat event) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (event.getEvent() == null || event.getEvent().equals("null")) {
            return sdf1.format(event.getDate()) + " no events that day";
        } else {
            return sdf1.format(event.getDate()) + " " + event.getEvent() + " " + event.getStreamer();
        }
    }
}
