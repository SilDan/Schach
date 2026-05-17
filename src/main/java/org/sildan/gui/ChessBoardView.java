package org.sildan.gui;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Creates the visual representation of the chess board.
 */
public class ChessBoardView {

    private final int tileSize;
    private final int boardSize;
    private final ChessBoardViewModel viewModel;

    /**
     * Creates a new chess board view.
     *
     * @param tileSize  the size of one square in pixels
     * @param boardSize the number of squares per row and column
     * @param viewModel the view model used to handle board interactions
     */
    public ChessBoardView(int tileSize, int boardSize, ChessBoardViewModel viewModel) {
        this.tileSize = tileSize;
        this.boardSize = boardSize;
        this.viewModel = viewModel;
    }

    /**
     * Creates the chess board.
     *
     * @return the JavaFX parent node containing the board
     */
    public Parent createBoard() {
        GridPane board = new GridPane();

        for (int gridY = 0; gridY < boardSize; gridY++) {
            for (int x = 0; x < boardSize; x++) {

                int chessY = boardSize - 1 - gridY;

                Rectangle square = createSquare(x, chessY);
                board.add(square, x, gridY);
            }
        }

        return board;
    }

    private Rectangle createSquare(int x, int y) {
        Rectangle square = new Rectangle(tileSize, tileSize);

        boolean isLightSquare = (x + y) % 2 == 0;
        square.setFill(isLightSquare ? Color.web("#6FA8DC") : Color.BEIGE);

        square.setOnMouseClicked(event -> viewModel.handleSquareClick(x, y));

        return square;
    }
}