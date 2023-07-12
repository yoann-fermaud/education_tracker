package edu.laplateforme.studentmanagementsystem.common;

import java.util.Scanner;

public class UserInputHandler {
    public static short getUserInput() {
        Scanner scanner = new Scanner(System.in);
        short userInput = (short) scanner.nextInt();
        scanner.close();

        return userInput;
    }
}
