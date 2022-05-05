package com.company.login;

import com.company.actions.*;
import com.company.actions.otherActions.ExitAction;
import com.company.actions.otherActions.LoginAction;
import com.company.actions.otherActions.RegisterAction;
import com.company.actions.otherActions.ShowActionsAction;
import com.company.calendar.CalendarFormat;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Login {
    private List<User> users;
    private Scanner sc;
    private Session session;
    List<Action> actions = new ArrayList<>();
    int order;
    private List<CalendarFormat> calendar;

    public Login(List<User> users, Scanner sc, Session session, List<CalendarFormat> calendar) {
        this.users = users;
        this.sc = sc;
        this.session = session;
        this.calendar = calendar;
    }

    ShowActionsAction showActionsAction = new ShowActionsAction(actions);

    public void initialize() {
        LoginAction loginAction = new LoginAction(users, sc, session);
        RegisterAction registerAction = new RegisterAction(users, sc);
        ExitAction exitAction = new ExitAction(users, calendar);
        actions.add(loginAction);
        actions.add(registerAction);
        actions.add(exitAction);

    }

    public void login() throws FileNotFoundException {
        showActionsAction.execute();
        while (session.getSessionUser() == null) {
            System.out.println("What u want to do? Write 0 if u want to see all operations. Write number of action: ");
            try {
                order = sc.nextInt();
                if (order == 0) {
                    showActionsAction.execute();
                } else {
                    actions.get(order - 1).execute();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number ");
                sc.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Pleas enter number from range 1 to " + actions.size());
            } catch (Exception e) {
                System.out.println("Something went wrong, try again");
            }

        }

    }
}
