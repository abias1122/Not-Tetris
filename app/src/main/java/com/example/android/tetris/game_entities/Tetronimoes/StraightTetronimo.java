package com.example.android.tetris.game_entities.Tetronimoes;

import android.util.Log;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

/**
 * Represents a straight tetronimo:
 *       + + + +
 */

public class StraightTetronimo extends Tetronimo {

    public StraightTetronimo(GridCellView[] gameGridCells) {
        super(gameGridCells,
                new int[][] {{3, 1}, {4, 1}, {5, 1}, {6, 1}},
                R.drawable.straight_tetron_grid_cell,
                2);
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
                        anchorYPos == 0 ||
                        anchorYPos == 1 ||
                        mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() ||
                        mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos].getOccupied() ||
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

                        //cell is two spaces left of anchor
                        if(componentXPos == (anchorXPos - 2) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos, anchorYPos - 2);
                        }

                        //cell is directly right of anchor cell
                        if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                        }

                        //cell is directly left of anchor cell
                        if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                        }
                    }
                }
                else {
                    if (anchorYPos > 2 &&
                            !mComponentCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos - 3) * NUM_COLS) + anchorXPos].getOccupied()) {

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

                            //cell is two cells left of anchor cell
                            if(componentXPos == (anchorXPos - 2) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 3);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else  if (anchorYPos > 0 &&
                            anchorYPos < (NUM_ROWS - 2) &&
                            !mComponentCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos + 2) * NUM_COLS) + anchorXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                            }

                            //cell is directly left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 2);
                            }

                            //cell is two cells left of anchor cell
                            if(componentXPos == (anchorXPos - 2) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else  if (anchorYPos < (NUM_ROWS - 3) &&
                            !mComponentCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos + 2) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos + 3) * NUM_COLS) + anchorXPos].getOccupied()) {

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

                            //cell is two cells left of anchor cell
                            if(componentXPos == (anchorXPos - 2) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 3);
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

                mCurrentState = RotState.NINETY_DEG;
                break;
            }

            case NINETY_DEG: {
                if(anchorXPos == 0 ||
                        anchorXPos == (NUM_COLS - 1) ||
                        anchorXPos == (NUM_COLS - 2) ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)].getOccupied()) {
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

                        //cell is two spaces above anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 2)) {
                            moveComponentToCell(i, anchorXPos + 2, anchorYPos);
                        }

                        //cell is directly above anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                            moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                        }

                        //cell is directly below anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                            moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                        }
                    }
                }
                else {
                    if (anchorXPos < (NUM_COLS - 3) &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 3)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                            }

                            //cell is directly above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos + 2, anchorYPos);
                            }

                            //cell is two cells above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 2)) {
                                moveComponentToCell(i, anchorXPos + 3, anchorYPos);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorXPos < (NUM_COLS - 1) &&
                            anchorXPos > 1 &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 2)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                            }

                            //cell is directly above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos - 2, anchorYPos);
                            }

                            //cell is two cells above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 2)) {
                                moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorXPos > 2 &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 2)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 3)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                            }

                            //cell is directly above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos - 2, anchorYPos);
                            }

                            //cell is two cells above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 2)) {
                                moveComponentToCell(i, anchorXPos - 3, anchorYPos);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 2)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    //no rotate
                    else {
                        break;
                    }
                }

                mCurrentState = RotState.ONE_EIGHTY_DEG;
                break;
            }

            case ONE_EIGHTY_DEG: {
                if(anchorYPos == (NUM_ROWS - 1) ||
                        anchorYPos == 0 ||
                        anchorYPos == (NUM_ROWS - 2) ||
                        mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() ||
                        mGameGridCells[((anchorYPos + 2) * NUM_COLS) + anchorXPos].getOccupied() ||
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

                        //cell is two spaces right of anchor
                        if(componentXPos == (anchorXPos + 2) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos, anchorYPos + 2);
                        }

                        //cell is directly right of anchor cell
                        if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                        }

                        //cell is directly left of anchor cell
                        if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                            moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                        }
                    }
                }
                else {
                    if (anchorYPos < (NUM_ROWS - 3) &&
                            !mComponentCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos + 2) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos + 3) * NUM_COLS) + anchorXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                            }

                            //cell is directly right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 2);
                            }

                            //cell is two cells right of anchor cell
                            if(componentXPos == (anchorXPos + 2) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 3);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorYPos < (NUM_ROWS - 1) &&
                            anchorYPos > 1 &&
                            !mComponentCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos + 1);
                            }

                            //cell is directly right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                            }

                            //cell is two cells right of anchor cell
                            if(componentXPos == (anchorXPos + 2) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 2);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorYPos > 2 &&
                            !mComponentCells[((anchorYPos - 1) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos].getOccupied() &&
                            !mComponentCells[((anchorYPos - 3) * NUM_COLS) + anchorXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly left of anchor cell
                            if(componentXPos == (anchorXPos - 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 1);
                            }

                            //cell is directly right of anchor cell
                            if(componentXPos == (anchorXPos + 1) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 2);
                            }

                            //cell is two cells right of anchor cell
                            if(componentXPos == (anchorXPos + 2) && componentYPos == anchorYPos) {
                                moveComponentToCell(i, anchorXPos, anchorYPos - 3);
                            }
                        }

                        mAnchorCell = mGameGridCells[((anchorYPos - 2) * NUM_COLS) + anchorXPos];
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
                if(anchorXPos == 0 ||
                        anchorXPos == (NUM_COLS - 1) ||
                        anchorXPos == 1 ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() ||
                        mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)].getOccupied()) {
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

                        //cell is two spaces below anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 2)) {
                            moveComponentToCell(i, anchorXPos - 2, anchorYPos);
                        }

                        //cell is directly above anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                            moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                        }

                        //cell is directly below anchor cell
                        if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                            moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                        }
                    }
                }
                else {
                    if (anchorXPos > 2 &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 2)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 3)].getOccupied()) {

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

                            //cell is two cells below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 2)) {
                                moveComponentToCell(i, anchorXPos - 3, anchorYPos);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorXPos > 0 &&
                            anchorXPos < (NUM_COLS - 2) &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly above anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos - 1)) {
                                moveComponentToCell(i, anchorXPos - 1, anchorYPos);
                            }

                            //cell is directly below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 1)) {
                                moveComponentToCell(i, anchorXPos + 1, anchorYPos);
                            }

                            //cell is two cells below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 2)) {
                                moveComponentToCell(i, anchorXPos + 2, anchorYPos);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];
                        Log.i(TAG, "ANCHOR CHANGED");
                        Log.i(TAG, "anchorXPos: " + mAnchorCell.getXPos());
                        Log.i(TAG, "anchorYPos: " + mAnchorCell.getYPos());
                    }
                    else if (anchorXPos < (NUM_COLS - 3) &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)].getOccupied() &&
                            !mComponentCells[(anchorYPos * NUM_COLS) + (anchorXPos + 3)].getOccupied()) {

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

                            //cell is two cells below anchor cell
                            if(componentXPos == anchorXPos && componentYPos == (anchorYPos + 2)) {
                                moveComponentToCell(i, anchorXPos + 3, anchorYPos);
                            }
                        }

                        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 2)];
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
        return "Straight";
    }
}
