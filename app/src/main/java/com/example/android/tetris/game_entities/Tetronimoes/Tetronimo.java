package com.example.android.tetris.game_entities.Tetronimoes;

import com.example.android.tetris.game_entities.Gameboard;
import com.example.android.tetris.game_entities.GridCellView;

/**
 * @author Anthony Bias
 * Tracks 4 {@link GridCellView}'s positions on the Gameboard and
 * provides ways for the player and game to move them as a whole.
 *
 * Different subclasses of Tetronimo store GridCellViews in different configurations
 * and have different ways of moving component cells relative to eachother.
 */
//TODO: Make unit tests to properly test rotation
public abstract class Tetronimo {

    final int NUM_COLS = 10;
    final int NUM_ROWS = 24;

    /**
     * ID for the drawable resource used to
     * fill in GridCellViews. Each Tetronimo subclass
     * has its own drawable.
     */
    final private int DRAWABLE_ID;

    /**
     * Gameboard for current game.
     */
    Gameboard mGameBoard;

    /**
     * GridCellViews that the Tetronimo tracks.
     */
    private GridCellView[] mComponentCells;

    /**
     * GridCellView that other component cells are
     * moved relative to when the tetronimo is moved.
     */
    GridCellView mAxisCell;

    /**
     * Used for logging.
     */
    final String TAG = "Tetronimo";

    /**
     * Rotation state
     */
    enum RotState {
        ZERO_DEG, NINETY_DEG,
        ONE_EIGHTY_DEG, TWO_SEVENTY_DEG;
    }

    /**
     * Used  to determine how an object of a Tetronimo subclass rotates.
     */
    RotState mCurrentState;

    /**
     * Constructor
     * @param gameboard        the gameboard being used in the current game
     * @param initialPositions starting X and Y positions for component cells
     * @param drawableId       drawable to be used when occupying a cell
     * @param axisCellIndex    index of the axis cell's X and Y positions in initialPositions
     */
    public Tetronimo(Gameboard gameboard, int[][] initialPositions, int drawableId, int axisCellIndex) {

        mGameBoard = gameboard;
        mComponentCells = new GridCellView[4];
        DRAWABLE_ID = drawableId;

        //Set component cells
        for(int i = 0; i < initialPositions.length; i++) {

            int[] xAndYPositions = initialPositions[i];
            int cellXPos = xAndYPositions[0];
            int cellYPos = xAndYPositions[1];

            mComponentCells[i] = mGameBoard.getGridCell(cellXPos, cellYPos);
        }

        mAxisCell = mComponentCells[axisCellIndex];
        mCurrentState = RotState.ZERO_DEG;
    }

    /**
     * Make tetronimo appear on screen
     */
    public void putInGame() {
        for (GridCellView componentCell : mComponentCells) {
            componentCell.setOccupied(true);
            componentCell.setImageResource(DRAWABLE_ID);
        }
    }

    /**
     * Rotate tetronimo 90 degrees clockwise
     */
    public abstract void rotate();

    /**
     * Called by a Tetronimo subclass's implementation of rotate(). Moves cells to
     * where they need to be once rotate() decides which cells need to go where.
     * @param fromCoordinates     array of array of x and y positions of component cells to move from.
     *                            in the sub arrays, put x in index 0 and y in index 1.
     * @param toCoordinates       array of x and y positions to move component cells to.
     *                            in the sub arrays, put x in index 0 and y in index 1.
     *                            function assumes toCoordinates has same size as fromCoordinates
     * @param newAxisCoordinates  new coordinates for axis cell: if axis is not changing, use
     *                            current mAxisCell coordinates.
     */
    void rotate(int[][] fromCoordinates, int[][] toCoordinates, int[] newAxisCoordinates) {

        GridCellView componentCell;
        for(int i = 0; i < fromCoordinates.length; i++) {
            for (int j = 0; j < mComponentCells.length; j++) {
                componentCell = mComponentCells[j];
                if (componentCell.getXPos() == fromCoordinates[i][0] &&
                        componentCell.getYPos() == fromCoordinates[i][1]) {
                    moveComponentToCell(j, toCoordinates[i][0], toCoordinates[i][1]);
                    break;
                }
            }
        }

        mAxisCell = mGameBoard.getGridCell(newAxisCoordinates[0], newAxisCoordinates[1]);
    }

    /**
     * Move each component cell down by 1 if space is available.
     * Used to make the tetronimo fall down the screen.
     * @return true if tetronimo can move down, false if otherwise
     */
    public boolean moveDown() {

        int yPos;
        int xPos;

        sortComponentGridCellsByYPos();
        //check that tetronimo can move down
        for (GridCellView componentCell : mComponentCells) {

            if (componentCell.getYPos() == NUM_ROWS - 1) {
                return false;
            }

            yPos = componentCell.getYPos();
            xPos = componentCell.getXPos();
            GridCellView cellToCheck = mGameBoard.getGridCell(xPos, yPos + 1);

            if (cellToCheck.getOccupied()) {
                boolean checkedCellIsComponent = false;

                //check if occupied cell is a component cell
                for (GridCellView gridCell : mComponentCells) {
                    if (cellToCheck.equals(gridCell)) {
                        checkedCellIsComponent = true;
                        break;
                    }
                }
                if (!checkedCellIsComponent) {
                    return false;
                }
            }
        }

        //move axis down by 1 to keep up with component cells
        int axisXPos = mAxisCell.getXPos();
        int axisYPos = mAxisCell.getYPos();
        mAxisCell = mGameBoard.getGridCell(axisXPos, axisYPos + 1);

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

        sortComponentGridCellsByXPos();
        //check if cells directly left of component cells are occupied
        for (GridCellView componentCell : mComponentCells) {

            if (componentCell.getXPos() == 0) {
                return;
            }

            yPos = componentCell.getYPos();
            xPos = componentCell.getXPos();
            GridCellView cellToCheck = mGameBoard.getGridCell(xPos - 1, yPos);

            if (cellToCheck.getOccupied()) {
                boolean checkedCellIsComponent = false;

                //check if occupied cell is a component
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

        //move each component cell left by 1
        for(int i = 0; i < mComponentCells.length; i++) {
            xPos = mComponentCells[i].getXPos();
            yPos = mComponentCells[i].getYPos();
            moveComponentToCell(i, xPos - 1, yPos);
        }

        //move axis left by 1 to keep up with component cells
        int axisXPos = mAxisCell.getXPos();
        int axisYPos = mAxisCell.getYPos();
        mAxisCell = mGameBoard.getGridCell(axisXPos - 1, axisYPos);
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
            GridCellView cellToCheck = mGameBoard.getGridCell(xPos + 1, yPos);

            if (cellToCheck.getOccupied()) {
                boolean checkedCellIsComponent = false;

                //check if occupied cell is a component
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
        mAxisCell = mGameBoard.getGridCell(axisXPos + 1, axisYPos);
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
            if(mGameBoard.getGridCell(xPos, newYPos).getOccupied()) {
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

    /**
     *
     * @param gridCell GridCellView to look beneath
     * @return lowest free row below gridCell. Returns gridCell's
     * current yPos if there is an occupied cell directly beneath
     */
    private GridCellView getLowestFreeCellBeneathCell(GridCellView gridCell) {
        int yPos = gridCell.getYPos();
        int xPos = gridCell.getXPos();
        boolean lowerCellExists = yPos < NUM_ROWS - 1;

        while(lowerCellExists) {
            if(mGameBoard.getGridCell(xPos, yPos + 1).getOccupied()) {
                break;
            }

            yPos++;
            lowerCellExists = yPos < NUM_ROWS - 1;
        }

        return mGameBoard.getGridCell(xPos, yPos);
    }

    /**
     * Change the position of a component GridCellView on the gameboard
     * @param componentIndex index in mComponentCells of the cell to move
     * @param newXPos        xPos to move the cell to
     * @param newYPos        yPos to move the cell to
     */
    private void moveComponentToCell(int componentIndex, int newXPos, int newYPos) {

        mComponentCells[componentIndex].setImageResource(android.R.color.transparent);
        mComponentCells[componentIndex].setOccupied(false);

        mComponentCells[componentIndex] = mGameBoard.getGridCell(newXPos, newYPos);
        mComponentCells[componentIndex].setImageResource(DRAWABLE_ID);
        mComponentCells[componentIndex].setOccupied(true);
    }

    /**
     * Sort component cells from greatest to least YPos
     */
    private void sortComponentGridCellsByYPos() {

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
    private void sortComponentGridCellsByXPos() {

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
