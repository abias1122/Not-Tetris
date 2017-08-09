package com.example.android.tetris.game_entities;

import com.example.android.tetris.R;

/**
 * Tetronimo shaped like a square:
 *
 * + +
 * + +
 */

public class SquareTetronimo extends Tetronimo {

    private GridCellView mComponentViews[];
    private GridCellView mGameboard[];

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 24;

    private int mBottommostRowOccupied;
    private int mRightmostColOccupied;
    private int mLeftmostColOccupied;

    public SquareTetronimo(GridCellView[] gameboard) {
        mGameboard = gameboard;

        //component views in top center of gameboard
        mComponentViews = new GridCellView[4];
        mComponentViews[0] = mGameboard[4*NUM_ROWS + 1];
        mComponentViews[1] = mGameboard[5*NUM_ROWS + 1];
        mComponentViews[2] = mGameboard[4*NUM_ROWS + 2];
        mComponentViews[3] = mGameboard[5*NUM_ROWS + 2];

        mComponentViews[0].setImageResource(R.drawable.square_tetron_grid_cell);
        mComponentViews[1].setImageResource(R.drawable.square_tetron_grid_cell);
        mComponentViews[2].setImageResource(R.drawable.square_tetron_grid_cell);
        mComponentViews[3].setImageResource(R.drawable.square_tetron_grid_cell);

        mBottommostRowOccupied = 2;
        mLeftmostColOccupied = 4;
        mRightmostColOccupied = 5;
    }

    @Override
    public void rotate() {
        //rotating a square 90 degrees doesn't change appearance
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
