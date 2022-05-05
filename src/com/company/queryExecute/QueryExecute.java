package com.company.queryExecute;

import com.company.connection.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecute {

    public static ResultSet executeSelectUsers(String selectQuery) {
        try {
            Connection connection = Connector.connectUsers();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ResultSet executeSelectCalendar(String selectQuery) {
        try {
            Connection connection = Connector.connectCalendar();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ResultSet executeSelectMessages(String selectQuery) {
        try {
            Connection connection = Connector.connectMesages();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void executeQueryUsers(String query) {
        Connection connection = Connector.connectUsers();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQueryCalendar(String query) {
        Connection connection = Connector.connectCalendar();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeQueryMessages(String query) {
        Connection connection = Connector.connectMesages();
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}