package com.company.menusCode;

import com.company.actions.Action;
import com.company.actions.eventActions.PrintCalendarAction;
import com.company.actions.otherActions.ExitAction;
import com.company.actions.otherActions.ShowActionsAction;
import com.company.actions.streamerActions.*;
import com.company.calendar.CalendarFormat;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ViewerMenuCode {
    private List<User> users;
    private Session session;
    private List<CalendarFormat> calendar;
    private List<String> listsOfStreamers;
    Scanner sc = new Scanner(System.in);
    List<Action> actions = new ArrayList<>();
    int order;

    public ViewerMenuCode(List<User> users, Session session, List<CalendarFormat> calendar, List<String> listsOfStreamers) {
        this.users = users;
        this.session = session;
        this.calendar = calendar;
        this.listsOfStreamers = listsOfStreamers;
    }

    public void initialize() {
        PrintCalendarAction printCalendarAction = new PrintCalendarAction(calendar, sc);
        ShowStreamersAction showStreamersAction = new ShowStreamersAction(listsOfStreamers);
        ShowFollowingStreamers showFollowingStreamers = new ShowFollowingStreamers(session);
        FollowStreamerAction followStreamerAction = new FollowStreamerAction(listsOfStreamers, sc, session, users);
        UnfollowStreamerAction unfollowStreamerAction = new UnfollowStreamerAction(users, sc, session);
        SendMessageAction sendMessageAction = new SendMessageAction(users, sc, session);
        CollectMessageAction collectMessageAction = new CollectMessageAction(session, sc);
        ExitAction exitAction = new ExitAction(users, calendar);
        actions.add(printCalendarAction);
        actions.add(showStreamersAction);
        actions.add(showFollowingStreamers);
        actions.add(followStreamerAction);
        actions.add(unfollowStreamerAction);
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
