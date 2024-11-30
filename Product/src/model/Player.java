package model;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final Board board;

    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new Board(boardSize);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }
}
