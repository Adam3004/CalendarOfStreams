package com.company.queries;

import com.company.actions.Action;
import com.company.queryExecute.QueryExecute;


import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintAllUsersQuery implements Action {

    @Override
    public String getActionName() {
        return "Print all users in datebase";
    }

    @Override
    public void execute() throws FileNotFoundException {
        try {
            ResultSet resultSet = QueryExecute.executeSelectUsers("SELECT * FROM public.users");
            while (!resultSet.isLast()) {
                resultSet.next();
                String getLogin = resultSet.getString("LOGIN");
                String getPassword = resultSet.getString("PASSWORD");
                int getAge = resultSet.getInt("AGE");
                String getRole = resultSet.getString("ROLE");
                String getfollowingStreamers = resultSet.getString("FollowingStreamers");
                if (getfollowingStreamers.isEmpty()) {
                    System.out.println("Login: " + getLogin + " Password: " + getPassword + " Age: " + getAge + " Role: "
                            + getRole + " there is not any following streamers yet");
                } else {
                    System.out.println("Login: " + getLogin + " Password: " + getPassword + " Age: " + getAge + " Role: "
                            + getRole + "Following streamers: " + getfollowingStreamers);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
