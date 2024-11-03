package org.bankingsystem.dao;

import org.bankingsystem.entity.User;

import java.sql.*;
import java.util.Properties;

public class DbConnection {
    private Connection connection;
    private Statement statement;
// Singleton Desing pattern

    public Statement getStatement() {
        if (statement == null) {
            Properties props = new Properties();
            String url = "jdbc:postgresql://localhost:5432/bank";
            props.setProperty("ssl", "false");
            props.setProperty("user", "postgres");
            props.setProperty("password", "postgres");
            try {
                connection = DriverManager.getConnection(url, props);
                statement = connection.createStatement();
            } catch (Exception e) {
                System.out.println("Fail to create connection");
                e.printStackTrace();
            }

            return statement;
        } else {
            return statement;
        }


    }
}
