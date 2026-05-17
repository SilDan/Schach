package org.sildan.model.pieces;

import org.sildan.model.Color;

//
public class Piece {
    PieceType type;
    Color color;
    Coordinates coordinates;

    // constructor
    public Piece(PieceType type, Color color, Coordinates coordinates) {
        this.type = type;
        this.color = color;
        this.coordinates = coordinates;
    }

    // getter and setter
    void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinate(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public void setType(PieceType type) {
        this.type = type;
    }
    public void setColor(Color color) {}

    public String toString() {
        return color + " " + type + " at " + coordinates;
    }
}
