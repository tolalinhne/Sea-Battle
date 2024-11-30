import controller.GameController;
import view.Menu;

import java.util.Scanner;
import view.GameView;

public class Main {
    static final String GREEN = "\033[32m";
    static final String RESET = "\033[0m";

    public static void main(String[] args) {
        Menu menu = new Menu();
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu.displayMainMenu();
            int choice = menu.getChoice(scanner);

            switch (choice) {
                case 1 -> {
                    GameView.clearScreen();
                    gameController.startGame();
                }
                case 2 -> {
                    if (gameController.loadGame()) {
                        GameView.clearScreen();
                        gameController.resumeGame();
                    } else {
                        GameView.clearScreen();
                        System.out.println(GREEN + "No saved game found. Starting a new game." + RESET);
                        gameController.startGame();
                    }
                }
                case 3 -> {
                    GameView.clearScreen();
                    System.out.println(GREEN +
                            "  ██████╗  ██████╗  ██████╗ ██████╗ ██████╗ ██╗   ██╗███████╗\n" +
                            " ██╔════╝ ██╔═══██╗██╔═══██╗██╔══██╗██╔══██╗███╗ ███║██╔════╝\n" +
                            " ██║  ███╗██║   ██║██║   ██║██║  ██║██████╔╝  ████╔═╝█████╗  \n" +
                            " ██║   ██║██║   ██║██║   ██║██║  ██║██╔══██╗   ██╔╝  ██╔══╝  \n" +
                            " ╚██████╔╝╚██████╔╝╚██████╔╝██████╔╝██████╔╝   ██║   ███████╗\n" +
                            "  ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝  ╚═════╝    ╚═╝   ╚══════╝\n" + RESET);
                    System.out.println(GREEN + "Exiting the game. Goodbye!" + RESET);
                    return;
                }
            }
        }
    }
}

