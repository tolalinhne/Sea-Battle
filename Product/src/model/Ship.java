package model;

public class Ship {
    private final int size;
    private final char symbol;

    public Ship(int size, char symbol) {
        this.size = size;
        this.symbol = symbol;
    }

    public int getSize() {
        return size;
    }

    public char getSymbol() {
        return symbol;
    }
}
