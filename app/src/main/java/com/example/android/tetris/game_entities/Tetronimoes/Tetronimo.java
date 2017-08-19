package com.example.android.tetris.game_entities.Tetronimoes;

import android.util.Log;
import android.widget.GridLayout;

import com.example.android.tetris.R;
import com.example.android.tetris.game_entities.GridCellView;

/**
 * A shape comprising of four squares used in Tetris
 */

public abstract class Tetronimo {

    protected final int NUM_COLS = 10;
    protected final int NUM_ROWS = 24;
    protected final int DRAWABLE_ID;
    protected final String TAG = "Tetronimo";

    protected GridCellView[] mComponentCells;
    protected GridCellView[] mGameGridCells;
    protected GridCellView mAnchorCell;

    protected enum RotState {
        ZERO_DEG, NINETY_DEG,
        ONE_EIGHTY_DEG, TWO_SEVENTY_DEG;
    }
    protected RotState mCurrentState;

    public Tetronimo(GridCellView[] gameGridCells, int[][] initialPositions, int drawableId, int anchorCellIndex) {

        mGameGridCells = gameGridCells;
        mComponentCells = new GridCellView[4];
        DRAWABLE_ID = drawableId;

        for(int i = 0; i < initialPositions.length; i++) {
            //index 0 is x position; index 1 is y position
            int[] xAndYPositions = initialPositions[i];
            mComponentCells[i]
                    = mGameGridCells[(xAndYPositions[1] * NUM_COLS) + xAndYPositions[0]];
        }

        for(GridCellView gridCell : mComponentCells) {
            gridCell.setOccupied(true);
            gridCell.setImageResource(DRAWABLE_ID);
        }

        mAnchorCell = mComponentCells[anchorCellIndex];
        mCurrentState = RotState.ZERO_DEG;
    }

    public abstract void rotate();

    public void moveDown() {

        int yPos;
        int xPos;

        sortComponentGridCellsByYPos();
        for(int i = 0; i < mComponentCells.length; i++) {

            if(mComponentCells[i].getYPos() == NUM_ROWS - 1) {
                //TODO: Implement place()
                return;
            }

            yPos = mComponentCells[i].getYPos();
            xPos = mComponentCells[i].getXPos();
            GridCellView cellToCheck = mGameGridCells[((yPos + 1) * NUM_COLS) + xPos];
//            Log.i(TAG, "cellToCheck xPos: " + cellToCheck.getXPos());
//            Log.i(TAG, "cellToCheck yPos: " + cellToCheck.getYPos());

            boolean checkedCellIsComponent = false;
            if(cellToCheck.getOccupied()) {

                for(GridCellView gridCell : mComponentCells) {
                    if(cellToCheck.equals(gridCell)) {
                        checkedCellIsComponent = true;
                        break;
                    }
                }
//                Log.i(TAG, "checkedCellIsComponent = " + checkedCellIsComponent);
                if(!checkedCellIsComponent) {
                    //TODO: Implement place()
                    return;
                }
            }
        }
        if(mAnchorCell.getYPos() < NUM_ROWS - 1) {
            mAnchorCell =
                    mGameGridCells[((mAnchorCell.getYPos() + 1) * NUM_COLS) + mAnchorCell.getXPos()];
        }

        Log.i(TAG, "ANCHOR CHANGED");
        Log.i(TAG, "anchorCell xPos: " + mAnchorCell.getXPos());
        Log.i(TAG, "anchorCell yPos: " + mAnchorCell.getYPos());

        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i].setOccupied(false);

            mComponentCells[i] = mGameGridCells[((yPos + 1) * NUM_COLS) + xPos];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
            mComponentCells[i].setOccupied(true);
        }

        Log.i(TAG, "------------------End moveDown()----------------------");
    }

    public void moveLeft() {
        int yPos;
        int xPos;

        sortComponentGridCellsByXPos();
        for(int i = 0; i < mComponentCells.length; i++) {

            if(mComponentCells[i].getXPos() == 0) {
                return;
            }

            yPos = mComponentCells[i].getYPos();
            xPos = mComponentCells[i].getXPos();
            GridCellView cellToCheck = mGameGridCells[(yPos * NUM_COLS) + (xPos - 1)];
//            Log.i(TAG, "xPos: " + xPos);
//            Log.i(TAG, "yPos: " + yPos);

            boolean checkedCellIsComponent = false;
            if(cellToCheck.getOccupied()) {

                for(GridCellView gridCell : mComponentCells) {
                    if(cellToCheck.equals(gridCell)) {
                        checkedCellIsComponent = true;
                        break;
                    }
                }
//                Log.i(TAG, "checkedCellIsComponent = " + checkedCellIsComponent);
                if(!checkedCellIsComponent) {
                    return;
                }
            }
        }

        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i].setOccupied(false);

            mComponentCells[i] = mGameGridCells[(yPos * NUM_COLS) + (xPos - 1)];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
            mComponentCells[i].setOccupied(true);
        }

        if(mAnchorCell.getXPos() != 0) {
            mAnchorCell =
                    mGameGridCells[(mAnchorCell.getYPos() * NUM_COLS) + (mAnchorCell.getXPos() - 1)];
        }

        Log.i(TAG, "ANCHOR CHANGED");
        Log.i(TAG, "anchorCell xPos: " + mAnchorCell.getXPos());
        Log.i(TAG, "anchorCell yPos: " + mAnchorCell.getYPos());
        Log.i(TAG, "------------------End moveLeft()----------------------");
    }

    public void moveRight() {
        int yPos;
        int xPos;

        sortComponentGridCellsByXPos();
        for (int i = mComponentCells.length - 1; i >= 0; i--) {

            if (mComponentCells[i].getXPos() == NUM_COLS - 1) {
                return;
            }

            yPos = mComponentCells[i].getYPos();
            xPos = mComponentCells[i].getXPos();
            GridCellView cellToCheck = mGameGridCells[(yPos * NUM_COLS) + (xPos + 1)];
//            Log.i(TAG, "xPos: " + xPos);
//            Log.i(TAG, "yPos: " + yPos);

            boolean checkedCellIsComponent = false;
            if (cellToCheck.getOccupied()) {

                for (GridCellView gridCell : mComponentCells) {
                    if (cellToCheck.equals(gridCell)) {
                        checkedCellIsComponent = true;
                        break;
                    }
                }
//                Log.i(TAG, "checkedCellIsComponent = " + checkedCellIsComponent);
                if (!checkedCellIsComponent) {
                    return;
                }
            }
        }

        for (int i = mComponentCells.length - 1; i >= 0; i--) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i].setOccupied(false);

            mComponentCells[i] = mGameGridCells[(yPos * NUM_COLS) + (xPos + 1)];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
            mComponentCells[i].setOccupied(true);
        }

        if (mAnchorCell.getXPos() != (NUM_COLS - 1)) {
            mAnchorCell =
                    mGameGridCells[(mAnchorCell.getYPos() * NUM_COLS) + (mAnchorCell.getXPos() + 1)];
        }
        Log.i(TAG, "ANCHOR CHANGED");
        Log.i(TAG, "anchorCell xPos: " + mAnchorCell.getXPos());
        Log.i(TAG, "anchorCell yPos: " + mAnchorCell.getYPos());
        Log.i(TAG, "------------------End moveRight()----------------------");
    }

    public void moveToBottom() {

        sortComponentGridCellsByYPos();
        //Lower rows are represented with higher numbers
        int highestRowToFallTo = getLowestFreeRowBeneathCell(mComponentCells[0]);
        int lowestRowInTetron = mComponentCells[0].getYPos();
        for(int i = 1; i < mComponentCells.length; i++) {
            if(mComponentCells[i].getYPos() < lowestRowInTetron) {
                continue;
            }

            int row = getLowestFreeRowBeneathCell(mComponentCells[i]);
            if(row < highestRowToFallTo) {
                highestRowToFallTo = row;
            }
        }

        int yPosToUse = mComponentCells[0].getYPos();
        int xPos;
        for(int i = 0; i < mComponentCells.length; i++) {

            xPos = mComponentCells[i].getXPos();
            if(mComponentCells[i].getYPos() < yPosToUse) {
                yPosToUse = mComponentCells[i].getYPos();
                highestRowToFallTo--;
            }

            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i].setOccupied(false);

            mComponentCells[i] = mGameGridCells[(highestRowToFallTo * NUM_COLS) + xPos];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
            mComponentCells[i].setOccupied(true);
        }
    }

    /**
     *
     * @param gridCell
     * @return lowest free row below gridCell. Returns gridCell's
     * current yPos if there is an occupied cell directly beneath
     */
    private int getLowestFreeRowBeneathCell(GridCellView gridCell) {
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

        return yPos;
    }

    private void sortComponentGridCellsByYPos() {

        //greatest to least
        boolean swapped;
        do {
            swapped = false;
            //
            // Log.i(TAG, "swapped: false");
            for(int i = 0; i < mComponentCells.length - 1; i++) {
                if(mComponentCells[i].getYPos() < mComponentCells[i + 1].getYPos()) {
                    GridCellView temp = mComponentCells[i];
                    mComponentCells[i] = mComponentCells[i + 1];
                    mComponentCells[i + 1] = temp;
                    swapped = true;
                    //Log.i(TAG, "swapped: true");
                }
            }
        } while (swapped);
    }

    private void sortComponentGridCellsByXPos() {

        boolean swapped;
        do {
            swapped = false;
            //
            // Log.i(TAG, "swapped: false");
            for(int i = 0; i < mComponentCells.length - 1; i++) {
                if(mComponentCells[i].getXPos() > mComponentCells[i + 1].getXPos()) {
                    GridCellView temp = mComponentCells[i];
                    mComponentCells[i] = mComponentCells[i + 1];
                    mComponentCells[i + 1] = temp;
                    swapped = true;
                    //Log.i(TAG, "swapped: true");
                }
            }
        } while (swapped);
    }
}
