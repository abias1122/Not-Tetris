package com.example.android.tetris.game_entities;

import com.example.android.tetris.R;

/**
 * Represents T-tetronimo:
 *  + + +
 *    +
 */

public class TTetronimo extends Tetronimo {

    public TTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 1}, {4, 1}, {5, 1}, {4, 0}},
                R.drawable.t_tetron_grid_cell);
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        return;
    }
}
