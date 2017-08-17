package com.example.android.tetris.game_entities;

import com.example.android.tetris.R;

/**
 * Represents a straight tetronimo:
 *       + + + +
 */

public class StraightTetronimo extends Tetronimo {

    public StraightTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 1}, {4, 1}, {5, 1}, {6, 1}},
                R.drawable.straight_tetron_grid_cell,
                0);
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        return;
    }
}
