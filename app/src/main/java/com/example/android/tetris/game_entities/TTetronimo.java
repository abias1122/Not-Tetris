package com.example.android.tetris.game_entities;

import android.util.Log;
import android.widget.GridLayout;

import com.example.android.tetris.R;

/**
 * Represents T-tetronimo:
 *  + + +
 *    +
 */

public class TTetronimo extends Tetronimo {

    private enum RotState {
        ZERO_DEG, NINETY_DEG,
        ONE_EIGHTY_DEG, TWO_SEVENTY_DEG;
    }
    private RotState mCurrentState;

    private final String TAG = "TTetronimo";
    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 24;

    public TTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 1}, {4, 1}, {5, 1}, {4, 0}},
                R.drawable.t_tetron_grid_cell,
                1);

        mCurrentState = RotState.ZERO_DEG;
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        boolean useNormalRotate = true;
        GridCellView[] gameGridCells = super.getGameGridCells();
        GridCellView[] componentCells = super.getComponentCells();
        GridCellView anchorCell = getAnchorCell();

        switch (mCurrentState) {

            case ZERO_DEG: {
                int xPos = anchorCell.getXPos();
                int yPos = anchorCell.getYPos();

                if(gameGridCells[((yPos + 1) * NUM_COLS) + xPos].getOccupied()) {
                    useNormalRotate = false;
                }

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < componentCells.length; i++) {

                        //cell is directly left of anchor cell
                        if(componentCells[i].getXPos() == (anchorCell.getXPos() - 1)) {
                            componentCells[i].setImageResource(android.R.color.transparent);
                            componentCells[i] = gameGridCells[((yPos + 1) * NUM_COLS) + xPos];
                            componentCells[i].setImageResource(getDrawableId());
                        }
                        Log.i(TAG, "xPos: " + componentCells[i].getXPos());
                        Log.i(TAG, "yPos: " + componentCells[i].getYPos());
                    }
                }
                else {
                    for(int i = 0; i < componentCells.length; i++) {

                        //cell is directly left of anchor cell
                        if(componentCells[i].getXPos() == (anchorCell.getXPos() - 1)) {
                            componentCells[i].setImageResource(android.R.color.transparent);
                            componentCells[i] = gameGridCells[((yPos - 2) * NUM_COLS) + xPos];
                            componentCells[i].setImageResource(getDrawableId());
                        }

                        //cell is directly right of anchor cell
                        if(componentCells[i].getXPos() == (anchorCell.getXPos() + 1)) {
                            componentCells[i].setImageResource(android.R.color.transparent);
                            componentCells[i] = gameGridCells[((yPos - 1) * NUM_COLS) + componentCells[i].getXPos()];
                            componentCells[i].setImageResource(getDrawableId());
                        }
                    }
                }

                mCurrentState = RotState.NINETY_DEG;
            }

            case NINETY_DEG: {

            }

            case ONE_EIGHTY_DEG: {

            }

            case TWO_SEVENTY_DEG: {

            }
        }

    }
}
