package org.sildan.gui;

import org.sildan.model.Color;
import org.sildan.model.moves.ChessMove;
import org.sildan.model.pieces.Coordinates;
import org.sildan.model.pieces.Piece;
import org.sildan.model.pieces.PieceType;
import org.sildan.model.position.Position;

import java.util.List;
import java.util.Optional;

/**
 * ViewModel for the chess board.
 * <p>
 * This class acts as a bridge between the JavaFX view and the chess domain model.
 */
public class ChessBoardViewModel {

    private final Position position;

    private final List<ChessMove> scriptedMoves = List.of(
            new ChessMove(new Coordinates(4, 1), new Coordinates(4, 3)), // e2 -> e4
            new ChessMove(new Coordinates(4, 6), new Coordinates(4, 4)), // e7 -> e5
            new ChessMove(new Coordinates(6, 0), new Coordinates(5, 2)), // g1 -> f3
            new ChessMove(new Coordinates(1, 7), new Coordinates(2, 5))  // b8 -> c6
    );

    private int nextScriptedMoveIndex = 0;

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
     * Plays the next move from the predefined move sequence.
     *
     * @return true if a move was played, false if no scripted move is left
     */
    public boolean playNextScriptedMove() {
        if (nextScriptedMoveIndex >= scriptedMoves.size()) {
            System.out.println("No scripted moves left.");
            return false;
        }

        ChessMove nextMove = scriptedMoves.get(nextScriptedMoveIndex);
        position.executeMove(nextMove);
        nextScriptedMoveIndex++;

        System.out.println("Played scripted move: "
                + nextMove.getFrom()
                + " -> "
                + nextMove.getTo());

        return true;
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