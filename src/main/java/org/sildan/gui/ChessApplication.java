package org.sildan.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts the JavaFX chess application.
 */
public class ChessApplication extends Application {

    private static final int TILE_SIZE = 80;
    private static final int BOARD_SIZE = 8;

    @Override
    public void start(Stage stage) {
        ChessBoardViewModel viewModel = new ChessBoardViewModel();
        ChessBoardView chessBoardView = new ChessBoardView(TILE_SIZE, BOARD_SIZE, viewModel);

        Scene scene = new Scene(
                chessBoardView.createBoard(),
                TILE_SIZE * BOARD_SIZE,
                TILE_SIZE * BOARD_SIZE
        );

        stage.setTitle("Sildan Chess");
        stage.setScene(scene);
        stage.show();
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