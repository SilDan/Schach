package org.sildan.ruleset;

import org.sildan.model.moves.ChessMove;
import org.sildan.model.pieces.Piece;
import org.sildan.model.position.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * The MovelistGenerator class is responsible for finding all possible moves for a given position.
 */
public class MovelistGenerator {

    // Constructor
    public MovelistGenerator() {

    }

    // find all moves for a given Position
    List<ChessMove> findAllMoves(Position position) {
        List<ChessMove> allMoves = new ArrayList<>();
        for (Piece piece : position.getWhitePiecesOnBoard()) {
            switch(piece.getType()) {
             //   case PAWN -> allMoves.addAll(PawnRules.getPawnMoves(piece, position));
                //     case BISHOP -> allMoves.addAll(BishopRules.getBishopMoves(piece, position));
                //     case ROOK -> allMoves.addAll(RookRules.getRookMoves(piece, position));
                //     case QUEEN -> allMoves.addAll(QueenRules.getQueenMoves(piece, position));
                //     case KNIGHT -> allMoves.addAll(KnightRules.getKnightMoves(piece, position));
                //     case BATMAN -> allMoves.addAll(BatmanRules.getBatmanMoves(piece, position));
                case KING -> allMoves.addAll(KingRules.getMoves(piece, position));

            }
        }

        return allMoves;
    }


}
