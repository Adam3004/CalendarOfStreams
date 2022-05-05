package com.company.actions.userActions;

import com.company.actions.Action;
import com.company.convert.ConvertStringToRole;
import com.company.exceptionMethods.AgeUnderZeroMethod;
import com.company.exceptionMethods.NullInputExceptionMethod;
import com.company.exceptionMethods.WrongConverToRoleMethod;
import com.company.exceptions.AgeUnderZeroException;
import com.company.exceptions.NullInputException;
import com.company.exceptions.WrongConvertToRoleException;
import com.company.findInList.IsLoginInList;
import com.company.role.Role;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AddUserAction implements Action {
    private List<User> users;
    private Scanner sc;
    private List<String> listsOfStreamers;
    String login;
    String password;
    int age;
    String roleString;
    Role role;

    public AddUserAction(List<User> users, Scanner sc, List<String> listsOfStreamers) {
        this.users = users;
        this.sc = sc;
        this.listsOfStreamers = listsOfStreamers;
    }

    @Override
    public String getActionName() {
        return "Add new user";
    }

    @Override
    public void execute() throws FileNotFoundException {
        sc.nextLine();
        System.out.println("Enter new user's login: ");
        try {
            login = sc.nextLine();
            NullInputExceptionMethod.method(login);
        } catch (NullInputException e) {

            return;
        }
        if (IsLoginInList.isLoginInList(users, login)) {
            System.out.println("This login is actually in use");
        } else {
            System.out.println("Enter new user's password: ");
            try {
                password = sc.nextLine();
                NullInputExceptionMethod.method(password);
            } catch (NullInputException e) {

                return;
            }

            System.out.println("Enter new user's age: ");
            try {
                String ageString = sc.nextLine();
                NullInputExceptionMethod.method(ageString);
                age = Integer.parseInt(ageString);
                AgeUnderZeroMethod.method(age);
            } catch (NullInputException | AgeUnderZeroException e) {

                return;
            } catch (Exception e) {
                System.out.println("Error");

                return;
            }
            System.out.println("Enter new user's role: (use only uppercase)");
            roleString = sc.nextLine().toUpperCase();
            try {
                role = ConvertStringToRole.convert(roleString);
                WrongConverToRoleMethod.method(roleString);
            } catch (WrongConvertToRoleException e) {

                return;
            } catch (Exception e) {
                System.out.println("Error");

                return;
            }
            User newUser = new User(login, password, age, role, "");
            users.add(newUser);
            if (newUser.getRole() == Role.STREAMER) {
                listsOfStreamers.add(newUser.getLogin());
            }
            System.out.println("Addition complete");
        }
    }
}
