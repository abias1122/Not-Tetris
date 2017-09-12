package com.example.android.tetris.game_entities.Tetronimoes;

import android.util.Log;

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
                1);
    }

    @Override
    public void rotate() {

        boolean useNormalRotate = true;
        int anchorXPos = mAnchorCell.getXPos();
        int anchorYPos = mAnchorCell.getYPos();
        Log.i(TAG, "=========IN ROTATE=========");
        Log.i(TAG, "anchorCell xPos: " + anchorXPos);
        Log.i(TAG, "anchorCell yPos: " + anchorYPos);

        switch (mCurrentState) {
            case ZERO_DEG: {
                if(anchorYPos == (NUM_ROWS - 1) ||
                        mGameGridCells[((anchorYPos + 1) * NUM_COLS) + (anchorXPos + 1)].getOccupied() ||
                        mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() ||
                        mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied()) {
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
                            moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                        }

                        //cell is directly right of anchor cell
                        if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos + 1, anchorYPos + 1);
                        }

                        //cell is directly left of anchor cell
                        if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                        }
                    }
                }
                else {
                    if (anchorYPos < (NUM_ROWS - 2) &&
                            !mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mGameGridCells[((anchorYPos + 2) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mGameGridCells[((anchorYPos + 2) * NUM_COLS) + (anchorXPos + 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                            }

                            //cell is directly left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 2);
                            }

                            //cell is directly top right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos + 1, anchorYPos + 2);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorYPos > 1 &&
                            !mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 2);
                            }

                            //cell is directly top right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    //no rotate
                    else {
                        break;
                    }
                }

                mCurrentState = RotState.NINETY_DEG;
                break;
            }

            case NINETY_DEG: {
                if(anchorXPos == 0 ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() ||
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
                            moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                        }

                        //cell is directly above anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                            moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                        }

                        //cell is directly below anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                            moveComponentToCell(i, anchorXPos - 1, anchorYPos + 1);
                        }
                    }
                }
                else {
                    if (anchorXPos < (NUM_COLS - 2) &&
                            !mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() &&
                            !mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                            }

                            //cell is directly bottom right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos + 2, anchorYPos);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorXPos > 1 &&
                            !mGameGridCells[((anchorYPos + 1) * NUM_COLS) + (anchorXPos - 2)].getOccupied() &&
                            !mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 2)].getOccupied() &&
                            !mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                            }

                            //cell is directly below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos - 2, anchorYPos);
                            }

                            //cell is directly bottom right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos - 2, anchorYPos + 1);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else {
                        break;
                    }
                }

                mCurrentState = RotState.ONE_EIGHTY_DEG;
                break;
            }

            case ONE_EIGHTY_DEG: {
                if(anchorYPos == 0 ||
                        mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() ||
                        mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos - 1)].getOccupied() ||
                        mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied()) {
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
                            moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                        }

                        //cell is directly right of anchor cell
                        if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                        }

                        //cell is directly left of anchor cell
                        if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos - 1, anchorYPos - 1);
                        }
                    }
                }
                else {
                    if (anchorYPos > 1 &&
                            !mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mGameGridCells[((anchorYPos - 2) * NUM_COLS) + (anchorXPos - 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                            }

                            //cell is directly left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 2);
                            }

                            //cell is diagonally bottom left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos - 1, anchorYPos - 2);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorYPos < (NUM_ROWS - 2) &&
                            !mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mGameGridCells[((anchorYPos + 2) * NUM_COLS) + anchorXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 2);
                            }

                            //cell is diagonally bottom left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    //no rotate
                    else {
                        break;
                    }
                }

                mCurrentState = RotState.TWO_SEVENTY_DEG;
                break;
            }

            case TWO_SEVENTY_DEG: {
                if(anchorXPos == (NUM_COLS - 1) ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() ||
                        mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos + 1)].getOccupied()) {
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
                            moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                        }

                        //cell is directly above anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                            moveComponentToCell(i, anchorXPos + 1, anchorYPos - 1);
                        }

                        //cell is directly below anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                            moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                        }
                    }
                }
                else {
                    if (anchorXPos < (NUM_COLS - 2) &&
                            !mGameGridCells[((anchorYPos - 1) * NUM_COLS) + (anchorXPos + 2)].getOccupied() &&
                            !mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)].getOccupied() &&
                            !mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                            }

                            //cell is directly below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos + 2, anchorYPos);
                            }

                            //cell is diagonally top left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos + 2, anchorYPos - 1);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorXPos > 1 &&
                            !mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() &&
                            !mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 2)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                            }

                            //cell is diagonally top left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos - 2, anchorYPos);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    //no rotate
                    else {
                        break;
                    }
                }

                mCurrentState = RotState.ZERO_DEG;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "L";
    }
}
