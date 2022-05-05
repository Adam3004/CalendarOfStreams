package com.company.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Enter password to you postgreSQL
public class Connector {
    private static final String URL = "jdbc:postgresql://localhost/users";
    private static final String USER = "postgres";
    private static final String PASSWORD = ""; //enter your own password to postgre-sql
    private static final String URLCALENDAR = "jdbc:postgresql://localhost/calendar";
    private static final String URLMESSAGES = "jdbc:postgresql://localhost/messages";

    public static Connection connectUsers() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static Connection connectCalendar() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URLCALENDAR, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static Connection connectMesages() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URLMESSAGES, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
