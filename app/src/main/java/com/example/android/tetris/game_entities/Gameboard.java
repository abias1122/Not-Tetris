package com.example.android.tetris.game_entities;

import android.content.Context;
import android.widget.GridLayout;

/**
 * @author Anthony Bias
 *
 * Wrapper class for array holding gameboard's GridCellViews
 */

public class Gameboard {

    public final int NUM_ROWS = 24;
    public final int NUM_COLS = 10;
    private GridCellView[] mGridCellsViews;

    /**
     * Constructor for Gameboard.
     * @param context context for the GridLayout that holds the tetronimos
     */
    public Gameboard(Context context, GridLayout gameGridLayout) {

        mGridCellsViews = new GridCellView[NUM_ROWS * NUM_COLS];
        for(int y = 0; y < NUM_ROWS; y++) {
            for(int x = 0; x < NUM_COLS; x++) {
                GridCellView newGridCell = new GridCellView(context, x, y);
                mGridCellsViews[(y * NUM_COLS) + x] = newGridCell;
                gameGridLayout.addView(newGridCell);
            }
        }
    }

    /**
     * Return the GridCellView at with the specified xPos
     * and YPos.
     * @param x x position starting at left of board. Can be 0 through NUM_COLS-1
     * @param y y positions starting at the top of the board. Can be 0 through NUM_ROWS-1
     * @return GridCellView at (x, y)
     */
    public GridCellView getGridCell(int x, int y) {
        if ((x >= 0 && x < NUM_COLS) && (y >= 0 && y < NUM_ROWS)) {
            return mGridCellsViews[(y * NUM_COLS) + x];
        }
        else if (y < 1 || y >= NUM_ROWS){
            throw new IllegalArgumentException("getGridCell: y must be between 0 and " +
                    (NUM_ROWS - 1) + "inclusive.");
        }
        else {
            throw new IllegalArgumentException("getGridCell: x must be between 0 and " +
                    (NUM_COLS - 1) + "inclusive.");
        }
    }
}
