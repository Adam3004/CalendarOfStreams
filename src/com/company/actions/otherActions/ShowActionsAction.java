package com.company.actions.otherActions;

import com.company.actions.Action;

import java.io.FileNotFoundException;
import java.util.List;

public class ShowActionsAction implements Action {
    private List<Action> actions;

    public ShowActionsAction(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String getActionName() {
        return "Show possible actions";
    }

    @Override
    public void execute() throws FileNotFoundException {
        for (int i = 1; i < actions.size() + 1; i++) {
            System.out.println(i + ". " + actions.get(i - 1).getActionName());
        }

//        nie wiem jeszcze jak to zrobic zeby i sie zwiekszalo, ale z czasem na pewno się dowiem
//        int i=0;
//        actions.stream()
//                .map(a-> printNumberInMap(i,a),i++)
//                .forEach(System.out::println);
    }
//    public static String printNumberInMap(int i,Action a){
//        i++;
//        return i+". " + a.getActionName();
//    }

}
