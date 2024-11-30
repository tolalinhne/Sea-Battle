package view;

import java.awt.*;
import java.util.Scanner;

public class Menu {
    public void displayMainMenu() {
        final String YELLOW = "\033[33m";
        final String BLUE = "\033[34m";
        final String RESET = "\033[0m";
        final String RED = "\033[31m";

        System.out.println(BLUE +
                " ███████╗███████╗ █████╗     ██████╗  █████╗ ████████╗████████╗██╗     ███████╗\n" +
                " ██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝\n" +
                " ███████ █████╗  ███████║    ██████╔╝███████║   ██║      ██║   ██║     █████╗  \n" +
                " ╚═══╗██ ██╔══╝  ██╔══██║    ██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝  \n" +
                " ███████╗███████╗██║  ██║    ██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗\n" +
                " ╚══════╝╚══════╝╚═╝  ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝\n" + RED);

        System.out.println(YELLOW +
                "===================== GAME MENU =====================\n" +
                "1. New Game\n" +
                "2. Continue Game\n" +
                "3. Exit\n" +
                "====================================================" + RESET);
        System.out.print(YELLOW + "Enter your choice: " + RESET);
    }

    public int getChoice(Scanner scanner) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return choice;
    }
}


