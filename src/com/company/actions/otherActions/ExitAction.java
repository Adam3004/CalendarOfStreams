package com.company.actions.otherActions;

import com.company.actions.Action;
import com.company.calendar.CalendarFormat;
import com.company.saver.Saver;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;

public class ExitAction implements Action {
    private List<User> users;
    private List<CalendarFormat> calendar;

    public ExitAction(List<User> users, List<CalendarFormat> calendar) {
        this.users = users;
        this.calendar = calendar;
    }

    @Override
    public String getActionName() {
        return "Exit";
    }

    @Override
    public void execute() throws FileNotFoundException {
        Saver.saveList(users, "users.txt");
        Saver.saveCalendar(calendar);
        System.exit(0);
    }
}
