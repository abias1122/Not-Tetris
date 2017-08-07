package com.example.android.tetris.game_entities;

import android.widget.GridLayout;

/**
 * A shape comprising of four squares used in Tetris
 */

public abstract class Tetronimo {
    private int mComponentViews[];

    private int mBottommostRowOccupied;
    private int mRightmostColOccupied;
    private int mLeftmostColOccupied;


    public abstract void rotate();

    public int getBottommostRowOccupied() {
        return mBottommostRowOccupied;
    }

    public int getLeftmostColOccupied() {
        return mLeftmostColOccupied;
    }

    public int getRightmostColOccupied() {
        return mRightmostColOccupied;
    }
}
