package org.sildan.model.position;

import org.sildan.model.Color;
import org.sildan.model.moves.ChessMove;
import org.sildan.model.pieces.Coordinates;
import org.sildan.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Position {

    private final List<Piece> whitePiecesOnBoard;
    private final List<Piece> blackPiecesOnBoard;
    private final List<ChessMove> movesPlayed;
    private Color whoseTurn;

    public Position() {
        whitePiecesOnBoard = new ArrayList<>();
        blackPiecesOnBoard = new ArrayList<>();
        movesPlayed = new ArrayList<>();
        whoseTurn = Color.WHITE;
    }

    public void addPiece(Piece piece) {
        if (piece.getColor().equals(Color.WHITE)) {
            whitePiecesOnBoard.add(piece);
        } else {
            blackPiecesOnBoard.add(piece);
        }
    }

    public List<Piece> getWhitePiecesOnBoard() {
        return whitePiecesOnBoard;
    }

    public List<Piece> getBlackPiecesOnBoard() {
        return blackPiecesOnBoard;
    }

    public void executeMove(ChessMove chessMove) {
        List<Piece> ownPieces = getPiecesForColor(whoseTurn);
        Color opponentColor = getOpponentColor(whoseTurn);
        List<Piece> opponentPieces = getPiecesForColor(opponentColor);

        Piece movingPiece = findPieceAt(chessMove.getFrom(), ownPieces)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No " + whoseTurn + " piece found at " + chessMove.getFrom()
                ));

        Optional<Piece> ownPieceAtTarget = findPieceAt(chessMove.getTo(), ownPieces);
        if (ownPieceAtTarget.isPresent()) {
            throw new IllegalArgumentException("Cannot move to a square occupied by an own piece.");
        }

        Optional<Piece> capturedPiece = findPieceAt(chessMove.getTo(), opponentPieces);
        capturedPiece.ifPresent(piece -> {
            chessMove.setCaptured(piece.getType());
            opponentPieces.remove(piece);
        });

        movingPiece.setCoordinate(chessMove.getTo());
        movesPlayed.add(chessMove);
        whoseTurn = opponentColor;
    }

    public Optional<Piece> getPieceForColorAt(Coordinates coordinates, Color color) {
        List<Piece> pieces = getPiecesForColor(color);
        return findPieceAt(coordinates, pieces);
    }

    public Color getWhoseTurn() {
        return whoseTurn;
    }

    private List<Piece> getPiecesForColor(Color color) {
        if (color.equals(Color.WHITE)) {
            return whitePiecesOnBoard;
        }

        if (color.equals(Color.BLACK)) {
            return blackPiecesOnBoard;
        }

        throw new IllegalArgumentException("Unsupported color: " + color);
    }

    private Color getOpponentColor(Color color) {
        return color.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Optional<Piece> findPieceAt(Coordinates coordinates, List<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.getCoordinates().equals(coordinates)) {
                return Optional.of(piece);
            }
        }

        return Optional.empty();
    }
}