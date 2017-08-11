package com.example.android.tetris.game_entities;

import com.example.android.tetris.R;

/**
 * Represents reverse-L tetronimo:
 *        +
 *        +
 *      + +
 */

public class ReverseLTetronimo extends Tetronimo {

    public ReverseLTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 1}, {4, 1}, {5, 1}, {3, 0}},
                R.drawable.reverse_l_tetron_grid_cell);
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        return;
    }
}
