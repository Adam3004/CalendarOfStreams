package com.company.menus;

import com.company.calendar.CalendarFormat;
import com.company.menusCode.ViewerMenuCode;
import com.company.role.Role;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;

public class ViewerMenu implements Menu {
    private List<User> users;
    private Session session;
    private List<CalendarFormat> calendar;
    private List<String> listsOfStreamers;

    public ViewerMenu(List<User> users, Session session, List<CalendarFormat> calendar, List<String> listsOfStreamers) {
        this.users = users;
        this.session = session;
        this.calendar = calendar;
        this.listsOfStreamers = listsOfStreamers;
    }

    @Override
    public String getMenusName() {
        return "Viewer Menu";
    }

    @Override
    public Role role() {
        return Role.VIEWER;
    }

    @Override
    public void execute() throws FileNotFoundException {
        ViewerMenuCode runner = new ViewerMenuCode(users, session, calendar, listsOfStreamers);
        runner.initialize();
        runner.menu();
    }
}
