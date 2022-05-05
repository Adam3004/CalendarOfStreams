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

public class UnfollowStreamerAction implements Action {
    private List<User> users;
    private Scanner sc;
    private Session session;
    private String choice;
    private int numberOfStreamer;

    public UnfollowStreamerAction(List<User> users, Scanner sc, Session session) {
        this.users = users;
        this.sc = sc;
        this.session = session;
    }


    @Override
    public String getActionName() {
        return "Unfollow streamer";
    }

    @Override
    public void execute() throws FileNotFoundException {
        List<String> listOfFollowingStreamers = new ArrayList<>();
        int number = 1;

        listOfFollowingStreamers = Layout.methodToDisconnect(session.getSessionUser().getFollowingStreamers(), listOfFollowingStreamers);
        if (listOfFollowingStreamers.isEmpty()) {
            System.out.println("You do not follow any streamers yet");
        } else {
            System.out.println("Streamers who you follow: ");
            for (String streamer : listOfFollowingStreamers) {
                System.out.println(number + ". " + streamer);
                number += 1;
            }
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
            String followingStreamers = removeStreamer(listOfFollowingStreamers, session.getSessionUser(), users, numberOfStreamer);
            session.getSessionUser().setFollowingStreamers(followingStreamers);
        }
    }


    private static String removeStreamer(List<String> listOfFollowingStreamers, User user, List<User> users, int numberOfStreamer) {
        String followingStreamers;
        int index;

        try {
            listOfFollowingStreamers.remove(numberOfStreamer - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of range");

            return user.getFollowingStreamers();
        }
        followingStreamers = Layout.methodToConnect(listOfFollowingStreamers);
        index = FindUserInList.findIndexInList(users, user.getLogin());
        users.get(index).setFollowingStreamers(followingStreamers);
        System.out.println("Streamer is unfollowed");

        return followingStreamers;
    }

    public static void unfollowRemovedStreamer(String removingStreamer, List<User> users) {
        List<String> listOfFollowingStreamers = new ArrayList<>();
        int number;

        for (User user : users) {
            listOfFollowingStreamers.clear();
            number = listOfFollowingStreamers.size() + 1;
            listOfFollowingStreamers = Layout.methodToDisconnect(user.getFollowingStreamers(), listOfFollowingStreamers);
            for (int i = 0; i < listOfFollowingStreamers.size(); i++) {
                if (listOfFollowingStreamers.get(i).equals(removingStreamer)) {
                    number = i;
                    break;
                }
            }
            if (number < listOfFollowingStreamers.size() + 1) {
                UnfollowStreamerAction.removeStreamer(listOfFollowingStreamers, user, users, number + 1);
            }
        }
    }


    public static void replaceStreamer(String removingStreamer, List<User> users, String newStreamersName) {
        List<String> listOfFollowingStreamers = new ArrayList<>();
        int number;
        int index = 0;

        for (User user : users) {
            listOfFollowingStreamers.clear();
            number = listOfFollowingStreamers.size() + 1;
            listOfFollowingStreamers = Layout.methodToDisconnect(user.getFollowingStreamers(), listOfFollowingStreamers);
            for (int i = 0; i < listOfFollowingStreamers.size(); i++) {
                if (listOfFollowingStreamers.get(i).equals(removingStreamer)) {
                    number = i;
                    break;
                }
            }
            if (number < listOfFollowingStreamers.size() + 1) {
                UnfollowStreamerAction.removeStreamer(listOfFollowingStreamers, user, users, number + 1);
                FollowStreamerAction.replaceStreamer(user, newStreamersName, users, index);
            }
            index += 1;
        }
    }
}
