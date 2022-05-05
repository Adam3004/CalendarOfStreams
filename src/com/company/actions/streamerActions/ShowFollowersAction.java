package com.company.actions.streamerActions;

import com.company.actions.Action;
import com.company.followingAccessories.Layout;
import com.company.session.Session;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ShowFollowersAction implements Action {
    private List<User> users;
    private Session session;
    String streamerName;

    public ShowFollowersAction(List<User> users, Session session) {
        this.users = users;
        this.session = session;
    }

    @Override
    public String getActionName() {
        return "Show followers";
    }

    @Override
    public void execute() throws FileNotFoundException {
        streamerName = session.getSessionUser().getLogin();

        users.stream()
                .filter(this::isFollower)
                .map(u -> u.getLogin() + " Age: " + u.getAge())
                .forEach(System.out::println);
    }

    private boolean isFollower(User u) {
        List<String> listOfFollowingStreamers = new ArrayList<>();
        listOfFollowingStreamers = Layout.methodToDisconnect(u.getFollowingStreamers(), listOfFollowingStreamers);
        return listOfFollowingStreamers.contains(streamerName);
    }
}
