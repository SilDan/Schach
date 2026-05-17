package org.sildan.gui;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Creates the visual representation of the chess board.
 */
public class ChessBoardView {

    private static final double PIECE_SIZE_FACTOR = 0.78;

    private final int tileSize;
    private final int boardSize;
    private final ChessBoardViewModel viewModel;
    private final SvgImageLoader svgImageLoader;

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
        this.svgImageLoader = new SvgImageLoader();
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

                StackPane square = createSquare(x, chessY);
                board.add(square, x, gridY);
            }
        }

        return board;
    }

    private StackPane createSquare(int x, int y) {
        Rectangle background = new Rectangle(tileSize, tileSize);

        boolean isLightSquare = (x + y) % 2 != 0;
        background.setFill(isLightSquare ? Color.BEIGE : Color.web("#6FA8DC"));

        StackPane square = new StackPane(background);

        String pieceImagePath = viewModel.getPieceImagePathAt(x, y);
        if (!pieceImagePath.isBlank()) {
            Image pieceImage = svgImageLoader.loadSvg(
                    pieceImagePath,
                    tileSize * PIECE_SIZE_FACTOR,
                    tileSize * PIECE_SIZE_FACTOR
            );

            ImageView pieceImageView = new ImageView(pieceImage);
            pieceImageView.setMouseTransparent(true);

            square.getChildren().add(pieceImageView);
        }

        square.setOnMouseClicked(event -> viewModel.handleSquareClick(x, y));

        return square;
    }
}