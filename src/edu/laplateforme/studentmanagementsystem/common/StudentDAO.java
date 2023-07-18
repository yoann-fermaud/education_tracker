package edu.laplateforme.studentmanagementsystem.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class StudentDAO {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private void establishConnection() {
        Properties properties = new Properties();
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream("src/edu/laplateforme/studentmanagementsystem/resources/config.ini");
            properties.load(inputStream);

            String dbUrl = properties.getProperty("db.url");
            String dbName = properties.getProperty("db.database");
            String dbUser = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");

            String dbFullUrl = dbUrl + dbName;

            this.connection = DriverManager.getConnection(dbFullUrl, dbUser, dbPassword);
//            System.out.println("connected");

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void closeResultSet() {
        try {
            if (this.resultSet != null && !this.resultSet.isClosed()) {
                this.resultSet.close();
//                System.out.println("resultSet closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeStatement() {
        try {
            if (this.statement != null && !this.statement.isClosed()) {
                this.statement.close();
//                System.out.println("statement closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
//                System.out.println("db closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}