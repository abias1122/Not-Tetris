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

    /**
     * Called from subclass constructors
     * @param gameGridCells the grid cells being used in the current game
     * @param initialPositions starting X and Y positions for component cells
     * @param drawableId drawable to be used when occupying a cell
     * @param anchorCellIndex index of the anchor cell's X and Y positions in initialPositions
     */
    public Tetronimo(GridCellView[] gameGridCells, int[][] initialPositions, int drawableId, int anchorCellIndex) {

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

        mAnchorCell = mComponentCells[anchorCellIndex];
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
                    return false;
                }
            }
        }

        //move anchor down by 1 to keep up with component cells
        int anchorXPos = mAnchorCell.getXPos();
        int anchorYPos = mAnchorCell.getYPos();
        mAnchorCell = mGameGridCells[((anchorYPos + 1) * NUM_COLS) + anchorXPos];

//        Log.i(TAG, "ANCHOR CHANGED");
//        Log.i(TAG, "anchorCell xPos: " + mAnchorCell.getXPos());
//        Log.i(TAG, "anchorCell yPos: " + mAnchorCell.getYPos());

        //move each component cell down by 1
        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i].setOccupied(false);

            mComponentCells[i] = mGameGridCells[((yPos + 1) * NUM_COLS) + xPos];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
            mComponentCells[i].setOccupied(true);
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

        //move each component cell left by 1
        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i].setOccupied(false);

            mComponentCells[i] = mGameGridCells[(yPos * NUM_COLS) + (xPos - 1)];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
            mComponentCells[i].setOccupied(true);
        }

        //move anchor left by 1 to keep up with component cells
        int anchorXPos = mAnchorCell.getXPos();
        int anchorYPos = mAnchorCell.getYPos();
        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos - 1)];

//        Log.i(TAG, "ANCHOR CHANGED");
//        Log.i(TAG, "anchorCell xPos: " + mAnchorCell.getXPos());
//        Log.i(TAG, "anchorCell yPos: " + mAnchorCell.getYPos());
//        Log.i(TAG, "------------------End moveLeft()----------------------");
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

        //move each component cell right by 1
        for (int i = mComponentCells.length - 1; i >= 0; i--) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i].setOccupied(false);

            mComponentCells[i] = mGameGridCells[(yPos * NUM_COLS) + (xPos + 1)];
            mComponentCells[i].setImageResource(DRAWABLE_ID);
            mComponentCells[i].setOccupied(true);
        }

        //move anchor cell right by 1 to keep up with component cells
        int anchorXPos = mAnchorCell.getXPos();
        int anchorYPos = mAnchorCell.getYPos();
        mAnchorCell = mGameGridCells[(anchorYPos * NUM_COLS) + (anchorXPos + 1)];

//        Log.i(TAG, "ANCHOR CHANGED");
//        Log.i(TAG, "anchorCell xPos: " + mAnchorCell.getXPos());
//        Log.i(TAG, "anchorCell yPos: " + mAnchorCell.getYPos());
//        Log.i(TAG, "------------------End moveRight()----------------------");
    }

    /**
     * Move tetronimo to lowest available position directly beneath it
     */
    public void moveToBottom() {

        sortComponentGridCellsByYPos();
        int rowToFallTo = getLowestFreeRowBeneathCell(mComponentCells[0]);
        int lowestRowInTetron = mComponentCells[0].getYPos();
        
        for(int i = 1; i < mComponentCells.length; i++) {
            if(mComponentCells[i].getYPos() < lowestRowInTetron) {
                continue;
            }

            int row = getLowestFreeRowBeneathCell(mComponentCells[i]);
            if(row < rowToFallTo) {
                rowToFallTo = row;
            }
        }

        int yPosToUse = mComponentCells[0].getYPos();
        int xPos;
        for(int i = 0; i < mComponentCells.length; i++) {

            xPos = mComponentCells[i].getXPos();
            if(mComponentCells[i].getYPos() < yPosToUse) {
                yPosToUse = mComponentCells[i].getYPos();
                rowToFallTo--;
            }

            if(mComponentCells[i].equals(mAnchorCell)) {
                mAnchorCell = mGameGridCells[(rowToFallTo * NUM_COLS) + xPos];
            }

            mComponentCells[i].setImageResource(android.R.color.transparent);
            mComponentCells[i].setOccupied(false);

            mComponentCells[i] = mGameGridCells[(rowToFallTo * NUM_COLS) + xPos];
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

    /**
     * Sort component cells from greatest to least YPos
     */
    protected void sortComponentGridCellsByYPos() {

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

    /**
     * Sort component cells from least to greatest XPos
     */
    protected void sortComponentGridCellsByXPos() {

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
