package com.company.actions.streamerActions;

import com.company.actions.Action;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.message.Message;
import com.company.message.MessageCreator;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SendMessageAction implements Action {
    private List<User> users;
    private Scanner sc;
    private Session session;
    String receiver;
    String tittle;
    String message;

    public SendMessageAction(List<User> users, Scanner sc, Session session) {
        this.users = users;
        this.sc = sc;
        this.session = session;
    }

    @Override
    public String getActionName() {
        return "Send message";
    }

    @Override
    public void execute() throws FileNotFoundException {
        receiver = receiverChoice(sc, users);
        if (receiver == null) {

            return;
        }
        tittle = enterTittle(sc);
        if (tittle == null) {

            return;
        }
        message = enterMessage(sc);
        if (message == null) {

            return;
        }
        messageCreator(receiver, tittle, message, session);
    }

    private static String receiverChoice(Scanner sc, List<User> users) {
        String receiver;

        sc.nextLine();
        System.out.println("Enter receiver:");
        receiver = sc.nextLine();
        try {
            NullInputExceptionMethod.method(receiver);
        } catch (NullInputException e) {
            System.out.println("It cannot be empty");

            return null;
        }
        if (!isReceiverInUsers(users, receiver)) {

            return null;
        }
        return receiver;
    }

    private static boolean isReceiverInUsers(List<User> users, String receiver) {
        for (User checkingUser : users) {
            if (checkingUser.getLogin().equals(receiver)) {

                return true;
            }
        }

        return false;
    }

    private static String enterTittle(Scanner sc) {
        String tittle;

        System.out.println("Enter tittle of your message: ");
        tittle = sc.nextLine();
        try {
            NullInputExceptionMethod.method(tittle);
        } catch (NullInputException e) {
            System.out.println("It cannot be empty");

            return null;
        }

        return tittle;
    }

    private static String enterMessage(Scanner sc) {
        String message;

        System.out.println("Enter your message: ");
        message = sc.nextLine();
        try {
            NullInputExceptionMethod.method(message);
        } catch (NullInputException e) {
            System.out.println("It cannot be empty");

            return null;
        }

        return message;
    }

    private static void messageCreator(String receiver, String tittle, String message, Session session) {
        Date date = new Date();
        String sender = session.getSessionUser().getLogin();
        Message newMessage = new Message(receiver, tittle, message, false, date, sender);
        MessageCreator.sendMessageQuery(newMessage);
        System.out.println("Message has been sent");
    }
}
