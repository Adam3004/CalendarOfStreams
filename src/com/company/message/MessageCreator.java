package com.company.message;

import com.company.followingAccessories.Layout;
import com.company.queryExecute.QueryExecute;
import com.company.user.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageCreator {
    public static void sendMessageAboutAdditionOfEvent(List<User> users, String streamerName, Date date, String eventName) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String tittle = "New event has already been added";
        String message = "Streamer " + streamerName + " has already added new event: " + eventName + " on " +
                sdf1.format(date);

        checkingUserToSendMessage(users, streamerName, tittle, message, false);
    }

    public static void sendMessageAboutEditionOfEvent(List<User> users, String streamerName, Date date, String eventName,
                                                      Date previousDate, String previousEventsName) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String tittle = "Event has been edited";
        String message = "Streamer " + streamerName + " has already edited the event: " + eventName + " on " +
                sdf1.format(date) + "\n"
                + "New name of event: " + previousEventsName + " on " + sdf1.format(previousDate);

        checkingUserToSendMessage(users, streamerName, tittle, message, false);
    }

    public static void sendMessageAboutRemovalOfEvent(List<User> users, String streamerName, Date previousDate,
                                                      String previousEventsName) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String tittle = "Event has beed removed";
        String message = "Streamer " + streamerName + " has already removed the event: " + previousEventsName +
                " which was announced on " + sdf1.format(previousDate);
        checkingUserToSendMessage(users, streamerName, tittle, message, false);

    }

    public static void sendMessageToStreamerAboutCreatingEventWithHim(String streamerName, Date date,
                                                                      String eventName) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String tittle = "New event with you has already been added";
        String message = "Moderator has already added new event: " + eventName + " on " +
                sdf1.format(date) + ". You can always remove this event if it is mistake.";

        sendMessageToStreamer(streamerName, tittle, message, false);
    }

    public static void sendMessageQuery(Message newMessage) {
        String receiver = newMessage.getReceiver();
        String tittle = newMessage.getTittle();
        String message = newMessage.getMessage();
        Date sendingDate = newMessage.getDate();
        boolean isViewed = newMessage.isViewed();
        String sender = newMessage.getSender();
        java.sql.Date date = new java.sql.Date(sendingDate.getTime());
        QueryExecute.executeQueryMessages("INSERT INTO public.messages(\"Receiver\", \"Tittle\", \"Message\", \"Viewed\"," +
                " \"Date\", \"Sender\") VALUES ('" + receiver + "','" + tittle + "','" + message + "'," + isViewed + ",'" +
                date + "','" + sender + "')");
    }

    private static void checkingUserToSendMessage(List<User> users, String streamerName,
                                                  String tittle, String message, boolean isViewed) {
        List<String> listOfFollowingStreamers = new ArrayList<>();
        Date sendingDate = new Date();
        String receiver;

        for (User user : users) {
            listOfFollowingStreamers.clear();
            listOfFollowingStreamers = Layout.methodToDisconnect(user.getFollowingStreamers(), listOfFollowingStreamers);
            if (listOfFollowingStreamers.contains(streamerName)) {
                receiver = user.getLogin();
                Message newMessage = new Message(receiver, tittle, message, isViewed, sendingDate, "Generator");
                sendMessageQuery(newMessage);
            }
        }
    }

    private static void sendMessageToStreamer(String streamerName, String tittle, String message, boolean isViewed) {
        String receiver = streamerName;
        Date sendingDate = new Date();

        Message newMessage = new Message(receiver, tittle, message, isViewed, sendingDate, "Generator");
        sendMessageQuery(newMessage);
    }
}
