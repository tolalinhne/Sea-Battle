package view;

import java.util.Scanner;

public class GameView {
    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getPlayerName(Scanner scanner, int playerNumber) {
        System.out.print("Nhập tên cho Người chơi " + playerNumber + ": ");
        return scanner.nextLine();
    }

    public int getBoardSize(Scanner scanner) {
        int size;
        while (true) {
            System.out.print("Nhập kích thước bảng (10-20): ");
            size = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm
            if (size >= 10 && size <= 20) {
                break;
            }
            System.out.println("Kích thước không hợp lệ. Vui lòng nhập từ 5 đến 10.");
        }
        return size;
    }

    public String getCoordinateInput(Scanner scanner) {
        System.out.print("Nhập tọa độ hoặc gõ 'exit' để lưu và thoát: ");
        return scanner.nextLine().toUpperCase();
    }

    public void showValidEndPositions(int[][] positions) {
        System.out.println("Các vị trí kết thúc hợp lệ:");
        for (int i = 0; i < positions.length; i++) {
            char row = (char) ('A' + positions[i][0]);
            int col = positions[i][1] + 1;
            System.out.println((i + 1) + ". " + row + col);
        }
    }

    public int getEndPositionChoice(Scanner scanner, int maxOptions) {
        int choice;
        while (true) {
            System.out.print("Chọn vị trí kết thúc (1-" + maxOptions + "): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Xóa bộ đệm
            if (choice >= 1 && choice <= maxOptions) {
                break;
            }
            System.out.println("Lựa chọn không hợp lệ.");
        }
        return choice;
    }

    public void showPlayerTurn(String playerName) {
        System.out.println("Lượt của " + playerName + ":");
    }

    public void showAttackResult(boolean hit) {
        if (hit) {
            System.out.println("Tấn công trúng đích!");
        } else {
            System.out.println("Tấn công trượt!");
        }
    }

    public void showWinner(String playerName) {
        System.out.println("\nChúc mừng " + playerName + " đã chiến thắng!");
    }

    public void pauseAndContinue(Scanner scanner) {
        System.out.print("Nhấn Enter để tiếp tục...");
        scanner.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println(); // Mô phỏng xóa màn hình
    }
}
