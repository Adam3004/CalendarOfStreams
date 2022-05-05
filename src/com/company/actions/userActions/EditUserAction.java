package com.company.actions.userActions;

import com.company.actions.Action;
import com.company.actions.streamerActions.UnfollowStreamerAction;
import com.company.convert.ConvertStringToRole;
import com.company.exceptionMethods.AgeUnderZeroMethod;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptionMethods.WrongConverToRoleMethod;
import com.company.exceptions.AgeUnderZeroException;
import com.company.exceptions.NullInputException;
import com.company.exceptions.WrongConvertToRoleException;
import com.company.findInList.FindUserInList;
import com.company.findInList.IsLoginInList;
import com.company.reader.Reader;
import com.company.role.Role;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class EditUserAction implements Action {
    private Session session;
    private List<User> users;
    private Scanner sc;
    private List<String> listsOfStreamers;
    String enteringLogin;
    String order;
    int index;
    int numberOfOrder;
    boolean isSessionUserChanged = false;
    User changedSessionUser;
    boolean correctPasswords = false;


    public EditUserAction(Session session, List<User> users, Scanner sc, List<String> listsOfStreamers) {
        this.session = session;
        this.users = users;
        this.sc = sc;
        this.listsOfStreamers = listsOfStreamers;
    }


    @Override
    public String getActionName() {
        return "Edit user";
    }


    @Override
    public void execute() throws FileNotFoundException {
        User sessionUser = session.getSessionUser();
        sc.nextLine();
        if (sessionUser.getRole().equals(Role.MOD)) {
            System.out.println("Enter login of user which you would like to edit: ");
            enteringLogin = sc.nextLine();
            if (!IsLoginInList.isLoginInList(users, enteringLogin)) {
                System.out.println("User does not exist");
            } else {
                index = FindUserInList.findIndexInList(users, enteringLogin);
                if (sessionUser.getLogin().equals(users.get(index).getLogin())) {
                    isSessionUserChanged = true;
                }
                while (true) {
                    System.out.println("User's personal data: ");
                    System.out.println("1. Login: " + users.get(index).getLogin());
                    System.out.println("2. Password: " + users.get(index).getPassword());
                    System.out.println("3. Age: " + users.get(index).getAge());
                    System.out.println("4. Role: " + users.get(index).getRole());
                    System.out.println("5. Exit");
                    System.out.println("What u want to edit?: (Choose one from those above and enter a number)");
                    try {
                        order = sc.nextLine();
                        NullInputExceptionMethod.method(order);
                        numberOfOrder = Integer.parseInt(order);
                    } catch (NullInputException e) {

                        continue;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();

                        continue;
                    }
                    switch (numberOfOrder) {
                        case 1:
                            changeLoginMethod(sc, users, index, session);

                            break;
                        case 2:
                            changePasswordByModMethod(sc, users, index);

                            break;
                        case 3:
                            changeAgeMethod(sc, users, index);

                            break;
                        case 4:
                            changeRoleMethod(sc, users, index, session, listsOfStreamers);

                            break;
                        case 5:
                            if (isSessionUserChanged) {
                                changedSessionUser = new User(users.get(index).getLogin(), users.get(index).getPassword(),
                                        users.get(index).getAge(), users.get(index).getRole(), users.get(index).getFollowingStreamers());
                                session.setSessionUser(changedSessionUser);
                            }

                            return;
                        default:
                            System.out.println("Wrong command");
                    }
                }
            }
        } else {
            System.out.println("Your personal data: ");
            System.out.println("Login: " + sessionUser.getLogin());
            System.out.println("Age: " + sessionUser.getAge());
            System.out.println("Role: " + sessionUser.getRole());
            System.out.println("You want to change password? Write \"Y\" or \"N\"");
            order = sc.nextLine();
            switch (order) {
                case "Y":
                    System.out.println("Enter your previous password: ");
                    correctPasswords = passwordCorrectionChecker(sc, sessionUser.getPassword());
                    if (correctPasswords) {
                        changePasswordByViewerMethod(sc, sessionUser, session, users);
                    } else {
                        System.out.println("Wrong password ");
                    }
                case "N":

                    return;
                default:
                    System.out.println("You should write \"Y\" or \"N\"");
            }

        }
    }


    private static void changeLoginMethod(Scanner sc, List<User> users, int index, Session session) {
        String newLogin;
        System.out.println("Enter new login: ");
        try {
            newLogin = sc.nextLine();
            NullInputExceptionMethod.method(newLogin);
        } catch (NullInputException e) {

            return;
        }
        if (IsLoginInList.isLoginInList(users, newLogin)) {
            System.out.println("This login is actually in use");
        } else {
            if (users.get(index).getRole() == Role.STREAMER) {
                UnfollowStreamerAction.replaceStreamer(users.get(index).getLogin(), users, newLogin);
            }
            users.get(index).setLogin(newLogin);
            index = FindUserInList.findIndexInList(users, session.getSessionUser().getLogin());
            session.setSessionUser(users.get(index));
            System.out.println("Changing complete");
        }

    }


    private static void changePasswordByModMethod(Scanner sc, List<User> users, int index) {
        String newPassword;
        System.out.println("Enter new password: ");
        try {
            newPassword = sc.nextLine();
            NullInputExceptionMethod.method(newPassword);
        } catch (NullInputException e) {

            return;
        }
        if (newPassword.equals(users.get(index).getPassword())) {
            System.out.println("Password cannot be the same");
        } else {
            users.get(index).setPassword(newPassword);
            System.out.println("Changing complete");
        }

    }


    private static void changeAgeMethod(Scanner sc, List<User> users, int index) {
        String newAgeString;
        int newAge;
        System.out.println("Enter new age: ");
        try {
            newAgeString = sc.nextLine();
            NullInputExceptionMethod.method(newAgeString);
            newAge = Integer.parseInt(newAgeString);
            AgeUnderZeroMethod.method(newAge);
        } catch (NullInputException | AgeUnderZeroException e) {

            return;
        } catch (NumberFormatException e) {
            System.out.println("It must be a number");

            return;
        }
        users.get(index).setAge(newAge);
        System.out.println("Changing complete");
    }


    private static void changeRoleMethod(Scanner sc, List<User> users, int index, Session session, List<String> listsOfStreamers) {
        String newRoleString;
        Role newRole;
        System.out.println("Enter new role: ");
        try {
            newRoleString = sc.nextLine().toUpperCase();
            NullInputExceptionMethod.method(newRoleString);
            newRole = ConvertStringToRole.convert(newRoleString);
            WrongConverToRoleMethod.method(newRoleString);
        } catch (WrongConvertToRoleException | NullInputException e) {

            return;
        }
        if (users.get(index).getRole() == Role.STREAMER && newRole != Role.STREAMER) {
            UnfollowStreamerAction.unfollowRemovedStreamer(users.get(index).getLogin(), users);
        }
        users.get(index).setRole(newRole);
        if (newRole == Role.STREAMER) {
            listsOfStreamers.add(users.get(index).getLogin());
        }
        index = FindUserInList.findIndexInList(users, session.getSessionUser().getLogin());
        session.setSessionUser(users.get(index));
        System.out.println("Changing complete");
    }


    private static boolean passwordCorrectionChecker(Scanner sc, String checkingPassword) {
        String enteringPassword;
        try {
            enteringPassword = sc.nextLine();
            NullInputExceptionMethod.method(enteringPassword);
        } catch (NullInputException e) {

            return false;
        }

        return enteringPassword.equals(checkingPassword);
    }


    private static void changePasswordByViewerMethod(Scanner sc, User sessionUser, Session session, List<User> users) {
        String newPassword;
        int index;
        boolean correctPasswords = false;
        System.out.println("Enter new password: ");
        newPassword = sc.nextLine();
        try {
            NullInputExceptionMethod.method(newPassword);
        } catch (NullInputException e) {

            return;
        }
        if (newPassword.equals(sessionUser.getPassword())) {
            System.out.println("Password cannot be the same");
        } else {
            System.out.println("Repeat new password: ");
            correctPasswords = passwordCorrectionChecker(sc, newPassword);
            if (correctPasswords) {
                sessionUser.setPassword(newPassword);
                session.setSessionUser(sessionUser);
                index = FindUserInList.findIndexInList(users, sessionUser.getLogin());
                users.get(index).setPassword(newPassword);
                System.out.println("Your password was changed");
            } else {
                System.out.println("Wrong password");
            }
        }
    }
}
