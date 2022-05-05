package com.company.menus;

import com.company.calendar.CalendarFormat;
import com.company.menusCode.ModMenuCode;
import com.company.role.Role;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;

public class ModMenu implements Menu {
    private List<User> users;
    private Session session;
    private List<CalendarFormat> calendar;
    private List<String> listsOfStreamers;

    public ModMenu(List<User> users, Session session, List<CalendarFormat> calendar, List<String> listsOfStreamers) {
        this.users = users;
        this.session = session;
        this.calendar = calendar;
        this.listsOfStreamers = listsOfStreamers;
    }

    @Override
    public String getMenusName() {
        return "Mod Menu";
    }

    @Override
    public Role role() {
        return Role.MOD;
    }

    @Override
    public void execute() throws FileNotFoundException {
        ModMenuCode runner = new ModMenuCode(users, session, calendar, listsOfStreamers);
        runner.initialize();
        runner.menu();
    }
}
