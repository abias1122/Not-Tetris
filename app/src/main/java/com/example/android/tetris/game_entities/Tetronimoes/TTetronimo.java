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

        boolean useNormalRotate = true;
        int axisXPos = mAxisCell.getXPos();
        int axisYPos = mAxisCell.getYPos();
        Log.i(TAG, "=========IN ROTATE=========");
        Log.i(TAG, "axisCell xPos: " + axisXPos);
        Log.i(TAG, "axisCell yPos: " + axisYPos);

        switch (mCurrentState) {

            case ZERO_DEG: {

                useNormalRotate = axisYPos < (NUM_ROWS - 1) &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + axisXPos].getOccupied();
                int componentXPos;
                int componentYPos;

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();

                        //cell is directly left of axis cell
                        if(componentXPos == (axisXPos - 1)) {
                            moveComponentToCell(i, axisXPos, axisYPos + 1);
                        }
                    }
                }
                else if(axisYPos > 1 &&
                        !mGameGridCells[((axisYPos - 2) * NUM_COLS) + axisXPos].getOccupied() &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();

                        //cell is directly left of axis cell
                        if(componentXPos == (axisXPos - 1)) {
                            moveComponentToCell(i, axisXPos, axisYPos - 2);
                        }

                        //cell is directly right of axis cell
                        if(componentXPos == (axisXPos + 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                        }
                    }

                    mAxisCell = mGameGridCells[((axisYPos - 1) * NUM_COLS) + axisXPos];
                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if(axisYPos < (NUM_ROWS - 1) &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)].getOccupied() &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly above axis cell
                        if(componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos - 1);
                        }

                        //cell is directly right of axis cell
                        if(componentXPos == (axisXPos + 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos + 1);
                        }
                    }

                    mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)];
                }
                else if(axisYPos > 1 &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)].getOccupied() &&
                        !mGameGridCells[((axisYPos - 2) * NUM_COLS) + (axisXPos - 1)].getOccupied()) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();

                        //axis cell
                        if(mComponentCells[i].equals(mAxisCell)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos - 1);
                        }

                        //cell is directly right of axis cell
                        if(componentXPos == (axisXPos + 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos - 2);
                        }
                    }

                    mAxisCell = mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)];
                }
                else if(axisXPos < (NUM_COLS - 2) &&
                        axisYPos < (NUM_ROWS - 1) &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                        !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 2)].getOccupied() &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //axis cell
                        if(mComponentCells[i].equals(mAxisCell)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                        }

                        //cell is directly left of axis cell
                        if(componentXPos == (axisXPos - 1)) {
                            moveComponentToCell(i, axisXPos + 2, axisYPos);
                        }

                        //cell is directly above axis cell
                        if(componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos + 1);
                        }
                    }

                    mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)];
                }
                else if(axisXPos < (NUM_COLS - 2) &&
                        axisYPos > 2 &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 2)].getOccupied() &&
                        !mGameGridCells[((axisYPos - 2) * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //axis cell
                        if(mComponentCells[i].equals(mAxisCell)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                        }

                        //cell is directly left of axis cell
                        if(componentXPos == (axisXPos - 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos - 2);
                        }

                        //cell is directly above axis cell
                        if(componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos + 2, axisYPos - 1);
                        }
                    }

                    mAxisCell = mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)];
                }
                //cannot rotate
                else {
                    break;
                }

                mCurrentState = RotState.NINETY_DEG;
                break;
            }

            case NINETY_DEG: {

                useNormalRotate = axisXPos > 0 &&
                        !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)].getOccupied();

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly above axis cell
                        if(componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos);
                        }
                    }
                }
                else if(axisXPos < (NUM_COLS - 2) &&
                        !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 2)].getOccupied() &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()
                        ) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly above axis cell
                        if(componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos + 2, axisYPos);
                        }

                        //cell is directly below axis cell
                        if(componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos + 1);
                        }
                    }

                    mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)];
                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if(axisXPos > 0 &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)].getOccupied() &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly right of axis cell
                        if(componentXPos == (axisXPos + 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                        }

                        //cell is directly below axis cell
                        if(componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos - 1);
                        }
                    }

                    mAxisCell = mGameGridCells[((axisYPos - 1) * NUM_COLS) + axisXPos];
                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if(axisXPos < (NUM_COLS - 2) &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 2)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentYPos = mComponentCells[i].getYPos();

                        //cell is axis cell
                        if(mComponentCells[i].equals(mAxisCell)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                        }

                        //cell is directly below axis cell
                        if(componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos + 2, axisYPos - 1);
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

                mCurrentState = RotState.ONE_EIGHTY_DEG;
                break;
            }

            case ONE_EIGHTY_DEG: {

                useNormalRotate = axisYPos > 0 &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + axisXPos].getOccupied();

                int componentXPos;
                int componentYPos;
                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        //cell is directly right of axis cell
                        if(componentXPos == (axisXPos + 1)) {
                            moveComponentToCell(i, axisXPos, axisYPos - 1);
                        }
                    }
                }
                else if(axisYPos < (NUM_ROWS - 2) &&
                        !mGameGridCells[((axisYPos + 2) * NUM_COLS) + axisXPos].getOccupied() &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();

                        //cell is directly right of axis cell
                        if(componentXPos == (axisXPos + 1)) {
                            moveComponentToCell(i, axisXPos, axisYPos + 2);
                        }

                        //cell is directly left of axis cell
                        if(componentXPos == (axisXPos - 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos + 1);
                        }
                    }

                    mAxisCell = mGameGridCells[((axisYPos + 1) * NUM_COLS) + axisXPos];
                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if (axisYPos > 0 &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly below axis cell
                        if(componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos - 1);
                        }

                        //cell is directly left of axis cell
                        if(componentXPos == (axisXPos - 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos + 1);
                        }
                    }

                    mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)];
                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if(axisYPos < (NUM_ROWS - 2) &&
                        !mGameGridCells[((axisYPos + 2) * NUM_COLS) + (axisXPos + 1)].getOccupied() &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();

                        //cell is axis cell
                        if(mComponentCells[i].equals(mAxisCell)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos + 1);
                        }

                        //cell is directly left of axis cell
                        if(componentXPos == (axisXPos - 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos + 2);
                        }
                    }

                    mAxisCell = mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)];
                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                //no rotate
                else {
                    break;
                }

                mCurrentState = RotState.TWO_SEVENTY_DEG;
                break;
            }

            case TWO_SEVENTY_DEG: {

                useNormalRotate = axisXPos < (NUM_COLS - 1) &&
                        !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)].getOccupied();
                int componentXPos;
                int componentYPos;

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {
                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly below axis cell
                        if(componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos + 1, axisYPos);
                        }
                    }
                }
                else if(axisXPos > 1 &&
                        !mGameGridCells[((axisYPos - 1) * NUM_COLS) + (axisXPos - 1)].getOccupied() &&
                        !mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 2)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly above axis cell
                        if(componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos - 1);
                        }

                        //cell is directly below axis cell
                        if(componentYPos == (axisYPos + 1)) {
                            moveComponentToCell(i, axisXPos - 2, axisYPos);
                        }
                    }

                    mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)];
                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if(axisXPos > 1 &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 2)].getOccupied() &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly above axis cell
                        if(componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos + 1);
                        }

                        //cell is axis cell
                        if(mComponentCells[i].equals(mAxisCell)) {
                            moveComponentToCell(i, axisXPos - 2, axisYPos + 1);
                        }
                    }

                    mAxisCell = mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)];
                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if(axisXPos < (NUM_COLS - 1) &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos - 1)].getOccupied() &&
                        !mGameGridCells[((axisYPos + 1) * NUM_COLS) + (axisXPos + 1)].getOccupied()) {

                    for(int i = 0; i < mComponentCells.length; i++) {

                        componentXPos = mComponentCells[i].getXPos();
                        componentYPos = mComponentCells[i].getYPos();

                        //cell is directly above axis cell
                        if(componentYPos == (axisYPos - 1)) {
                            moveComponentToCell(i, axisXPos - 1, axisYPos + 1);
                        }

                        //cell is directly left ofaxis cell
                        if(componentXPos == axisXPos - 1) {
                            moveComponentToCell(i, axisXPos +1, axisYPos + 1);
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

                mCurrentState = RotState.ZERO_DEG;
                break;
            }
        }

        Log.i(TAG, "========DONE ROTATE=======");
    }

    @Override
    public String toString() {
        return "T";
    }
}
