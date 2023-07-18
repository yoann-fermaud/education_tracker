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

    public void addStudent() {
        establishConnection();
        try {
            if (!this.connection.isClosed()) {
                System.out.println("""
                        Student Management System
                        -------------------------
                        Add a new student :""");

                System.out.print("First name: ");
                String dbFirstName = UserInputHandler.getUserInputString();

                System.out.print("Last name: ");
                String dbLastName = UserInputHandler.getUserInputString();

                System.out.print("Age: ");
                String dbAge = UserInputHandler.getUserInputString();

                System.out.print("Grades: ");
                String dbGrades = UserInputHandler.getUserInputString();

                String valuesQuery = "'" + dbFirstName + "'" + ", " + "'" + dbLastName + "'" + ", " + "'" + dbAge + "'" + ", " + "'" + dbGrades + "'";

                this.statement = this.connection.createStatement();
                this.statement.executeUpdate("INSERT INTO students (first_name, last_name, age, grades) " +
                        "VALUES (" + valuesQuery + ")");

                System.out.println("Student added !");

            } else {
                System.out.println("db already closed");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement();
            closeConnection();
        }
    }

    public void showAllStudents() {
        establishConnection();
        try {
            if (!this.connection.isClosed()) {
                System.out.println("""
                        Student Management System
                        -------------------------
                        Students list :
                        ID | first_name | last_name | age | grades
                        ------------------------------------------""");

                this.statement = this.connection.createStatement();
                this.resultSet = this.statement.executeQuery("SELECT * FROM students");

                while (this.resultSet.next()) {
                    String dbID = this.resultSet.getString("id");
                    String dbFirstName = this.resultSet.getString("first_name");
                    String dbLastName = this.resultSet.getString("last_name");
                    String dbAge = this.resultSet.getString("age");
                    String dbGrades = this.resultSet.getString("grades");

                    System.out.println(dbID + "  | " + dbFirstName + " | " + dbLastName + " | " + dbAge + " | " + dbGrades);
                }
            } else {
                System.out.println("db already closed");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            closeResultSet();
            closeStatement();
            closeConnection();
        }
    }
}
