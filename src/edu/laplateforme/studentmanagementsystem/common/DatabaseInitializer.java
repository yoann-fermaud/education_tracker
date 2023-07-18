package edu.laplateforme.studentmanagementsystem.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseInitializer {
    public static void databaseInit() {
        Connection connection = null;
        Statement statement = null;
        Properties properties = new Properties();
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream("src/edu/laplateforme/studentmanagementsystem/resources/config.ini");
            properties.load(inputStream);

            String dbUrl = properties.getProperty("db.url");
            String dbUser = properties.getProperty("db.user");
            String dbPassword = properties.getProperty("db.password");

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS students_management_system";
            String useDatabaseQuery = "USE students_management_system";
            String createTableQuery = "CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "first_name VARCHAR(25), last_name VARCHAR(25), age VARCHAR(2), grades TEXT)";

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            statement = connection.createStatement();

            statement.executeUpdate(createDatabaseQuery);
            statement.executeUpdate(useDatabaseQuery);
            statement.executeUpdate(createTableQuery);

//            System.out.println("db created");

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);

        } finally {
            // Close the statement and connection
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
