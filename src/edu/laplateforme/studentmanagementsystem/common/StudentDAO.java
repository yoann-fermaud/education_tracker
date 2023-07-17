package edu.laplateforme.studentmanagementsystem.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class StudentDAO {
    private Connection connection;
    public void establishConnection() {
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
            System.out.println("connected");

        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());

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

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.println("db closed");
            } else {
                System.out.println("ar closed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
