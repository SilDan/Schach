package org.sildan.model.pieces;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Coordinates must be between 0 and 7 inclusive.");
        }
        this.x = x;
        this.y = y;
    }

    // getter and setter
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
