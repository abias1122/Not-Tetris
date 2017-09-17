package com.example.android.tetris.game_entities.Tetronimoes;

import android.util.Log;
import android.widget.GridLayout;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

/**
 * A shape comprising of four squares used in Tetris
 */

public abstract class Tetronimo {

    final int NUM_COLS = 10;
    final int NUM_ROWS = 24;
    final int DRAWABLE_ID;
    final String TAG = "Tetronimo";

    GridCellView[] mComponentCells;
    GridCellView[] mGameGridCells;
    GridCellView mAxisCell;

    enum RotState {
        ZERO_DEG, NINETY_DEG,
        ONE_EIGHTY_DEG, TWO_SEVENTY_DEG;
    }
    RotState mCurrentState;

    /**
     * Called from subclass constructors
     * @param gameGridCells the grid cells being used in the current game
     * @param initialPositions starting X and Y positions for component cells
     * @param drawableId drawable to be used when occupying a cell
     * @param axisCellIndex index of the axis cell's X and Y positions in initialPositions
     */
    public Tetronimo(GridCellView[] gameGridCells, int[][] initialPositions, int drawableId, int axisCellIndex) {

        mGameGridCells = gameGridCells;
        mComponentCells = new GridCellView[4];
        DRAWABLE_ID = drawableId;

        //Set component cells
        for(int i = 0; i < initialPositions.length; i++) {

            int[] xAndYPositions = initialPositions[i];
            int cellXPos = xAndYPositions[0];
            int cellYPos = xAndYPositions[1];

            mComponentCells[i] = mGameGridCells[(cellYPos * NUM_COLS) + cellXPos];
        }

        mAxisCell = mComponentCells[axisCellIndex];
        mCurrentState = RotState.ZERO_DEG;
    }

    public void putInGame() {
        for(int i = 0; i < mComponentCells.length; i++) {
            mComponentCells[i].setOccupied(true);
            mComponentCells[i].setImageResource(DRAWABLE_ID);
        }
    }

    /**
     * Rotate tetronimo 90 degrees clockwise
     */
    public abstract void rotate();

    /**
     * Move each component cell down by 1 if space is available.
     * Used to make the tetronimo fall down the screen.
     * @return true if tetronimo could move down, false if otherwise
     */
    public boolean moveDown() {

        int yPos;
        int xPos;

        sortComponentGridCellsByYPos();
        //check that tetronimo can move down
        for(int i = 0; i < mComponentCells.length; i++) {

            if(mComponentCells[i].getYPos() == NUM_ROWS - 1) {
                return false;
            }

            yPos = mComponentCells[i].getYPos();
            xPos = mComponentCells[i].getXPos();
            GridCellView cellToCheck = mGameGridCells[((yPos + 1) * NUM_COLS) + xPos];

            boolean checkedCellIsComponent = false;
            if(cellToCheck.getOccupied()) {

                for(GridCellView gridCell : mComponentCells) {
                    if(cellToCheck.equals(gridCell)) {
                        checkedCellIsComponent = true;
                        break;
                    }
                }
                if(!checkedCellIsComponent) {
                    return false;
                }
            }
        }

        //move axis down by 1 to keep up with component cells
        int axisXPos = mAxisCell.getXPos();
        int axisYPos = mAxisCell.getYPos();
        mAxisCell = mGameGridCells[((axisYPos + 1) * NUM_COLS) + axisXPos];

        //move each component cell down by 1
        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            moveComponentToCell(i, xPos, yPos + 1);
        }

        return true;
//        Log.i(TAG, "------------------End moveDown()----------------------");
    }

    /**
     * Move the tetronimo left by 1 cell
     */
    public void moveLeft() {
        int yPos;
        int xPos;

        //check if cells directly left of component cells are occupied
        sortComponentGridCellsByXPos();
        for(int i = 0; i < mComponentCells.length; i++) {

            if(mComponentCells[i].getXPos() == 0) {
                return;
            }

            yPos = mComponentCells[i].getYPos();
            xPos = mComponentCells[i].getXPos();
            GridCellView cellToCheck = mGameGridCells[(yPos * NUM_COLS) + (xPos - 1)];

            boolean checkedCellIsComponent = false;
            if(cellToCheck.getOccupied()) {

                for(GridCellView gridCell : mComponentCells) {
                    if(cellToCheck.equals(gridCell)) {
                        checkedCellIsComponent = true;
                        break;
                    }
                }
                if(!checkedCellIsComponent) {
                    return;
                }
            }
        }

        //move each component cell left by 1
        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            moveComponentToCell(i, xPos - 1, yPos);
        }

        //move axis left by 1 to keep up with component cells
        int axisXPos = mAxisCell.getXPos();
        int axisYPos = mAxisCell.getYPos();
        mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos - 1)];
    }

    /**
     * Move the tetronimo right by 1 cell
     */
    public void moveRight() {
        int yPos;
        int xPos;

        //check if cells directly right of component cells are occupied
        sortComponentGridCellsByXPos();
        for (int i = mComponentCells.length - 1; i >= 0; i--) {

            if (mComponentCells[i].getXPos() == NUM_COLS - 1) {
                return;
            }

            yPos = mComponentCells[i].getYPos();
            xPos = mComponentCells[i].getXPos();
            GridCellView cellToCheck = mGameGridCells[(yPos * NUM_COLS) + (xPos + 1)];

            boolean checkedCellIsComponent = false;
            if (cellToCheck.getOccupied()) {

                for (GridCellView gridCell : mComponentCells) {
                    if (cellToCheck.equals(gridCell)) {
                        checkedCellIsComponent = true;
                        break;
                    }
                }
                if (!checkedCellIsComponent) {
                    return;
                }
            }
        }

        //move each component cell right by 1
        for (int i = mComponentCells.length - 1; i >= 0; i--) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            moveComponentToCell(i, xPos + 1, yPos);
        }

        //move axis cell right by 1 to keep up with component cells
        int axisXPos = mAxisCell.getXPos();
        int axisYPos = mAxisCell.getYPos();
        mAxisCell = mGameGridCells[(axisYPos * NUM_COLS) + (axisXPos + 1)];
    }

    /**
     * Move tetronimo to lowest available position directly beneath it
     */
    public void moveToBottom() {
        sortComponentGridCellsByYPos();
        int highestLowRow = getLowestFreeCellBeneathCell(mComponentCells[0]).getYPos();
        GridCellView anchorCell = mComponentCells[0];
        
        for(int i = 1; i < mComponentCells.length; i++) {

            GridCellView cell = getLowestFreeCellBeneathCell(mComponentCells[i]);

            if(cell.getYPos() < highestLowRow && !isComponentCell(cell)) {
                highestLowRow = cell.getYPos();
                anchorCell = mComponentCells[i];
            }
        }

        for(int i = 0; i < mComponentCells.length; i++) {
            int yPosDifference = anchorCell.getYPos() - mComponentCells[i].getYPos();
            int newYPos = highestLowRow - yPosDifference;
            int xPos = mComponentCells[i].getXPos();

            //prevent L and ReverseLTetronimoes from clipping into cells
            //i set to -1 to have loop start from beginning at continue
            if(mGameGridCells[(newYPos * NUM_COLS) + xPos].getOccupied()) {
                highestLowRow --;
                i = -1;
                continue;
            }

            moveComponentToCell(i, xPos, newYPos);
        }
    }

    /**
     * Determine if a given GridCellView object is an one of the
     * tetronimo's component cells
     *
     * @param gridCell cell to check
     * @return true if gridCell is a component cell, false otherwise
     */
    private boolean isComponentCell(GridCellView gridCell) {
        for(GridCellView component : mComponentCells) {
            if(gridCell.equals(component)) {
                return true;
            }
        }

        return false;
    }

    //TODO: Fix Documentation
    /**
     *
     * @param gridCell
     * @return lowest free row below gridCell. Returns gridCell's
     * current yPos if there is an occupied cell directly beneath
     */
    private GridCellView getLowestFreeCellBeneathCell(GridCellView gridCell) {
        int yPos = gridCell.getYPos();
        int xPos = gridCell.getXPos();
        boolean lowerCellExists = yPos < NUM_ROWS - 1;

        while(lowerCellExists) {
            if(mGameGridCells[((yPos + 1) * NUM_COLS) + xPos].getOccupied()) {
                break;
            }

            yPos++;
            lowerCellExists = yPos < NUM_ROWS - 1;
        }

        return mGameGridCells[(yPos * NUM_COLS) + xPos];
    }

    protected void moveComponentToCell(int componentIndex, int newXPos, int newYPos) {

        mComponentCells[componentIndex].setImageResource(android.R.color.transparent);
        mComponentCells[componentIndex].setOccupied(false);

        mComponentCells[componentIndex] = mGameGridCells[(newYPos * NUM_COLS) + newXPos];
        mComponentCells[componentIndex].setImageResource(DRAWABLE_ID);
        mComponentCells[componentIndex].setOccupied(true);
    }

    /**
     * Sort component cells from greatest to least YPos
     */
    protected void sortComponentGridCellsByYPos() {

        //greatest to least
        boolean swapped;
        do {
            swapped = false;
            for(int i = 0; i < mComponentCells.length - 1; i++) {
                if(mComponentCells[i].getYPos() < mComponentCells[i + 1].getYPos()) {
                    GridCellView temp = mComponentCells[i];
                    mComponentCells[i] = mComponentCells[i + 1];
                    mComponentCells[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    /**
     * Sort component cells from least to greatest XPos
     */
    protected void sortComponentGridCellsByXPos() {

        boolean swapped;
        do {
            swapped = false;
            for(int i = 0; i < mComponentCells.length - 1; i++) {
                if(mComponentCells[i].getXPos() > mComponentCells[i + 1].getXPos()) {
                    GridCellView temp = mComponentCells[i];
                    mComponentCells[i] = mComponentCells[i + 1];
                    mComponentCells[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
}
