package com.example.android.tetris.game_entities;

import com.example.android.tetris.R;

/**
 * Represents Z tetronimo:
 *    + +
 *      + +
 */

public class ZTetronimo extends Tetronimo {

    public ZTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 0}, {4, 0}, {4, 1}, {5, 1}},
                R.drawable.z_tetron_grid_cell);
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        return;
    }
}
