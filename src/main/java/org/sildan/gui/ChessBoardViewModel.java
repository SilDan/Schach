package org.sildan.gui;

/**
 * ViewModel for the chess board.
 * <p>
 * This class acts as a bridge between the JavaFX view and the chess domain model.
 */
public class ChessBoardViewModel {

    /**
     * Handles a click on a board square.
     *
     * @param x the x-coordinate of the clicked square
     * @param y the y-coordinate of the clicked square
     */
    public void handleSquareClick(int x, int y) {
        System.out.println("Clicked in ViewModel: x=" + x + ", y=" + y);
    }
}