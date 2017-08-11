package com.example.android.tetris.game_entities;

import android.util.Log;

import com.example.android.tetris.R;

/**
 * Represents Z tetronimo:
 *    + +
 *      + +
 */

public class ZTetronimo extends Tetronimo {
    private GridCellView mComponentViews[];
    private GridCellView mAllGridCellViews[];

    private final String TAG = "ZTetronimo";

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 24;

    private int mBottommostRowOccupied;
    private int mRightmostColOccupied;
    private int mLeftmostColOccupied;

    public ZTetronimo(GridCellView[] gameboard) {
        mAllGridCellViews = gameboard;

        //component views in top center of gameboard
        mComponentViews = new GridCellView[4];
        mComponentViews[0] = mAllGridCellViews[0*NUM_COLS + 3];
        mComponentViews[1] = mAllGridCellViews[NUM_COLS + 4];
        mComponentViews[2] = mAllGridCellViews[0*NUM_COLS + 4];
        mComponentViews[3] = mAllGridCellViews[NUM_COLS + 5];

        Log.i(TAG, String.format("x:%d, y:%d", mComponentViews[0].getXPos(), mComponentViews[0].getYPos()));
        Log.i(TAG, String.format("x:%d, y:%d", mComponentViews[1].getXPos(), mComponentViews[1].getYPos()));
        Log.i(TAG, String.format("x:%d, y:%d", mComponentViews[2].getXPos(), mComponentViews[2].getYPos()));
        Log.i(TAG, String.format("x:%d, y:%d", mComponentViews[3].getXPos(), mComponentViews[3].getYPos()));

        for(GridCellView gridCell : mComponentViews) {
            gridCell.setOccupied(true);
            gridCell.setImageResource(R.drawable.z_tetron_grid_cell);
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
