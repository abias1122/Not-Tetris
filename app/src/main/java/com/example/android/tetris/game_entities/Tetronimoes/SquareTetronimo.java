package com.example.android.tetris.game_entities.Tetronimoes;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.Gameboard;

/**
 * Tetronimo shaped like a square:
 *
 * + +
 * + +
 */

public class SquareTetronimo extends Tetronimo {

    public SquareTetronimo(Gameboard gameboard) {
        super(gameboard,
                new int[][] {{4, 0}, {5, 0}, {4, 1}, {5, 1}},
                R.drawable.square_tetron_grid_cell);
    }

    @Override
    public void rotate() {/*rotating a square 90 degrees doesn't change appearance*/}

    @Override
    public String toString() {
        return "Square";
    }
}
