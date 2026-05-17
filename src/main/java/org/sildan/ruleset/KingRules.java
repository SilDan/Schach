package org.sildan.ruleset;

import org.sildan.model.moves.ChessMove;
import org.sildan.model.pieces.Coordinates;
import org.sildan.model.pieces.Piece;
import org.sildan.model.position.Position;
import org.sildan.validation.CoordinateValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that contains the rules for the king piece. This includes how it moves, how it captures, and any
 * special rules that apply to it (such as castling).
 */
public final class KingRules {

    List<ChessMove> allMoves;

    private KingRules() {
        // Private constructor to prevent instantiation
    }

    public static List<ChessMove> getMoves(Piece piece, Position position) {
        Coordinates coordinates = piece.getCoordinates();
        List<ChessMove> kingMoves = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Coordinates newCoordinates = new Coordinates(coordinates.getX() + i, coordinates.getY() + j);
                if (CoordinateValidator.isValid(newCoordinates)) {
                    ChessMove move = new ChessMove(coordinates, newCoordinates);
                    kingMoves.add(move);
                }
            }
        }
        return kingMoves;
    }

}