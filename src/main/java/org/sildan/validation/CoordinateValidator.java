package org.sildan.validation;

import org.sildan.gameconfig.board.BoardConfig;
import org.sildan.model.Color;
import org.sildan.model.pieces.Coordinates;
import org.sildan.model.position.Position;

public final class CoordinateValidator {

    // private constructor
    private CoordinateValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isValid(Coordinates coordinates, Position position) {
        return isOnTheBoard(coordinates) && !isOccupiedByOwnPiece(coordinates, position);
    }

    private static boolean isOnTheBoard(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        return isOnTheBoard(x, y);
    }

    public static boolean isOnTheBoard(int x, int y) {
        return x >= 0 && x < BoardConfig.BOARD_SIZE_X && y >= 0 && y < BoardConfig.BOARD_SIZE_Y;
    }

    private static boolean isOccupiedByOwnPiece(Coordinates coordinates, Position position) {
        return position.getPieceForColorAt(coordinates, position.getWhoseTurn()).isPresent();
    }
}
