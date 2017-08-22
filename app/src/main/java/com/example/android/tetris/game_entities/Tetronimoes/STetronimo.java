package com.example.android.tetris.game_entities.Tetronimoes;

import android.util.Log;

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
                2);
    }

    @Override
    public void rotate() {
        //TODO: Handle weird edge cases
        //There is no code to handle if the non-normal
        //rotations also go into occupied cells
        boolean useNormalRotate = true;
        int anchorXPos = mAnchorCell.getXPos();
        int anchorYPos = mAnchorCell.getYPos();
        Log.i(TAG, "=========IN ROTATE=========");
        Log.i(TAG, "anchorCell xPos: " + anchorXPos);
        Log.i(TAG, "anchorCell yPos: " + anchorYPos);

        switch (mCurrentState) {
            case ZERO_DEG: {
                if(anchorYPos == 0 ||
                        mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos - 1)].getOccupied() ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied()) {
                    useNormalRotate = false;

                    //this logging causes crash if tetronimo is at the bottom of the screen
                    //Log.i(TAG, "Cell below anchor occupied: " + mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied());
                }

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is diagonally bottom left of anchor cell
                        if(componentXPos == (anchorXPos - 1) && componentYPos == (anchorYPos + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos - 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }

                        //cell is directly right of anchor cell
                        if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }
                }
                //TODO: handle unusual rotations
//                else {
//                    for(int i = 0; i < mComponentCells.length; i++) {
//
//                        componentXPos = mComponentCells[i].getXPos();
//
//                        //cell is directly left of anchor cell
//                        if(componentXPos == (anchorXPos - 1)) {
//                            mComponentCells[i].setImageResource(android.R.color.transparent);
//                            mComponentCells[i].setOccupied(false);
//
//                            mComponentCells[i] = mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos];
//                            mComponentCells[i].setImageResource(DRAWABLE_ID);
//                            mComponentCells[i].setOccupied(true);
//                        }
//
//                        //cell is directly right of anchor cell
//                        if(componentXPos == (anchorXPos + 1)) {
//                            mComponentCells[i].setImageResource(android.R.color.transparent);
//                            mComponentCells[i].setOccupied(false);
//
//                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos + 1)];
//                            mComponentCells[i].setImageResource(DRAWABLE_ID);
//                            mComponentCells[i].setOccupied(true);
//                        }
//                    }
//
//                    mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
//                    Log.i(TAG, "ANCHOR CHANGED");
//                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
//                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
//                }

                mCurrentState = RotState.NINETY_DEG;
                break;
            }

            case NINETY_DEG: {
                if(anchorXPos == (NUM_COLS - 1) ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() ||
                        mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied()) {
                    useNormalRotate = false;

                    //this logging causes crash if tetronimo is at the bottom of the screen
                    //Log.i(TAG, "Cell below anchor occupied: " + mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied());
                }

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is diagonally top left of anchor cell
                        if(componentXPos == (anchorXPos - 1) && componentYPos == (anchorYPos - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }

                        //cell is directly below anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos + 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }
                }
                //TODO: handle unusual rotations
//                else {
//                    for(int i = 0; i < mComponentCells.length; i++) {
//
//                        componentXPos = mComponentCells[i].getXPos();
//
//                        //cell is directly left of anchor cell
//                        if(componentXPos == (anchorXPos - 1)) {
//                            mComponentCells[i].setImageResource(android.R.color.transparent);
//                            mComponentCells[i].setOccupied(false);
//
//                            mComponentCells[i] = mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos];
//                            mComponentCells[i].setImageResource(DRAWABLE_ID);
//                            mComponentCells[i].setOccupied(true);
//                        }
//
//                        //cell is directly right of anchor cell
//                        if(componentXPos == (anchorXPos + 1)) {
//                            mComponentCells[i].setImageResource(android.R.color.transparent);
//                            mComponentCells[i].setOccupied(false);
//
//                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos + 1)];
//                            mComponentCells[i].setImageResource(DRAWABLE_ID);
//                            mComponentCells[i].setOccupied(true);
//                        }
//                    }
//
//                    mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
//                    Log.i(TAG, "ANCHOR CHANGED");
//                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
//                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
//                }

                mCurrentState = RotState.ONE_EIGHTY_DEG;
                break;
            }

            case ONE_EIGHTY_DEG: {
                if(anchorYPos == (NUM_ROWS - 1) ||
                        mGameGridCells[((anchorYPos + 1) * NUM_COLS) + (anchorXPos + 1)].getOccupied() ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied()) {
                    useNormalRotate = false;

                    //this logging causes crash if tetronimo is at the bottom of the screen
                    //Log.i(TAG, "Cell below anchor occupied: " + mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied());
                }

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is diagonally top right of anchor cell
                        if(componentXPos == (anchorXPos + 1) && componentYPos == (anchorYPos - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }

                        //cell is directly left of anchor cell
                        if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + (anchorXPos + 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }
                }
                //TODO: handle unusual rotations
//                else {
//                    for(int i = 0; i < mComponentCells.length; i++) {
//
//                        componentXPos = mComponentCells[i].getXPos();
//
//                        //cell is directly left of anchor cell
//                        if(componentXPos == (anchorXPos - 1)) {
//                            mComponentCells[i].setImageResource(android.R.color.transparent);
//                            mComponentCells[i].setOccupied(false);
//
//                            mComponentCells[i] = mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos];
//                            mComponentCells[i].setImageResource(DRAWABLE_ID);
//                            mComponentCells[i].setOccupied(true);
//                        }
//
//                        //cell is directly right of anchor cell
//                        if(componentXPos == (anchorXPos + 1)) {
//                            mComponentCells[i].setImageResource(android.R.color.transparent);
//                            mComponentCells[i].setOccupied(false);
//
//                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos + 1)];
//                            mComponentCells[i].setImageResource(DRAWABLE_ID);
//                            mComponentCells[i].setOccupied(true);
//                        }
//                    }
//
//                    mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
//                    Log.i(TAG, "ANCHOR CHANGED");
//                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
//                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
//                }

                mCurrentState = RotState.TWO_SEVENTY_DEG;
                break;
            }

            case TWO_SEVENTY_DEG: {
                if(anchorXPos == 0 ||
                        mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied() ||
                        mGameGridCells[((anchorYPos + 1) * NUM_COLS) + (anchorXPos - 1)].getOccupied()) {
                    useNormalRotate = false;

                    //this logging causes crash if tetronimo is at the bottom of the screen
                    //Log.i(TAG, "Cell below anchor occupied: " + mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied());
                }

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is diagonally bottom right of anchor cell
                        if(componentXPos == (anchorXPos + 1) && componentYPos == (anchorYPos + 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + (anchorXPos - 1)];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }

                        //cell is directly above anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                            mComponentCells[i].setImageResource(android.R.color.transparent);
                            mComponentCells[i].setOccupied(false);

                            mComponentCells[i] = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];
                            mComponentCells[i].setImageResource(DRAWABLE_ID);
                            mComponentCells[i].setOccupied(true);
                        }
                    }
                }
                //TODO: handle unusual rotations
//                else {
//                    for(int i = 0; i < mComponentCells.length; i++) {
//
//                        componentXPos = mComponentCells[i].getXPos();
//
//                        //cell is directly left of anchor cell
//                        if(componentXPos == (anchorXPos - 1)) {
//                            mComponentCells[i].setImageResource(android.R.color.transparent);
//                            mComponentCells[i].setOccupied(false);
//
//                            mComponentCells[i] = mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos];
//                            mComponentCells[i].setImageResource(DRAWABLE_ID);
//                            mComponentCells[i].setOccupied(true);
//                        }
//
//                        //cell is directly right of anchor cell
//                        if(componentXPos == (anchorXPos + 1)) {
//                            mComponentCells[i].setImageResource(android.R.color.transparent);
//                            mComponentCells[i].setOccupied(false);
//
//                            mComponentCells[i] = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos + 1)];
//                            mComponentCells[i].setImageResource(DRAWABLE_ID);
//                            mComponentCells[i].setOccupied(true);
//                        }
//                    }
//
//                    mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
//                    Log.i(TAG, "ANCHOR CHANGED");
//                    Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
//                    Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
//                }

                mCurrentState = RotState.ZERO_DEG;
                break;
            }
        }
    }
}
