package com.company.findInList;

import com.company.role.Role;
import com.company.user.User;

import java.util.List;

public class FindUserInList {
    private List<User> users;
    private String login;
    private String password;

    public User findUserInList() {
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                if (password.equals(user.getPassword())) {

                    return user;
                }
            }
        }

        return null;
    }

    public boolean isStreamerInList() {
        for (User user : users) {
            if (user.getRole() == Role.STREAMER)
                if (login.equals(user.getLogin())) {

                    return true;
                }
        }

        return false;
    }

    public static int findIndexInList(List<User> users, String login) {
        for (int i = 0; i < users.size(); i++) {
            if (login.equals(users.get(i).getLogin())) {

                return i;
            }
        }

        return -1;
    }

    public FindUserInList(List<User> users, String login, String password) {
        this.users = users;
        this.login = login;
        this.password = password;
    }
}