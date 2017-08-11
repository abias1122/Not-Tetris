package com.example.android.tetris.game_entities;

import android.widget.GridLayout;

import com.example.android.tetris.R;

/**
 * A shape comprising of four squares used in Tetris
 */

public abstract class Tetronimo {

    private final int NUM_COLS = 10;

    private GridCellView[] mComponentCells;
    private GridCellView[] mGameGridCells;

    public Tetronimo(GridCellView[] gameGridCells, int[][] initialPositions, int drawableId) {

        mGameGridCells = gameGridCells;
        mComponentCells = new GridCellView[4];

        for(int i = 0; i < initialPositions.length; i++) {
            //index 0 is x position; index 1 is y position
            int[] xAndYPositions = initialPositions[i];
            mComponentCells[i]
                    = mGameGridCells[(xAndYPositions[1] * NUM_COLS) + xAndYPositions[0]];
        }

        for(GridCellView gridCell : mComponentCells) {
            gridCell.setOccupied(true);
            gridCell.setImageResource(drawableId);
        }
    }

    public GridCellView[] getGameGridCells() {
        return mGameGridCells;
    }

    public GridCellView[] getComponentCells() {
        return mComponentCells;
    }
    
    public abstract void rotate();

    public void moveDown() {
        //TODO: Implement moveDown()
    }
}
