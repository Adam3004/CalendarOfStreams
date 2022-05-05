package com.company.menus;

import com.company.calendar.CalendarFormat;
import com.company.menusCode.StreamerMenuCode;
import com.company.role.Role;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;

public class StreamerMenu implements Menu {
    private List<User> users;
    private Session session;
    private List<CalendarFormat> calendar;
    private List<String> listsOfStreamers;

    public StreamerMenu(List<User> users, Session session, List<CalendarFormat> calendar, List<String> listsOfStreamers) {
        this.users = users;
        this.session = session;
        this.calendar = calendar;
        this.listsOfStreamers = listsOfStreamers;
    }

    @Override
    public String getMenusName() {
        return "Streamer Menu";
    }

    @Override
    public Role role() {
        return Role.STREAMER;
    }

    @Override
    public void execute() throws FileNotFoundException {
        StreamerMenuCode runner = new StreamerMenuCode(users, session, calendar, listsOfStreamers);
        runner.initialize();
        runner.menu();
    }
}
