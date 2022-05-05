package com.company.actions.streamerActions;

import com.company.actions.Action;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.message.DeleteMessage;
import com.company.message.Message;
import com.company.queryExecute.QueryExecute;
import com.company.session.Session;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CollectMessageAction implements Action {
    private Session session;
    private Scanner sc;
    List<Message> messagesList = new ArrayList<>();
    List<Message> inbox = new ArrayList<>();

    public CollectMessageAction(Session session, Scanner sc) {
        this.session = session;
        this.sc = sc;
    }

    @Override
    public String getActionName() {
        return "Read messages";
    }

    @Override
    public void execute() throws FileNotFoundException {
        inbox.clear();
        messagesList.clear();

        findMessages(messagesList);
        if (messagesList.isEmpty()) {
            System.out.println("You have not any new messages");

            return;
        }
        inbox = inboxCollector(messagesList, session, false);
        sc.nextLine();
        if (inbox.isEmpty()) {
            System.out.println("You do not have any new messages now");
        } else if (!inbox.isEmpty()) {
            choiceOfMessage(sc, inbox, true);
        }
        whatToDoNextChoice(sc, messagesList, session);
    }

    private static List<Message> findMessages(List<Message> messagesList) {
        try {
            ResultSet resultSet = QueryExecute.executeSelectMessages("SELECT * FROM public.messages");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH-mm");
            while (!resultSet.isLast()) {
                resultSet.next();
                String receiver = resultSet.getString("Receiver");
                String tittle = resultSet.getString("Tittle");
                String contentOfMessage = resultSet.getString("Message");
                Date getDate = resultSet.getDate("Date");
                String dateString = sdf1.format(getDate);
                java.util.Date sendingDate = sdf1.parse(dateString);
                boolean isViewed = resultSet.getBoolean("Viewed");
                String sender = resultSet.getString("Sender");
                Message message = new Message(receiver, tittle, contentOfMessage, isViewed, sendingDate, sender);
                messagesList.add(message);
            }

            return messagesList;
        } catch (SQLException | ParseException e) {

            return null;
        }
    }

    private static void choiceOfMessage(Scanner sc, List<Message> inbox, boolean isViewedActivator) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        String choice;
        int numberOfMessage;

        System.out.println("Which message you want to read? Enter a number: ");
        choice = sc.nextLine();
        try {
            NullInputExceptionMethod.method(choice);
            numberOfMessage = Integer.parseInt(choice);
        } catch (NullInputException | NumberFormatException e) {
            e.printStackTrace();

            return;
        }
        if (numberOfMessage <= inbox.size()) {
            numberOfMessage = numberOfMessage - 1;
            System.out.println("Message from: " + inbox.get(numberOfMessage).getSender());
            System.out.println(inbox.get(numberOfMessage).getTittle());
            System.out.println(sdf2.format(inbox.get(numberOfMessage).getDate()));
            System.out.println(inbox.get(numberOfMessage).getMessage());
            System.out.println("");
            if (isViewedActivator) {
                isViewed(inbox, numberOfMessage, sdf1);
            }
        } else {
            System.out.println("Wrong number");
        }
    }

    private static void isViewed(List<Message> inbox, int numberOfMessage, SimpleDateFormat sdf1) {
        inbox.get(numberOfMessage).setViewed(true);
        Message updatingMessage = inbox.get(numberOfMessage);
        QueryExecute.executeQueryMessages("UPDATE public.messages SET \"Viewed\"=true WHERE \"Receiver\"='" +
                updatingMessage.getReceiver() + "' AND \"Tittle\"='" + updatingMessage.getTittle() + "' AND \"Date\"='" +
                sdf1.format(updatingMessage.getDate()) + "';");
    }

    private static void whatToDoNextChoice(Scanner sc, List<Message> messagesList, Session session) {
        String order;
        int numberOfOrder;
        List<Message> inbox = new ArrayList<>();
        while (true) {
            messagesList.clear();
            inbox.clear();
            findMessages(messagesList);
            System.out.println("What u want to do?: ");
            System.out.println("1. Read more new messages");
            System.out.println("2. Read viewed messages");
            System.out.println("3. Remove viewed messages");
            System.out.println("4. Exit");
            order = sc.nextLine();
            try {
                NullInputExceptionMethod.method(order);
                numberOfOrder = Integer.parseInt(order);
            } catch (NullInputException | NumberFormatException e) {
                e.printStackTrace();

                return;
            }
            switch (numberOfOrder) {
                case 1:
                    inbox = inboxCollector(messagesList, session, false);
                    if (inbox.isEmpty()) {
                        System.out.println("You do not have any new messages now");

                        break;
                    }
                    choiceOfMessage(sc, inbox, true);

                    break;
                case 2:
                    inbox = inboxCollector(messagesList, session, true);
                    if (inbox.isEmpty()) {
                        System.out.println("You do not have any viewed messages now");

                        break;
                    }
                    choiceOfMessage(sc, inbox, false);

                    break;
                case 3:
                    inbox = inboxCollector(messagesList, session, true);
                    DeleteMessage.remover(inbox);

                    break;
                case 4:

                    return;
                default:
                    System.out.println("Wrong number");


            }
        }
    }

    private static List<Message> inboxCollector(List<Message> messagesList, Session session, boolean viewed) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        List<Message> inbox;
        int number = 1;

        if (!viewed) {
            inbox = messagesList.stream()
                    .filter(message -> message.getReceiver().equals(session.getSessionUser().getLogin()))
                    .filter(message -> !message.isViewed())
                    .collect(Collectors.toList());
        } else {
            inbox = messagesList.stream()
                    .filter(message -> message.getReceiver().equals(session.getSessionUser().getLogin()))
                    .filter(Message::isViewed)
                    .collect(Collectors.toList());
        }
        System.out.println("There are your messages: ");
        for (Message message : inbox) {
            System.out.println(number + ". " + message.getTittle() + " | sending date: " + sdf2.format(message.getDate()));
            number += 1;
        }

        return inbox;
    }

}
