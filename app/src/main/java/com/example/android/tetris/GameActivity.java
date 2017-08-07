package com.example.android.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;

import com.example.android.tetris.game_entities.GridCellView;

public class GameActivity extends AppCompatActivity {

    private GridLayout mGameboard;
    private GridCellView[] mGridCellsViews;

    private final int NUM_COLS = 10;
    private final int NUM_ROWS = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameboard = (GridLayout) findViewById(R.id.gameboard);
        mGameboard.setColumnCount(NUM_COLS);
        mGameboard.setRowCount(NUM_ROWS);
        mGridCellsViews = new GridCellView[NUM_COLS * NUM_ROWS];

        for(int x = 0; x < NUM_COLS; x++) {
            for(int y = 0; y < NUM_ROWS; y++) {
                GridCellView newGridCell = new GridCellView(this, x, y);
//                newGridCell.setMaxHeight(1);
//                newGridCell.setMaxWidth(1);
                mGridCellsViews[(x * NUM_ROWS) + y] = newGridCell;
                mGameboard.addView(newGridCell);
            }
        }
    }
}
