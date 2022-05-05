package com.company.menusCode;

import com.company.actions.*;
import com.company.actions.eventActions.AddEventAction;
import com.company.actions.eventActions.EditEventAction;
import com.company.actions.eventActions.PrintCalendarAction;
import com.company.actions.eventActions.RemoveEventAction;
import com.company.actions.otherActions.ExitAction;
import com.company.actions.otherActions.ShowActionsAction;
import com.company.actions.streamerActions.*;
import com.company.actions.userActions.*;
import com.company.calendar.CalendarFormat;
import com.company.queries.PrintAllUsersQuery;
import com.company.queries.PrintCalendarQuery;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ModMenuCode {
    private List<User> users;
    Scanner sc = new Scanner(System.in);
    List<Action> actions = new ArrayList<>();
    int order;
    private Session session;
    private List<CalendarFormat> calendar;
    private List<String> listsOfStreamers;

    public ModMenuCode(List<User> users, Session session, List<CalendarFormat> calendar, List<String> listsOfStreamers) {
        this.users = users;
        this.session = session;
        this.calendar = calendar;
        this.listsOfStreamers = listsOfStreamers;
    }

    public void initialize() {
        SaveListOfUsersInDateBaseAction saveListInDateBaseAction = new SaveListOfUsersInDateBaseAction(users);
        PrintUsersAction printUsersAction = new PrintUsersAction(users, sc);
        PrintAllUsersQuery printAllUsersQuery = new PrintAllUsersQuery();
        PrintCalendarQuery printCalendarQuery = new PrintCalendarQuery(sc);
        PrintCalendarAction printCalendarAction = new PrintCalendarAction(calendar, sc);
        AddEventAction addEventAction = new AddEventAction(calendar, users, sc, session);
        EditEventAction editEventAction = new EditEventAction(sc, calendar, users);
        RemoveEventAction removeEventAction = new RemoveEventAction(sc, calendar, users);
        AddUserAction addUserAction = new AddUserAction(users, sc, listsOfStreamers);
        RemoveUserAction removeUserAction = new RemoveUserAction(sc, users, session);
        EditUserAction editUserAction = new EditUserAction(session, users, sc, listsOfStreamers);
        ShowStreamersAction showStreamersAction = new ShowStreamersAction(listsOfStreamers);
        SendMessageAction sendMessageAction = new SendMessageAction(users, sc, session);
        CollectMessageAction collectMessageAction = new CollectMessageAction(session, sc);
        ExitAction exitAction = new ExitAction(users, calendar);
        actions.add(printUsersAction);
        actions.add(saveListInDateBaseAction);
        actions.add(printAllUsersQuery);
        actions.add(printCalendarQuery);
        actions.add(printCalendarAction);
        actions.add(addEventAction);
        actions.add(editEventAction);
        actions.add(removeEventAction);
        actions.add(addUserAction);
        actions.add(removeUserAction);
        actions.add(editUserAction);
        actions.add(showStreamersAction);
        actions.add(sendMessageAction);
        actions.add(collectMessageAction);
        actions.add(exitAction);
    }

    ShowActionsAction showActionsAction = new ShowActionsAction(actions);

    public void menu() throws FileNotFoundException {
        showActionsAction.execute();
        while (true) {
            System.out.println("What u want to do? Write 0 if u want to see all operations. Write number of action: ");
            try {
                order = sc.nextInt();
                if (order == 0) {
                    showActionsAction.execute();
                } else {
                    actions.get(order - 1).execute();
                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("Please enter a number ");
                sc.nextLine();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println("Pleas enter number from range 1 to " + actions.size());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Something went wrong, try again");
            }
        }
    }
}
