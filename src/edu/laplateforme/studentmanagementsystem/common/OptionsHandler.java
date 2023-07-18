package edu.laplateforme.studentmanagementsystem.common;

public class OptionsHandler {
    public static void processOption(short option) {
        DatabaseInitializer.databaseInit();
        StudentDAO studentDAO = new StudentDAO();

        switch (option) {
            case 0:
                studentDAO.addStudent();
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
            case 4:
                studentDAO.showAllStudents();
                break;
            default:
                System.out.println("Unknown option !");
                break;
        }
    }
}
