package com.company.actions.otherActions;

import com.company.actions.Action;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.findInList.FindUserInList;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class LoginAction implements Action {
    private List<User> users;
    String login;
    String password;
    private Scanner sc;
    boolean logged = false;
    private Session session;

    public LoginAction(List<User> users, Scanner sc, Session session) {
        this.users = users;
        this.sc = sc;
        this.session = session;

    }

    @Override
    public String getActionName() {
        return "Login";
    }

    @Override
    public void execute() throws FileNotFoundException {
        sc.nextLine();
        if (!logged) {
            while (!logged) {
                System.out.println("If you'd like to leave write 'Exit' and press Enter ");
                System.out.println("Enter your login: ");
                try {
                    login = sc.nextLine();
                    NullInputExceptionMethod.method(login);
                } catch (NullInputException e) {

                    return;
                }
                if (login.equals("Exit")) {

                    break;
                } else {
                    System.out.println("Enter your password");
                    try {
                        password = sc.nextLine();
                        NullInputExceptionMethod.method(password);
                    } catch (NullInputException e) {

                        return;
                    }
                    logged = searchUserAndSetHim(users, login, password, session);
                }
            }
        } else {
            System.out.println("You are logged");
        }
    }


    private static boolean searchUserAndSetHim(List<User> users, String login, String password, Session session) {
        User foundUser;
        FindUserInList searchingUser = new FindUserInList(users, login, password);
        foundUser = searchingUser.findUserInList();
        if (foundUser != null) {
            System.out.println("You are logged");
            session.setSessionUser(foundUser);

            return true;
        } else {
            System.out.println("Your login or password is wrong, try again");

            return false;
        }
    }
}
