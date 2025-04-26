package com.example.lostandfound.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcURL = "jdbc:mysql://localhost:3306/LostAndFound_db";
            String jdbcUsername = "root";
            String jdbcPassword = "shara2010@NOT";
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }
}

