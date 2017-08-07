package com.example.android.tetris.game_entities;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * A representation of a grid cell in the Tetris grid.
 */

public class GridCellView extends ImageView {

    private boolean isOccupied; //whether or not cell occupied by tetonimo
    private int xPos;
    private int yPos;
    private int cellImage;
    private OnTouchListener touchListener;

    public GridCellView(Context context, int xPos, int yPos) {
        super(context);
        this.xPos = xPos;
        this.yPos = yPos;
        isOccupied = false;
    }

    public GridCellView(Context context) {
        super(context);
        isOccupied = false;
    }

    public GridCellView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        isOccupied = false;
    }

    public GridCellView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isOccupied = false;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setCellImage(int imageResId) {
        cellImage = imageResId;
    }
}
