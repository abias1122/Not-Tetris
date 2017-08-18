package com.example.android.tetris.game_entities.Tetronimoes;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

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
