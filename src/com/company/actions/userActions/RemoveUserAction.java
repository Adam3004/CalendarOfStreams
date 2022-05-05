package com.company.actions.userActions;

import com.company.actions.Action;
import com.company.actions.streamerActions.UnfollowStreamerAction;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.NullInputException;
import com.company.findInList.FindUserInList;
import com.company.findInList.IsLoginInList;
import com.company.followingAccessories.Layout;
import com.company.role.Role;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveUserAction implements Action {
    private Scanner sc;
    private List<User> users;
    private Session session;
    String login;
    int index;

    public RemoveUserAction(Scanner sc, List<User> users, Session session) {
        this.sc = sc;
        this.users = users;
        this.session = session;
    }

    @Override
    public String getActionName() {
        return "Remove user";
    }

    @Override
    public void execute() throws FileNotFoundException {
        sc.nextLine();
        System.out.println("Enter user's login: ");
        try {
            login = sc.nextLine();
            NullInputExceptionMethod.method(login);
        } catch (NullInputException e) {

            return;
        }

        if (!IsLoginInList.isLoginInList(users, login)) {
            System.out.println("This user does not exist ");
        } else {
            index = FindUserInList.findIndexInList(users, login);
            if (users.get(index).getRole() == Role.STREAMER) {
                UnfollowStreamerAction.unfollowRemovedStreamer(login, users);
            }
            users.remove(index);
            System.out.println("User was remover");
            index = FindUserInList.findIndexInList(users, session.getSessionUser().getLogin());
            session.setSessionUser(users.get(index));
        }

    }

}

