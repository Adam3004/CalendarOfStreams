package com.company.forwardingToMenu;

import com.company.calendar.CalendarFormat;
import com.company.menus.Menu;
import com.company.menus.ModMenu;
import com.company.menus.StreamerMenu;
import com.company.menus.ViewerMenu;
import com.company.role.Role;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Forwarding {
    private User user;
    private List<Role> roles;
    Role foundRole;
    List<Menu> menus = new ArrayList<>();
    private Session session;
    private List<User> users;
    private List<CalendarFormat> calendar;
    private List<String> listsOfStreamers;

    public Forwarding(User user, List<Role> roles, Session session, List<User> users, List<CalendarFormat> calendar,
                      List<String> listsOfStreamers) {
        this.user = user;
        this.roles = roles;
        this.session = session;
        this.users = users;
        this.calendar = calendar;
        this.listsOfStreamers=listsOfStreamers;
    }

    public void initialize() {
        StreamerMenu streamerMenu = new StreamerMenu(users, session, calendar,listsOfStreamers);
        ModMenu modMenu = new ModMenu(users, session, calendar,listsOfStreamers);
        ViewerMenu viewerMenu = new ViewerMenu(users, session, calendar,listsOfStreamers);
        menus.add(streamerMenu);
        menus.add(modMenu);
        menus.add(viewerMenu);
    }

    public void forwarding() throws FileNotFoundException {
        for (Role role : roles) {
            if (user.getRole() == role) {
                foundRole = user.getRole();

                break;
            }
        }

        for (Menu menu : menus) {
            if (foundRole == menu.role()) {
                menu.execute();
            }
        }

    }

}
