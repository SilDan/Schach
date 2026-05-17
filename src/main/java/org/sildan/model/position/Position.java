package org.sildan.model.position;

import org.sildan.model.Color;
import org.sildan.model.moves.ChessMove;
import org.sildan.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Position {
    List<Piece> whitePiecesOnBoard;
    List<Piece> blackPiecesOnBoard;
    List<ChessMove> movesPlayed;
    Color whoseTurn;

    Position() {
        whitePiecesOnBoard = new ArrayList<>();
        blackPiecesOnBoard = new ArrayList<>();
        movesPlayed = new ArrayList<>();
        whoseTurn = Color.WHITE;
    }

    void addPiece(Piece piece) {
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

        if (whoseTurn.equals(Color.WHITE)) {
            for (Piece piece : whitePiecesOnBoard) {
                if (piece.getCoordinates().equals(chessMove.getFrom())) {
                    piece.setCoordinate(chessMove.getTo());
                }
                if (piece.getCoordinates().equals(chessMove.getTo())) {
                chessMove.setCaptured(piece.getType());
                }
            }
        }
        whoseTurn = whoseTurn.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        movesPlayed.add(chessMove);
    }
}
