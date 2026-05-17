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

    private KingRules() {
        // Private constructor to prevent instantiation
    }

    public static List<ChessMove> getMoves(Piece piece, Position position) {
        Coordinates currentCoordinates = piece.getCoordinates();
        List<ChessMove> kingMoves = new ArrayList<>();

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                if (xOffset == 0 && yOffset == 0) {
                    continue;
                }

                int targetX = currentCoordinates.getX() + xOffset;
                int targetY = currentCoordinates.getY() + yOffset;

                if (!CoordinateValidator.isOnTheBoard(targetX, targetY)) {
                    continue;
                }

                Coordinates targetCoordinates = new Coordinates(targetX, targetY);

                if (CoordinateValidator.isValid(targetCoordinates, position)) {
                    ChessMove move = new ChessMove(currentCoordinates, targetCoordinates);
                    kingMoves.add(move);
                }
            }
        }

        return kingMoves;
    }
}