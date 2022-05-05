package com.company.findInList;

import com.company.user.User;

import java.util.List;

public class IsLoginInList {


    public static boolean isLoginInList(List<User> users, String login) {
        for (User user : users) {
            if (login.equals(user.getLogin())) {

                return true;
            }
        }

        return false;
    }


}
