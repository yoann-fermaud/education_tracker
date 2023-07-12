package edu.laplateforme.studentmanagementsystem.common;

public class OptionsHandler {
    public static void processOption(short option) {
        switch (option) {
            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            default:
                System.out.println("Unknown option !");
                break;
        }
    }
}
