package com.example.android.tetris.game_entities;

import com.example.android.tetris.R;

/**
 * Represents a straight tetronimo:
 *       + + + +
 */

public class StraightTetronimo extends Tetronimo {
    private GridCellView mComponentViews[];
    private GridCellView mAllGridCellViews[];

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 24;

    private int mBottommostRowOccupied;
    private int mRightmostColOccupied;
    private int mLeftmostColOccupied;

    public StraightTetronimo(GridCellView[] gameboard) {
        mAllGridCellViews = gameboard;

        //component views in top center of gameboard
        mComponentViews = new GridCellView[4];
        mComponentViews[0] = mAllGridCellViews[NUM_COLS + 3];
        mComponentViews[1] = mAllGridCellViews[NUM_COLS + 4];
        mComponentViews[2] = mAllGridCellViews[NUM_COLS + 5];
        mComponentViews[3] = mAllGridCellViews[NUM_COLS + 6];

        mComponentViews[0].setImageResource(R.drawable.straight_tetron_grid_cell);
        mComponentViews[1].setImageResource(R.drawable.straight_tetron_grid_cell);
        mComponentViews[2].setImageResource(R.drawable.straight_tetron_grid_cell);
        mComponentViews[3].setImageResource(R.drawable.straight_tetron_grid_cell);

        mComponentViews[0].setOccupied(true);
        mComponentViews[1].setOccupied(true);;
        mComponentViews[2].setOccupied(true);;
        mComponentViews[3].setOccupied(true);;

        mBottommostRowOccupied = 1;
        mLeftmostColOccupied = 3;
        mRightmostColOccupied = 6;
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