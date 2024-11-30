package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements Serializable {
    private static final long serialVersionUID = 1L;
    private final char[][] grid;
    private final int size;

    final String RED = "\033[31m";
    final String GREEN = "\033[32m";
    final String RESET = "\033[0m";
    final String BLUE_BACKROUND = "\033[44m";
    final String RESET_BACKROUND = "\033[40m";

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(grid[i], '-');
        }
    }

    public void printBoard(boolean fogOfWar) {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                if (fogOfWar) {
                    if (grid[i][j] == 'X') {
                        System.out.print(RED + "X " + RESET);
                    } else if (grid[i][j] == 'O') {
                        System.out.print(GREEN + "O " + RESET);
                    } else {
                        System.out.print("- ");
                    }
                } else if (grid[i][j] == 'O') {
                    System.out.print(GREEN + "O " + RESET);
                } else if (grid[i][j] == 'X') {
                    System.out.print(RED + "X " + RESET);
                } else if (grid[i][j] == 'A' || grid[i][j] == 'B' || grid[i][j] == 'C' || grid[i][j] == 'D' || grid[i][j] == 'E') {
                    System.out.print(BLUE_BACKROUND + grid[i][j] + " " + RESET_BACKROUND);
                } else {
                    System.out.print(grid[i][j] + " " );
                }
            }
            System.out.println();
        }
    }

    public boolean placeShip(int startRowShip, int startColShip, int endRowShip, int endColShip, Ship ship) {
        if (!isValidPlacement(startRowShip, startColShip, endRowShip, endColShip, ship)) {
            return false;
        }

        char symbol = ship.getSymbol();
        if (startRowShip == endRowShip) {
            for (int j = Math.min(startColShip, endColShip); j <= Math.max(startColShip, endColShip); j++) {
                grid[startRowShip][j] = symbol;
            }
        } else if (startColShip == endColShip) {
            for (int i = Math.min(startRowShip, endRowShip); i <= Math.max(startRowShip, endRowShip); i++) {
                grid[i][startColShip] = symbol;
            }
        }
        return true;
    }

    public boolean isValidPlacement(int startRowShip, int startColShip, int endRowShip, int endColShip, Ship ship) {
        if (startRowShip < 0 || startRowShip >= size || startColShip < 0 || startColShip >= size || endRowShip < 0 || endRowShip >= size || endColShip < 0 || endColShip >= size) {
            return false;
        }

        int dx = (endRowShip - startRowShip == 0) ? 0 : (endRowShip - startRowShip) / Math.abs(endRowShip - startRowShip);
        int dy = (endColShip - startColShip == 0) ? 0 : (endColShip - startColShip) / Math.abs(endColShip - startColShip);


        if ((Math.abs(endRowShip - startRowShip) + 1 != ship.getSize() && dx != 0) ||
                (Math.abs(endColShip - startColShip) + 1 != ship.getSize() && dy != 0)) {
            return false;
        }


        for (int i = 0; i < ship.getSize(); i++) {
            int nx = startRowShip + i * dx;
            int ny = startColShip + i * dy;
            if (grid[nx][ny] != '-') {
                return false;
            }
        }
        return true;
    }

    public boolean attack(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size || grid[x][y] == 'X' || grid[x][y] == 'O') {
            return false;
        }

        if (grid[x][y] != '-' && grid[x][y] != 'X' && grid[x][y] != 'O') {
            grid[x][y] = 'X';
            return true;
        } else {
            grid[x][y] = 'O';
            return false;
        }
    }

    public boolean allShipsSunk() {
        for (char[] row : grid) {
            for (char cell : row) {
                if (cell != '-' && cell != 'X' && cell != 'O') return false;
            }
        }
        return true;
    }

    public int[][] getValidEndPositions(int startRowShip, int startColShip, Ship ship) {
        List<int[]> validPositions = new ArrayList<>();
        int size = ship.getSize();

        if (startColShip + size - 1 < this.size) {
            boolean valid = true;
            for (int j = startColShip; j < startColShip + size; j++) {
                if (grid[startRowShip][j] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{startRowShip, startColShip + size - 1});
        }

        if (startColShip - size + 1 >= 0) {
            boolean valid = true;
            for (int j = startColShip; j > startColShip - size; j--) {
                if (grid[startRowShip][j] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{startRowShip, startColShip - size + 1});
        }

        if (startRowShip + size - 1 < this.size) {
            boolean valid = true;
            for (int i = startRowShip; i < startRowShip + size; i++) {
                if (grid[i][startColShip] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{startRowShip + size - 1, startColShip});
        }

        if (startRowShip - size + 1 >= 0) {
            boolean valid = true;
            for (int i = startRowShip; i > startRowShip - size; i--) {
                if (grid[i][startColShip] != '-') {
                    valid = false;
                    break;
                }
            }
            if (valid) validPositions.add(new int[]{startRowShip - size + 1, startColShip});
        }

        return validPositions.toArray(new int[0][]);
    }

    public void removeShip(char shipSymbol) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == shipSymbol) {
                    grid[i][j] = '-';
                }
            }
        }
    }
}
