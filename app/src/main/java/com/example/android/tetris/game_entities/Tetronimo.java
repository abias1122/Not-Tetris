package com.example.android.tetris.game_entities;

import android.util.Log;
import android.widget.GridLayout;

import com.example.android.tetris.R;

/**
 * A shape comprising of four squares used in Tetris
 */

public abstract class Tetronimo {

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 24;
    private final int DRAWABLE_ID;
    private final String TAG = "Tetronimo";

    private GridCellView[] mComponentCells;
    private GridCellView[] mGameGridCells;
    private SortCode lastUsedSortCode;

    private enum SortCode {
        X_SORT, Y_SORT, UNSORTED
    }

    public Tetronimo(GridCellView[] gameGridCells, int[][] initialPositions, int drawableId) {

        mGameGridCells = gameGridCells;
        mComponentCells = new GridCellView[4];
        DRAWABLE_ID = drawableId;
        lastUsedSortCode = SortCode.UNSORTED;

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
    }

    public GridCellView[] getGameGridCells() {
        return mGameGridCells;
    }

    public GridCellView[] getComponentCells() {
        return mComponentCells;
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
                Log.i(TAG, "checkedCellIsComponent = " + checkedCellIsComponent);
                if(!checkedCellIsComponent) {
                    //TODO: Implement place()
                    return;
                }
            }
        }

        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i] = mGameGridCells[((yPos + 1) * NUM_COLS) + xPos];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
        }

//        Log.i(TAG, "break");
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
                Log.i(TAG, "checkedCellIsComponent = " + checkedCellIsComponent);
                if(!checkedCellIsComponent) {
                    return;
                }
            }
        }

        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i] = mGameGridCells[(yPos * NUM_COLS) + (xPos - 1)];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
        }

//        Log.i(TAG, "break");
    }

    public void moveRight() {
        int yPos;
        int xPos;
        sortComponentGridCellsByXPos();
        for(int i = mComponentCells.length - 1; i >= 0; i--) {

            if(mComponentCells[i].getXPos() == NUM_COLS - 1) {
                return;
            }

            yPos = mComponentCells[i].getYPos();
            xPos = mComponentCells[i].getXPos();
            GridCellView cellToCheck = mGameGridCells[(yPos * NUM_COLS) + (xPos + 1)];
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
                Log.i(TAG, "checkedCellIsComponent = " + checkedCellIsComponent);
                if(!checkedCellIsComponent) {
                    return;
                }
            }
        }

        for(int i = mComponentCells.length - 1; i >= 0; i--) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i] = mGameGridCells[(yPos * NUM_COLS) + (xPos + 1)];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
        }

//        Log.i(TAG, "break");
    }

    private void sortComponentGridCellsByYPos() {
        if(lastUsedSortCode == SortCode.Y_SORT) {
            return;
        }

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

        lastUsedSortCode = SortCode.Y_SORT;
    }

    private void sortComponentGridCellsByXPos() {
        if(lastUsedSortCode == SortCode.X_SORT) {
            return;
        }

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

        lastUsedSortCode = SortCode.X_SORT;
    }
}
