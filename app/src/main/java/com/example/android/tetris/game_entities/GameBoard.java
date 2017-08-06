package com.example.android.tetris.game_entities;

import android.content.Context;
import android.widget.GridLayout;

/**
 * Representation of the Tetris grid where the tetronimoes
 * fall and accumulate.
 */

public class GameBoard extends GridLayout {

    //stores whether a given grid cell is occupied
    private boolean mGridCellOccupancy[][];

    private static final int NUMBER_OF_COLUMNS = 24;
    private static final int NUMBER_OF_ROWS = 10;

    private int mHighestOccupiedRow;
    private Context mContext;

    public GameBoard(Context context) {
        super(context);
        mGridCellOccupancy = new boolean[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
        initGridCellOccupancy();
        mHighestOccupiedRow = 0;
        mContext = context;
    }

    /**
     * Initialize all cells to not occupied. Used at start of game.
     */
    private void initGridCellOccupancy() {
        for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            for(int j = 0; j < NUMBER_OF_ROWS; j++) {
                mGridCellOccupancy[i][j] = false;
            }
        }
    }

//    public void setOccupied(int xIndex, int yIndex, boolean isOccupied) {
//        mGridCellOccupancy[xIndex][yIndex] = isOccupied;
//    }

//    /**
//     *
//     */
//    public enum RowLetter {
//        A(0), B(1), C(2),
//        D(3), E(4), F(5),
//        G(6), H(7), I(8),
//        J(9), K(10), L(11),
//        M(12), N(13), O(14),
//        P(15), Q(16), R(17),
//        S(18), T(19), U(20),
//        V(21), W(22), X(23);
//
//        private int value;
//        RowLetter(int value) {
//            this.value = value;
//        }
//    };
}
