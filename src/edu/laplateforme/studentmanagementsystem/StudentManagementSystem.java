package edu.laplateforme.studentmanagementsystem;

import edu.laplateforme.studentmanagementsystem.common.OptionsHandler;
import edu.laplateforme.studentmanagementsystem.common.UserInputHandler;

public class StudentManagementSystem {
    public static void main(String[] args) {
        System.out.println("""

                Student Management System
                -------------------------
                0 - Add a new student
                1 - Modify a student
                2 - Delete a student
                3 - Find a student
                4 - Show all students
                """);

        System.out.print("Choose an option: ");
        short userInput = UserInputHandler.getUserInputDigit();
        OptionsHandler.processOption(userInput);
    }
}
