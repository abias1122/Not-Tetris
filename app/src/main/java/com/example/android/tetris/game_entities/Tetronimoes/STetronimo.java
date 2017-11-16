package com.example.android.tetris.game_entities.Tetronimoes;

import android.util.Log;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.Gameboard;

/**
 * Represents S tetronimo:
 *     + +
 *   + +
 */

public class STetronimo extends Tetronimo {

    public STetronimo(Gameboard gameboard) {
        super(gameboard,
                new int[][] {{3, 1}, {4, 1}, {4, 0}, {5, 0}},
                R.drawable.s_tetron_grid_cell,
                2);
    }

    @Override
    public void rotate() {

        boolean useNormalRotate;
        int axisXPos = mAxisCell.getXPos();
        int axisYPos = mAxisCell.getYPos();
        Log.i(TAG, "=========IN ROTATE=========");
        Log.i(TAG, "axisCell xPos: " + axisXPos);
        Log.i(TAG, "axisCell yPos: " + axisYPos);

        switch (mCurrentState) {
            case ZERO_DEG: {
                useNormalRotate = axisYPos > 0 &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos - 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos).getOccupied();

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    int[][] fromCoordinates = {{axisXPos - 1, axisYPos + 1}, {axisXPos + 1, axisYPos}};
                    int[][] toCoordinates   = {{axisXPos - 1, axisYPos - 1}, {axisXPos - 1, axisYPos}};
                    int[] newAxisCoordinates = {axisXPos, axisYPos};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);
                }
                else if(axisYPos < (mGameBoard.NUM_ROWS - 2) &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos + 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos + 2).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos + 1, axisYPos}, {axisXPos - 1, axisYPos + 1}};
                    int[][] toCoordinates   = {{axisXPos + 1, axisYPos + 1}, {axisXPos + 1, axisYPos + 2}};
                    int[] newAxisCoordinates = {axisXPos + 1, axisYPos + 1};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if (axisYPos < (mGameBoard.NUM_ROWS - 2) &&
                        !mGameBoard.getGridCell(axisXPos, axisYPos + 2).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos + 1, axisYPos}, {axisXPos, axisYPos}};
                    int[][] toCoordinates   = {{axisXPos, axisYPos + 2}, {axisXPos - 1, axisYPos}};
                    int[] newAxisCoordinates = {axisXPos, axisYPos + 1};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if (axisYPos > 1 &&
                        !mGameBoard.getGridCell(axisXPos, axisYPos - 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos + 1).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos, axisYPos + 1}, {axisXPos - 1, axisYPos + 1}};
                    int[][] toCoordinates   = {{axisXPos + 1, axisYPos + 1}, {axisXPos, axisYPos - 1}};
                    int[] newAxisCoordinates = {axisXPos + 1, axisYPos};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                //no rotate
                else {
                    break;
                }

                mCurrentState = RotState.NINETY_DEG;
                break;
            }

            case NINETY_DEG: {
                useNormalRotate = axisXPos < mGameBoard.NUM_COLS - 1 &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos - 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos, axisYPos - 1).getOccupied();

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    int[][] fromCoordinates = {{axisXPos - 1, axisYPos - 1}, {axisXPos, axisYPos + 1}};
                    int[][] toCoordinates   = {{axisXPos, axisYPos - 1}, {axisXPos + 1, axisYPos - 1}};
                    int[] newAxisCoordinates = {axisXPos, axisYPos};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);
                }
                else if (axisXPos > 1 &&
                        !mGameBoard.getGridCell(axisXPos - 2, axisYPos).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos, axisYPos - 1).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos, axisYPos + 1}, {axisXPos, axisYPos}};
                    int[][] toCoordinates   = {{axisXPos - 2, axisYPos}, {axisXPos, axisYPos - 1}};
                    int[] newAxisCoordinates = {axisXPos - 1, axisYPos};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if (axisXPos > 1 &&
                        !mGameBoard.getGridCell(axisXPos - 2, axisYPos + 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos + 1).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos, axisYPos + 1}, {axisXPos - 1, axisYPos - 1}};
                    int[][] toCoordinates   = {{axisXPos - 2, axisYPos + 1}, {axisXPos - 1, axisYPos + 1}};
                    int[] newAxisCoordinates = {axisXPos - 1, axisYPos + 1};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if (axisXPos < (mGameBoard.NUM_COLS - 1) &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos + 1).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos - 1, axisYPos}, {axisXPos - 1, axisYPos - 1}};
                    int[][] toCoordinates   = {{axisXPos + 1, axisYPos}, {axisXPos - 1, axisYPos + 1}};
                    int[] newAxisCoordinates = {axisXPos, axisYPos + 1};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

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
                useNormalRotate = axisYPos < mGameBoard.NUM_ROWS - 1 &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos + 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos).getOccupied();

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    int[][] fromCoordinates = {{axisXPos + 1, axisYPos - 1}, {axisXPos - 1, axisYPos}};
                    int[][] toCoordinates   = {{axisXPos + 1, axisYPos}, {axisXPos + 1, axisYPos + 1}};
                    int[] newAxisCoordinates = {axisXPos, axisYPos};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);
                }
                else if (axisYPos > 1 &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos - 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos - 2).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos - 1, axisYPos}, {axisXPos + 1, axisYPos - 1}};
                    int[][] toCoordinates   = {{axisXPos - 1, axisYPos - 1}, {axisXPos - 1, axisYPos - 2}};
                    int[] newAxisCoordinates = {axisXPos - 1, axisYPos - 1};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if (axisYPos < (mGameBoard.NUM_ROWS - 1) &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos - 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos, axisYPos + 1).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos, axisYPos - 1}, {axisXPos + 1, axisYPos - 1}};
                    int[][] toCoordinates   = {{axisXPos - 1, axisYPos - 1}, {axisXPos, axisYPos + 1}};
                    int[] newAxisCoordinates = {axisXPos - 1, axisYPos};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if (axisYPos > 1 &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos, axisYPos - 2).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos, axisYPos}, {axisXPos - 1, axisYPos}};
                    int[][] toCoordinates   = {{axisXPos, axisYPos - 2}, {axisXPos + 1, axisYPos}};
                    int[] newAxisCoordinates = {axisXPos, axisYPos - 1};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

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
                useNormalRotate = axisXPos > 0 &&
                        !mGameBoard.getGridCell(axisXPos, axisYPos + 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos + 1).getOccupied();

                Log.i(TAG, "useNormalRotate = " + useNormalRotate);
                if(useNormalRotate) {

                    int[][] fromCoordinates = {{axisXPos + 1, axisYPos + 1}, {axisXPos, axisYPos - 1}};
                    int[][] toCoordinates   = {{axisXPos - 1, axisYPos + 1}, {axisXPos, axisYPos + 1}};
                    int[] newAxisCoordinates = {axisXPos, axisYPos};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);
                }
                else if (axisXPos < (mGameBoard.NUM_COLS - 2) &&
                        !mGameBoard.getGridCell(axisXPos + 2, axisYPos).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos, axisYPos + 1).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos, axisYPos - 1}, {axisXPos, axisYPos}};
                    int[][] toCoordinates   = {{axisXPos + 2, axisYPos}, {axisXPos, axisYPos + 1}};
                    int[] newAxisCoordinates = {axisXPos + 1, axisYPos};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else if (axisXPos > 0 &&
                        !mGameBoard.getGridCell(axisXPos - 1, axisYPos).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos - 1).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos + 1, axisYPos + 1}, {axisXPos + 1, axisYPos}};
                    int[][] toCoordinates   = {{axisXPos + 1, axisYPos - 1}, {axisXPos - 1, axisYPos}};
                    int[] newAxisCoordinates = {axisXPos, axisYPos - 1};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

                    Log.i(TAG, "AXIS CHANGED");
                    Log.i(TAG, "axisXPos: " + mAxisCell.getXPos());
                    Log.i(TAG, "axisYPos: " + mAxisCell.getYPos());
                }
                else  if (axisXPos < (mGameBoard.NUM_COLS - 2) &&
                        !mGameBoard.getGridCell(axisXPos + 1, axisYPos - 1).getOccupied() &&
                        !mGameBoard.getGridCell(axisXPos + 2, axisYPos - 1).getOccupied()) {

                    int[][] fromCoordinates = {{axisXPos + 1, axisYPos + 1}, {axisXPos, axisYPos - 1}};
                    int[][] toCoordinates   = {{axisXPos + 2, axisYPos - 1}, {axisXPos + 1, axisYPos - 1}};
                    int[] newAxisCoordinates = {axisXPos + 1, axisYPos - 1};
                    rotate(fromCoordinates, toCoordinates, newAxisCoordinates);

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
    }

    @Override
    public String toString() {
        return "S";
    }
}
