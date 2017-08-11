package com.example.android.tetris.game_entities;

import com.example.android.tetris.R;

/**
 * Represents T-tetronimo:
 *  + + +
 *    +
 */

public class TTetronimo extends Tetronimo {
    private GridCellView mComponentViews[];
    private GridCellView mAllGridCellViews[];

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 24;

    private int mBottommostRowOccupied;
    private int mRightmostColOccupied;
    private int mLeftmostColOccupied;

    public TTetronimo(GridCellView[] gameboard) {
        mAllGridCellViews = gameboard;

        //component views in top center of gameboard
        mComponentViews = new GridCellView[4];
        mComponentViews[0] = mAllGridCellViews[NUM_COLS + 3];
        mComponentViews[1] = mAllGridCellViews[NUM_COLS + 4];
        mComponentViews[2] = mAllGridCellViews[NUM_COLS + 5];
        mComponentViews[3] = mAllGridCellViews[0*NUM_COLS + 4];

        for(GridCellView gridCell : mComponentViews) {
            gridCell.setOccupied(true);
            gridCell.setImageResource(R.drawable.t_tetron_grid_cell);
        }

        mBottommostRowOccupied = 1;
        mLeftmostColOccupied = 3;
        mRightmostColOccupied = 5;
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        return;
    }

    @Override
    public int getBottommostRowOccupied() {
        return mBottommostRowOccupied;
    }

    @Override
    public int getLeftmostColOccupied() {
        return mLeftmostColOccupied;
    }

    @Override
    public int getRightmostColOccupied() {
        return mRightmostColOccupied;
    }
}
