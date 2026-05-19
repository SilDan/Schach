package org.sildan.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.sildan.model.Color;
import org.sildan.model.pieces.Coordinates;
import org.sildan.model.pieces.Piece;
import org.sildan.model.pieces.PieceType;
import org.sildan.model.position.Position;

/**
 * Starts the JavaFX chess application.
 */
public class ChessApplication extends Application {

    private static final int TILE_SIZE = 80;
    private static final int BOARD_SIZE = 8;

    @Override
    public void start(Stage stage) {
        Position position = createInitialPosition();

        ChessBoardViewModel viewModel = new ChessBoardViewModel(position);
        ChessBoardView chessBoardView = new ChessBoardView(TILE_SIZE, BOARD_SIZE, viewModel);

        Scene scene = new Scene(
                chessBoardView.createBoard(),
                TILE_SIZE * BOARD_SIZE,
                TILE_SIZE * BOARD_SIZE
        );

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                boolean moveWasPlayed = viewModel.playNextScriptedMove();

                if (moveWasPlayed) {
                    chessBoardView.refreshBoard();
                }
            }
        });

        stage.setTitle("Sildan Chess");
        stage.setScene(scene);
        stage.show();
    }

    private Position createInitialPosition() {
        Position position = new Position();

        position.addPiece(new Piece(PieceType.ROOK, Color.WHITE, new Coordinates(0, 0)));
        position.addPiece(new Piece(PieceType.KNIGHT, Color.WHITE, new Coordinates(1, 0)));
        position.addPiece(new Piece(PieceType.BISHOP, Color.WHITE, new Coordinates(2, 0)));
        position.addPiece(new Piece(PieceType.QUEEN, Color.WHITE, new Coordinates(3, 0)));
        position.addPiece(new Piece(PieceType.KING, Color.WHITE, new Coordinates(4, 0)));
        position.addPiece(new Piece(PieceType.BISHOP, Color.WHITE, new Coordinates(5, 0)));
        position.addPiece(new Piece(PieceType.KNIGHT, Color.WHITE, new Coordinates(6, 0)));
        position.addPiece(new Piece(PieceType.ROOK, Color.WHITE, new Coordinates(7, 0)));

        for (int x = 0; x < BOARD_SIZE; x++) {
            position.addPiece(new Piece(PieceType.PAWN, Color.WHITE, new Coordinates(x, 1)));
        }

        position.addPiece(new Piece(PieceType.ROOK, Color.BLACK, new Coordinates(0, 7)));
        position.addPiece(new Piece(PieceType.KNIGHT, Color.BLACK, new Coordinates(1, 7)));
        position.addPiece(new Piece(PieceType.BISHOP, Color.BLACK, new Coordinates(2, 7)));
        position.addPiece(new Piece(PieceType.QUEEN, Color.BLACK, new Coordinates(3, 7)));
        position.addPiece(new Piece(PieceType.KING, Color.BLACK, new Coordinates(4, 7)));
        position.addPiece(new Piece(PieceType.BISHOP, Color.BLACK, new Coordinates(5, 7)));
        position.addPiece(new Piece(PieceType.KNIGHT, Color.BLACK, new Coordinates(6, 7)));
        position.addPiece(new Piece(PieceType.ROOK, Color.BLACK, new Coordinates(7, 7)));

        for (int x = 0; x < BOARD_SIZE; x++) {
            position.addPiece(new Piece(PieceType.PAWN, Color.BLACK, new Coordinates(x, 6)));
        }

        return position;
    }

    /**
     * Starts the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}