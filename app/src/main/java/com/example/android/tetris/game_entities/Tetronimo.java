package com.example.android.tetris.game_entities;

import android.widget.GridLayout;

/**
 * A shape comprising of four squares used in Tetris
 */

public abstract class Tetronimo {
    public abstract void rotate();

    public abstract int getBottommostRowOccupied();

    public  abstract int getLeftmostColOccupied();

    public abstract int getRightmostColOccupied();
}
