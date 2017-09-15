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

        boolean useNormalRotate = true;
        int axisXPos = mAxisCell.getXPos();
        int axisYPos = mAxisCell.getYPos();
        Log.i(TAG, "=========IN ROTATE=========");
        Log.i(TAG, "axisCell xPos: " + axisXPos);
        Log.i(TAG, "axisCell yPos: " + axisYPos);

        switch (mCurrentState) {
            case ZERO_DEG: {
                if(axisYPos == 0 ||
                        mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)].getOccupied() ||
                        mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)].getOccupied()) {
                    useNormalRotate = false;
                }

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is diagonally bottom left of axis cell
                        if(componentXPos == (axisXPos - 1) && componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos - 1);
                        }

                        //cell is directly right of axis cell
                        if(componentXPos == (axisXPos + 1) && componentYPos == axisYPos) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos);
                        }
                    }
                }
                else {
                    if(axisYPos < (NUM_ROWS - 2) &&
                            !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                            !mGameGridCells[((axisYPos + 2) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly right of axis cell
                            if(componentYPos == axisYPos && componentXPos == (axisXPos + 1)) {
                                moveComponentToCell(i, axisXPos + 1, axisYPos + 1);
                            }

                            //cell is directly bottom left of axis cell
                            if(componentXPos == (axisXPos - 1) && componentYPos == (axisYPos + 1)) {
                                moveComponentToCell(i, axisXPos + 1, axisYPos + 2);
                            }
                        }

                        mAxisCell = mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                    }
                    else if (axisYPos < (NUM_ROWS - 2) &&
                            !mGameGridCells[((axisYPos + 2) * NUM_COLS) + axisXPos].getOccupied() &&
                            !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly right of axis cell
                            if(componentYPos == axisYPos && componentXPos == (axisXPos + 1)) {
                                moveComponentToCell(i, axisXPos, axisYPos + 2);
                            }

                            //cell is axis cell
                            if(mComponentCells[i].equals(mAxisCell)) {
                                moveComponentToCell(i, axisXPos - 1, axisYPos);
                            }
                        }

                        mAxisCell = mGameGridCells[((axisYPos + 1) * NUM_COLS) + axisXPos];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                    }
                    else if (axisYPos > 1 &&
                            !mGameGridCells[((axisYPos - 1) * NUM_COLS) + axisXPos].getOccupied() &&
                            !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly below axis cell
                            if(componentYPos == (axisYPos + 1) && componentXPos == axisXPos) {
                                moveComponentToCell(i, axisXPos + 1, axisYPos + 1);
                            }

                            //cell is directly bottom left of axis cell
                            if(componentXPos == (axisXPos - 1) && componentYPos == (axisYPos + 1)) {
                                moveComponentToCell(i, axisXPos, axisYPos - 1);
                            }
                        }

                        mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
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
                if(axisXPos == (NUM_COLS - 1) ||
                        mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)].getOccupied() ||
                        mGameGridCells[((axisYPos - 1) * NUM_COLS) + axisXPos].getOccupied()) {
                    useNormalRotate = false;
                }

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is diagonally top left of axis cell
                        if(componentXPos == (axisXPos - 1) && componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos, axisYPos - 1);
                        }

                        //cell is directly below axis cell
                        if(componentXPos == axisXPos && componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                        }
                    }
                }
                else {
                    if (axisXPos > 1 &&
                            !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 2)].getOccupied() &&
                            !mGameGridCells[((axisYPos - 1) * NUM_COLS) + axisXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly below axis cell
                            if(componentYPos == (axisYPos + 1) && componentXPos == axisXPos) {
                                moveComponentToCell(i, axisXPos - 2, axisYPos);
                            }

                            //cell is axis cell
                            if(mComponentCells[i].equals(mAxisCell)) {
                                moveComponentToCell(i, axisXPos, axisYPos - 1);
                            }
                        }

                        mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                    }
                    else if (axisXPos > 1 &&
                            !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 2)].getOccupied() &&
                            !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly below axis cell
                            if(componentYPos == (axisYPos + 1) && componentXPos == axisXPos) {
                                moveComponentToCell(i, axisXPos - 2, axisYPos + 1);
                            }

                            //cell is above left of axis cell
                            if(componentXPos == (axisXPos - 1) && componentYPos == (axisYPos - 1)) {
                                moveComponentToCell(i, axisXPos - 1, axisYPos + 1);
                            }
                        }

                        mAxisCell = mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                    }
                    else if (axisXPos < (NUM_COLS - 1) &&
                            !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                            !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly left axis cell
                            if(componentYPos == axisYPos && componentXPos == (axisXPos - 1)) {
                                moveComponentToCell(i, axisXPos + 1, axisYPos);
                            }

                            //cell is above left of axis cell
                            if(componentXPos == (axisXPos - 1) && componentYPos == (axisYPos - 1)) {
                                moveComponentToCell(i, axisXPos - 1, axisYPos + 1);
                            }
                        }

                        mAxisCell = mGameGridCells[((axisYPos + 1) * NUM_COLS) + axisXPos];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
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
                if(axisYPos == (NUM_ROWS - 1) ||
                        mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)].getOccupied() ||
                        mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)].getOccupied()) {
                    useNormalRotate = false;
                }

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is diagonally top right of axis cell
                        if(componentXPos == (axisXPos + 1) && componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos);
                        }

                        //cell is directly left of axis cell
                        if(componentXPos == (axisXPos - 1) && componentYPos == axisYPos) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos + 1);
                        }
                    }
                }
                else {
                    if (axisYPos > 1 &&
                            !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)].getOccupied() &&
                            !mGameGridCells[((axisYPos - 2) * NUM_COLS) + (axisXPos - 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly left axis cell
                            if(componentYPos == axisYPos && componentXPos == (axisXPos - 1)) {
                                moveComponentToCell(i, axisXPos - 1, axisYPos - 1);
                            }

                            //cell is above right of axis cell
                            if(componentXPos == (axisXPos + 1) && componentYPos == (axisYPos - 1)) {
                                moveComponentToCell(i, axisXPos - 1, axisYPos - 2);
                            }
                        }

                        mAxisCell = mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                    }
                    else if (axisYPos < (NUM_ROWS - 1) &&
                            !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)].getOccupied() &&
                            !mGameGridCells[((axisYPos + 1) * NUM_COLS) + axisXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly above axis cell
                            if(componentYPos == (axisYPos - 1) && componentXPos == axisXPos) {
                                moveComponentToCell(i, axisXPos - 1, axisYPos - 1);
                            }

                            //cell is above right of axis cell
                            if(componentXPos == (axisXPos + 1) && componentYPos == (axisYPos - 1)) {
                                moveComponentToCell(i, axisXPos, axisYPos + 1);
                            }
                        }

                        mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                    }
                    else if (axisYPos > 1 &&
                            !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                            !mGameGridCells[((axisYPos - 2) * NUM_COLS) + axisXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is axis cell
                            if(mComponentCells[i].equals(mAxisCell)) {
                                moveComponentToCell(i, axisXPos, axisYPos - 2);
                            }

                            //cell is directly left of axis cell
                            if(componentXPos == (axisXPos - 1) && componentYPos == axisYPos) {
                                moveComponentToCell(i, axisXPos + 1, axisYPos);
                            }
                        }

                        mAxisCell = mGameGridCells[((axisYPos - 1) * NUM_COLS) + axisXPos];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
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
                if(axisXPos == 0 ||
                        mGameGridCells[((axisYPos + 1) * NUM_COLS) + axisXPos].getOccupied() ||
                        mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)].getOccupied()) {
                    useNormalRotate = false;
                }

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is diagonally bottom right of axis cell
                        if(componentXPos == (axisXPos + 1) && componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos + 1);
                        }

                        //cell is directly above axis cell
                        if(componentXPos == axisXPos && componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos, axisYPos + 1);
                        }
                    }
                }
                else {
                    if (axisXPos < (NUM_COLS - 2) &&
                            !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 2)].getOccupied() &&
                            !mGameGridCells[((axisYPos + 1) * NUM_COLS) + axisXPos].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is directly above axis cell
                            if(componentXPos == axisXPos && componentYPos == (axisYPos - 1)) {
                                moveComponentToCell(i, axisXPos + 2, axisYPos);
                            }

                            //cell is axis cell
                            if(mComponentCells[i].equals(mAxisCell)) {
                                moveComponentToCell(i, axisXPos, axisYPos + 1);
                            }
                        }

                        mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                    }
                    else if (axisXPos > 0 &&
                            !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)].getOccupied() &&
                            !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is bottom right of axis cell
                            if(componentXPos == (axisXPos + 1) && componentYPos == (axisYPos + 1)) {
                                moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                            }

                            //cell is  directly right axis cell
                            if(componentXPos == (axisXPos + 1) && componentYPos == axisYPos) {
                                moveComponentToCell(i, axisXPos - 1, axisYPos);
                            }
                        }

                        mAxisCell = mGameGridCells[((axisYPos - 1) * NUM_COLS) + axisXPos];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                    }
                    else  if (axisXPos < (NUM_COLS - 2) &&
                            !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                            !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 2)].getOccupied()) {

                        for(int i = 0; i < mComponentCells.length; i++) {

                            componentXPos = mComponentCells[i].getXPos();
                            componentYPos = mComponentCells[i].getYPos();

                            //cell is bottom right of axis cell
                            if(componentXPos == (axisXPos + 1) && componentYPos == (axisYPos + 1)) {
                                moveComponentToCell(i, axisXPos + 2, axisYPos - 1);
                            }

                            //cell is  directly above axis cell
                            if(componentXPos == axisXPos && componentYPos == (axisYPos - 1)) {
                                moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                            }
                        }

                        mAxisCell = mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)];
                        Log.i(TAG, "AXIS CHANGED");
                        Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                        Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
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
        return "S";
    }
}
