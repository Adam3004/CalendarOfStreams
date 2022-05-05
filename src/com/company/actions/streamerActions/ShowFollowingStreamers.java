package com.company.actions.streamerActions;

import com.company.actions.Action;
import com.company.followingAccessories.Layout;
import com.company.session.Session;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ShowFollowingStreamers implements Action {
    private Session session;
    List<String> listOfFollowingStreamers = new ArrayList<>();
    String followingStreamers;

    public ShowFollowingStreamers(Session session) {
        this.session = session;
    }

    @Override
    public String getActionName() {
        return "Show following streamers";
    }

    @Override
    public void execute() throws FileNotFoundException {
        followingStreamers = session.getSessionUser().getFollowingStreamers();
        listOfFollowingStreamers = Layout.methodToDisconnect(followingStreamers, listOfFollowingStreamers);
        System.out.println("Following streamers: ");
        listOfFollowingStreamers.forEach(System.out::println);
    }
}
