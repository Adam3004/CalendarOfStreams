package com.company.actions.userActions;

import com.company.actions.Action;
import com.company.filtration.UsersFiltration;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class PrintUsersAction implements Action {
    private List<User> users;
    private Scanner sc;
    String order;

    public PrintUsersAction(List<User> users, Scanner sc) {
        this.users = users;
        this.sc = sc;
    }

    @Override
    public String getActionName() {
        return "Print all users";
    }

    @Override
    public void execute() throws FileNotFoundException {
        if (users.size() == 0) {
            System.out.println("List of users is empty");
        } else {
            users.stream()
                    .map(PrintUsersAction::printer)
                    .forEach(System.out::println);
            sc.nextLine();
            System.out.println("Do you want to filter results?: (Y/N)");
            order = sc.nextLine();
            if (order.equals("Y")) {
                UsersFiltration.method(users, sc);
            }
        }
    }

    private static String printer(User user) {
        if (user.getFollowingStreamers().isEmpty()) {
            return user.getLogin() + " " + user.getPassword() + " " + user.getAge() + " " + user.getRole() + " there is not any following streamers yet";
        } else {
            return user.getLogin() + " " + user.getPassword() + " " + user.getAge() + " " + user.getRole() + " " + user.getFollowingStreamers();
        }
    }
}
