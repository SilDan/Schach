package org.sildan.validation;

import org.sildan.gameconfig.board.BoardConfig;
import org.sildan.model.pieces.Coordinates;

public final class CoordinateValidator {

    // private constructor
    private CoordinateValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isValid(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        return x >= 0 && x < BoardConfig.BOARD_SIZE_X && y >= 0 && y < BoardConfig.BOARD_SIZE_Y;
    }
}
