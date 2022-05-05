package com.company.actions.otherActions;

import com.company.actions.Action;
import com.company.exceptionMethods.AgeUnderZeroMethod;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptions.AgeUnderZeroException;
import com.company.exceptions.NullInputException;
import com.company.findInList.IsLoginInList;
import com.company.role.Role;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class RegisterAction implements Action {
    private List<User> users;
    private Scanner sc;
    String login;
    String password;
    int age;

    public RegisterAction(List<User> users, Scanner sc) {
        this.users = users;
        this.sc = sc;
    }


    @Override
    public String getActionName() {
        return "Register";
    }

    @Override
    public void execute() throws FileNotFoundException {
        sc.nextLine();
        System.out.println("Enter your new login: ");
        try {
            login = sc.nextLine();
            NullInputExceptionMethod.method(login);
        } catch (NullInputException e) {

            return;
        }
        if (IsLoginInList.isLoginInList(users, login)) {
            System.out.println("This login is actually in use");

            return;
        }
        password = enterPassword(sc);
        if (password == null) {
            System.out.println("Passwords are different or empty");

            return;
        }
        age = enterAge(sc);
        if (age == -1) {

            return;
        }
        User newUser = new User(login, password, age, Role.VIEWER,"");
        users.add(newUser);
        System.out.println("Registration complete. You can log in now");

    }


    private static String enterPassword(Scanner sc) {
        String newPassword;
        boolean correctPasswords;
        System.out.println("Enter new password: ");
        newPassword = sc.nextLine();
        try {
            NullInputExceptionMethod.method(newPassword);
        } catch (NullInputException e) {

            return null;
        }
        System.out.println("Repeat new password: ");
        correctPasswords = passwordCorrectionChecker(sc, newPassword);
        if (correctPasswords) {

            return newPassword;
        } else {
            System.out.println("Wrong password");
        }

        return null;
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

    private static int enterAge(Scanner sc) {
        int age;
        System.out.println("Enter your age: ");
        try {
            String ageString = sc.nextLine();
            NullInputExceptionMethod.method(ageString);
            age = Integer.parseInt(ageString);
            AgeUnderZeroMethod.method(age);
        } catch (NullInputException e) {

            return -1;
        } catch (AgeUnderZeroException e) {

            return -1;
        } catch (NumberFormatException e) {
            System.out.println("You must enter a number ");

            return -1;
        } catch (Exception e) {
            System.out.println("Error");

            return -1;
        }

        return age;
    }
}


