package com.example.android.tetris.game_entities.Tetronimoes;

import android.util.Log;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

/**
 * Represents T-tetronimo:
 *  + + +
 *    +
 */

public class TTetronimo extends Tetronimo {

    private final String TAG = "TTetronimo";

    public TTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 1}, {4, 1}, {5, 1}, {4, 0}},
                R.drawable.t_tetron_grid_cell,
                1);
    }

    @Override
    public void rotate() {
        //TODO: implement rotate()
        boolean useNormalRotate = true;

        switch (mCurrentState) {

            case ZERO_DEG: {
                int anchorXPos = mAnchorCell.getXPos();
                int anchorYPos = mAnchorCell.getYPos();

                if(anchorYPos == (NUM_ROWS - 1) || mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied()) {
                    useNormalRotate = false;
                }

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly left of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i] = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                        }
                    }
                }
                else {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly left of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i] = mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                        }

                        //cell is directly right of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + mComponentCells[i].getXPos()];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                        }
                    }

                    mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
                    Log.i(TAG, "ANCHOR CHANGED");
                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                }

                mCurrentState = RotState.NINETY_DEG;
                return;
            }

            case NINETY_DEG: {
                int anchorXPos = mAnchorCell.getXPos();
                int anchorYPos = mAnchorCell.getYPos();

                if(anchorXPos == 0 || mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied()) {
                    useNormalRotate = false;
                }

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly above anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i] = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                        }
                    }
                }
                else {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly above anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i] = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                        }

                        //cell is directly below anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i] = mGameGridCells[(mComponentCells[i].getYPos() * NUM_COLS) + (mComponentCells[i].getXPos() + 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                        }
                    }

                    mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];
                    Log.i(TAG, "ANCHOR CHANGED");
                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                }

                mCurrentState = RotState.ONE_EIGHTY_DEG;
                return;
            }

            case ONE_EIGHTY_DEG: {

            }

            case TWO_SEVENTY_DEG: {

            }
        }

    }
}
