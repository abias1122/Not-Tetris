package com.example.android.tetris.game_entities.Tetronimoes;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

/**
 * Tetronimo shaped like a square:
 *
 * + +
 * + +
 */

public class SquareTetronimo extends Tetronimo {

    public SquareTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{4, 0}, {5, 0}, {4, 1}, {5, 1}},
                R.drawable.square_tetron_grid_cell,
                0);
    }

    @Override
    public void rotate() {
        //rotating a square 90 degrees doesn't change appearance
        return;
    }
}
