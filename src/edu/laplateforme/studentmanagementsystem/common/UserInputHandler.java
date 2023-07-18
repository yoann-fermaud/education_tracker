package edu.laplateforme.studentmanagementsystem.common;

import java.util.Scanner;

public class UserInputHandler {
    public static short getUserInputDigit() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextShort();
    }

    public static String getUserInputString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
