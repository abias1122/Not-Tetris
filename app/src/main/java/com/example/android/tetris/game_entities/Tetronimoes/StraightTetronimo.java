package com.example.android.tetris.game_entities.Tetronimoes;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

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
