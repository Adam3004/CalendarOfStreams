package com.company.actions.streamerActions;

import com.company.actions.Action;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.findInList.FindUserInList;
import com.company.followingAccessories.Layout;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FollowStreamerAction implements Action {
    private List<String> listsOfStreamers;
    private List<User> users;
    private Scanner sc;
    private Session session;

    public FollowStreamerAction(List<String> listsOfStreamers, Scanner sc, Session session, List<User> users) {
        this.listsOfStreamers = listsOfStreamers;
        this.sc = sc;
        this.session = session;
        this.users = users;
    }

    @Override
    public String getActionName() {

        return "Follow streamer";
    }

    @Override
    public void execute() throws FileNotFoundException {
        int number = 1;
        System.out.println("Streamers: ");
        for (String streamer : listsOfStreamers) {
            System.out.println(number + ". " + streamer);
            number += 1;
        }
        additionOfStreamToUser(sc, session, users, listsOfStreamers);
    }

    private static void additionOfStreamToUser(Scanner sc, Session session, List<User> users, List<String> listsOfStreamers) {
        List<String> listOfFollowingStreamers = new ArrayList<>();
        String choice;
        int numberOfStreamer;
        int index;
        String followingStreamers;
        boolean isStreamerOnList;

        sc.nextLine();
        System.out.println("Choose number of streamer who you want to follow: ");
        choice = sc.nextLine();
        try {
            NullInputExceptionMethod.method(choice);
            numberOfStreamer = Integer.parseInt(choice);
        } catch (NullInputException | NumberFormatException e) {
            e.printStackTrace();

            return;
        }
        listOfFollowingStreamers = Layout.methodToDisconnect(session.getSessionUser().getFollowingStreamers(), listOfFollowingStreamers);
        try {
            isStreamerOnList = isStreamerOnList(listOfFollowingStreamers, listsOfStreamers, numberOfStreamer);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of range");

            return;
        }
        if (!isStreamerOnList) {
            index = FindUserInList.findIndexInList(users, session.getSessionUser().getLogin());
            followingStreamers = followingStreamerEditor(session.getSessionUser(), listsOfStreamers.get(numberOfStreamer - 1));
            users.get(index).setFollowingStreamers(followingStreamers);
            session.getSessionUser().setFollowingStreamers(followingStreamers);
            System.out.println("Streamer is followed");
        } else {
            System.out.println("You have already followed this streamer");
        }
    }


    private static String followingStreamerEditor(User user, String streamersName) {
        String newFollowingStreamers;
        List<String> listOfFollowingStreamers = new ArrayList<>();
        String currentFollowingStreamers = user.getFollowingStreamers();

        listOfFollowingStreamers = Layout.methodToDisconnect(currentFollowingStreamers, listOfFollowingStreamers);
        listOfFollowingStreamers.add(streamersName);
        newFollowingStreamers = Layout.methodToConnect(listOfFollowingStreamers);

        return newFollowingStreamers;
    }


    private static boolean isStreamerOnList(List<String> listOfFollowingStreamers, List<String> listsOfStreamers,
                                            int numberOfStreamer) {

        return listOfFollowingStreamers.contains(listsOfStreamers.get(numberOfStreamer - 1));
    }

    public static void replaceStreamer(User user, String stremaersName, List<User> users, int index) {
        String followingStreamers = followingStreamerEditor(user, stremaersName);
        users.get(index).setFollowingStreamers(followingStreamers);
    }
}
