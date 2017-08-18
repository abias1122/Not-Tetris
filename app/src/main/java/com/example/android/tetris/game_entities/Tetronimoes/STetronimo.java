package com.example.android.tetris.game_entities.Tetronimoes;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

/**
 * Represents S tetronimo:
 *     + +
 *   + +
 */

public class STetronimo extends Tetronimo {

    public STetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 1}, {4, 1}, {4, 0}, {5, 0}},
                R.drawable.s_tetron_grid_cell,
                0);
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        return;
    }
}
