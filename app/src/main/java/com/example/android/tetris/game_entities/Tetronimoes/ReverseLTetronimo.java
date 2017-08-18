package com.example.android.tetris.game_entities.Tetronimoes;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

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
                R.drawable.reverse_l_tetron_grid_cell,
                0);
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        return;
    }
}
