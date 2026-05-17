package org.sildan.gui;

import org.sildan.model.Color;
import org.sildan.model.pieces.Coordinates;
import org.sildan.model.pieces.Piece;
import org.sildan.model.pieces.PieceType;
import org.sildan.model.position.Position;

import java.util.Optional;

/**
 * ViewModel for the chess board.
 * <p>
 * This class acts as a bridge between the JavaFX view and the chess domain model.
 */
public class ChessBoardViewModel {

    private final Position position;

    /**
     * Creates a new chess board view model.
     *
     * @param position the current chess position
     */
    public ChessBoardViewModel(Position position) {
        this.position = position;
    }

    /**
     * Returns the SVG image path for the piece on the given square.
     *
     * @param x the x-coordinate of the square
     * @param y the y-coordinate of the square
     * @return the SVG resource path or an empty string if the square is empty
     */
    public String getPieceImagePathAt(int x, int y) {
        Coordinates coordinates = new Coordinates(x, y);

        Optional<Piece> whitePiece = position.getPieceForColorAt(coordinates, Color.WHITE);
        if (whitePiece.isPresent()) {
            return toImagePath(whitePiece.get());
        }

        Optional<Piece> blackPiece = position.getPieceForColorAt(coordinates, Color.BLACK);
        if (blackPiece.isPresent()) {
            return toImagePath(blackPiece.get());
        }

        return "";
    }

    /**
     * Handles a click on a board square.
     *
     * @param x the x-coordinate of the clicked square
     * @param y the y-coordinate of the clicked square
     */
    public void handleSquareClick(int x, int y) {
        System.out.println("Clicked in ViewModel: x=" + x + ", y=" + y);
    }

    private String toImagePath(Piece piece) {
        String color = piece.getColor().equals(Color.WHITE) ? "white" : "black";
        String type = toFileNamePart(piece.getType());

        return "/pieces/" + color + "_" + type + ".svg";
    }

    private String toFileNamePart(PieceType pieceType) {
        return switch (pieceType) {
            case KING -> "king";
            case QUEEN -> "queen";
            case ROOK -> "rook";
            case BISHOP -> "bishop";
            case KNIGHT -> "knight";
            case PAWN -> "pawn";
            default -> "";
        };
    }
}