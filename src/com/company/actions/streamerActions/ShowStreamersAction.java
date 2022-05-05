package com.company.actions.streamerActions;

import com.company.actions.Action;

import java.io.FileNotFoundException;
import java.util.List;

public class ShowStreamersAction implements Action {
    private List<String> listsOfStreamers;


    public ShowStreamersAction(List<String> listsOfStreamers) {
        this.listsOfStreamers = listsOfStreamers;
    }


    @Override
    public String getActionName() {

        return "Show streamers";
    }

    @Override
    public void execute() throws FileNotFoundException {
        System.out.println("Streamers: ");
        listsOfStreamers.forEach(System.out::println);
    }
}
