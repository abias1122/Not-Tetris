package com.example.android.tetris.game_entities;

import android.widget.GridLayout;

import com.example.android.tetris.R;

/**
 * A shape comprising of four squares used in Tetris
 */

public abstract class Tetronimo {

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 24;
    private final int DRAWABLE_ID;

    private GridCellView[] mComponentCells;
    private GridCellView[] mGameGridCells;

    public Tetronimo(GridCellView[] gameGridCells, int[][] initialPositions, int drawableId) {

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
    }

    public GridCellView[] getGameGridCells() {
        return mGameGridCells;
    }

    public GridCellView[] getComponentCells() {
        return mComponentCells;
    }

    public abstract void rotate();

    public void moveDown() {
        //TODO: Implement moveDown()
        int yPos;
        int xPos;

        for(int i = 0; i < mComponentCells.length; i++) {

            if(mComponentCells[i].getYPos() == NUM_ROWS - 1) {
                //TODO: Implement place()
                return;
            }

            yPos = mComponentCells[i].getYPos();
            xPos = mComponentCells[i].getXPos();
            GridCellView cellToCheck = mGameGridCells[((yPos + 1) * NUM_COLS) + xPos];

            //Need to add 1 to yPos to check one cell lower than the currently occupied
            //component cell, and y numbers increase from top to bottom for GridLayouts
            boolean checkedCellIsComponent = false;
            if(cellToCheck.getOccupied()) {

                for(GridCellView gridCell : mComponentCells) {
                    if(cellToCheck.equals(gridCell)) {
                        checkedCellIsComponent = true;
                        break;
                    }
                }

                if(!checkedCellIsComponent) {
                    //TODO: Implement place()
                    return;
                }
            }
        }

        for(GridCellView gridCell: mComponentCells) {
            xPos = gridCell.getXPos();
            yPos = gridCell.getYPos();
            gridCell.setImageResource(android.R.color.transparent);
            gridCell = mGameGridCells[((yPos + 1) * NUM_COLS) + xPos];
            gridCell.setImageResource(DRAWABLE_ID);
        }
    }
}
