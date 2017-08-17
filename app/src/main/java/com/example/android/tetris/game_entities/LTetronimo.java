package com.example.android.tetris.game_entities;

import com.example.android.tetris.R;

/**
 * Represents and L tetronimo:
 *
 * +
 * +
 * + +
 */

public class LTetronimo extends Tetronimo {

    public LTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 1}, {4, 1}, {5, 1}, {5, 0}},
                R.drawable.l_tetron_grid_cell,
                0);
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        return;
    }
}
