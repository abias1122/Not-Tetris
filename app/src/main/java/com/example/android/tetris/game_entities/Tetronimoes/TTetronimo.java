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
        int anchorXPos = mAnchorCell.getXPos();
        int anchorYPos = mAnchorCell.getYPos();
        Log.i(TAG, "=========IN ROTATE=========");
        Log.i(TAG, "anchorCell xPos: " + anchorXPos);
        Log.i(TAG, "anchorCell yPos: " + anchorYPos);

        switch (mCurrentState) {

            case ZERO_DEG: {

                if(anchorYPos == (NUM_ROWS - 1) || mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied()) {
                    useNormalRotate = false;

                    //this logging causes crash if tetronimo is at the bottom of the screen
                    //Log.i(TAG, "Cell below anchor occupied: " + mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied());
                }

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly left of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }
                }
                else {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly left of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }

                        //cell is directly right of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + mComponentCells[i].getXPos()];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }

                    mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
                    Log.i(TAG, "ANCHOR CHANGED");
                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                }

                mCurrentState = RotState.NINETY_DEG;
                break;
            }

            case NINETY_DEG: {

                if(anchorXPos == 0 || mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied()) {
                    Log.i(TAG, "Cell left of anchor occupied: " + mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied());
                    useNormalRotate = false;
                }

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly above anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }
                }
                else {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly above anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }

                        //cell is directly below anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[(mComponentCells[i].getYPos() * NUM_COLS) + (mComponentCells[i].getXPos() + 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }

                    mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];
                    Log.i(TAG, "ANCHOR CHANGED");
                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                }

                mCurrentState = RotState.ONE_EIGHTY_DEG;
                break;
            }

            case ONE_EIGHTY_DEG: {

                if(mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied()) {
                    Log.i(TAG, "Cell above anchor occupied: " + mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied());
                    useNormalRotate = false;
                }

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly right of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + mAnchorCell.getXPos()];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }
                }
                else {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly right of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos + 2) * NUM_COLS) + mAnchorCell.getXPos()];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }

                        //cell is directly right of anchor cell
                        if(mComponentCells[i].getXPos() == (mAnchorCell.getXPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((mComponentCells[i].getYPos() + 1) * NUM_COLS) + mComponentCells[i].getXPos()];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }

                    mAnchorCell = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];
                    Log.i(TAG, "ANCHOR CHANGED");
                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                }

                mCurrentState = RotState.TWO_SEVENTY_DEG;
                break;
            }

            case TWO_SEVENTY_DEG: {

                if(mAnchorCell.getXPos() == (NUM_COLS - 1) || mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied()) {
                    Log.i(TAG, "Cell right of anchor occupied: " + mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied());

                    useNormalRotate = false;
                }

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly below anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }
                }
                else {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        //cell is directly above anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[(mComponentCells[i].getYPos() * NUM_COLS) + (mComponentCells[i].getXPos() - 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }

                        //cell is directly below anchor cell
                        if(mComponentCells[i].getYPos() == (mAnchorCell.getYPos() + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 2)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }

                    mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];
                    Log.i(TAG, "ANCHOR CHANGED");
                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                }

                mCurrentState = RotState.ZERO_DEG;
                break;
            }
        }

        Log.i(TAG, "========DONE ROTATE=======");
    }
}
