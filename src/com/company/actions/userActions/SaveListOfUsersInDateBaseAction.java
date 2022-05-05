package com.company.actions.userActions;

import com.company.actions.Action;
import com.company.queryExecute.QueryExecute;
import com.company.role.Role;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;

public class SaveListOfUsersInDateBaseAction implements Action {
    private List<User> users;
    String login;
    String password;
    int age;
    Role role;
    String followingSteamers;

    public SaveListOfUsersInDateBaseAction(List<User> users) {
        this.users = users;
    }

    @Override
    public String getActionName() {
        return "Save list of users in datebase";

    }

    @Override
    public void execute() throws FileNotFoundException {
        QueryExecute.executeQueryUsers("DELETE FROM public.users");
        for (User user : users) {
            login = user.getLogin();
            password = user.getPassword();
            age = user.getAge();
            role = user.getRole();
            followingSteamers = user.getFollowingStreamers();

            QueryExecute.executeQueryUsers("INSERT INTO public.users(\"LOGIN\", \"PASSWORD\", \"AGE\", \"ROLE\", \"FollowingStreamers\") VALUES " +
                    "('" + login + "','" + password + "'," + age + ",'" + role+ "','" +followingSteamers + "');");
        }
        System.out.println("Users are saved");
    }
}
