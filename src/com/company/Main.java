package com.company;

import com.company.additional.AddDaysToCalendar;
import com.company.calendar.CalendarFormat;
import com.company.forwardingToMenu.Forwarding;
import com.company.login.Login;
import com.company.reader.Reader;
import com.company.role.Role;
import com.company.role.Roles;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        List<String> listsOfStreamers = new ArrayList<>();
        List<CalendarFormat> calendar = new ArrayList<>();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyy-MM-dd HH:mm");
        System.out.println(sdf1.format(new Date()));
        Scanner sc = new Scanner(System.in);
        Reader.readListOfUsers(users, "users.txt");
        Reader.readCalendarFromDatebase(calendar);
        listsOfStreamers = Reader.readListOfStreamers(listsOfStreamers, users);
        AddDaysToCalendar.method(calendar);
        Session session = new Session();
        Login login = new Login(users, sc, session, calendar);
        login.initialize();
        login.login();
        User sessionUser = session.getSessionUser();
        Roles listOfRules = new Roles(roles);
        listOfRules.roles();
        Forwarding forwarding = new Forwarding(sessionUser, roles, session, users, calendar, listsOfStreamers);
        forwarding.initialize();
        forwarding.forwarding();
    }
}

