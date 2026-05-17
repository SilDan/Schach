package org.sildan.model.moves;

import org.sildan.model.pieces.Coordinates;
import org.sildan.model.pieces.PieceType;

public class ChessMove {

    Coordinates from;
    Coordinates to;
    PieceType captured;

    public ChessMove(Coordinates from, Coordinates to) {
        this.from = from;
        this.to = to;
        captured = PieceType.NONE;
    }

    public void setCaptured(PieceType captured) {
        this.captured = captured;
    }

    public Coordinates getTo() {
        return to;
    }

    public Coordinates getFrom() {
        return from;
    }

}
