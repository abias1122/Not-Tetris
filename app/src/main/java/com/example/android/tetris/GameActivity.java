package com.example.android.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;

import com.example.android.tetris.game_entities.GridCellView;
import com.example.android.tetris.game_entities.SquareTetronimo;

public class GameActivity extends AppCompatActivity {

    private final String TAG = "GameActivity";
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
                mGridCellsViews[(x * NUM_ROWS) + y] = newGridCell;
                mGameboard.addView(newGridCell);
            }
        }

        mGameboard.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int parentWidth = mGameboard.getWidth();
                int parentHeight = mGameboard.getHeight();
                int width = parentWidth / NUM_COLS;
                int height = parentHeight / NUM_ROWS;
                Log.i(TAG, "cell width: " + parentWidth);
                Log.i(TAG, "cell height:" + parentHeight);

                for(int x = 0; x < NUM_COLS; x++) {
                    for(int y = 0; y < NUM_ROWS; y++) {
                        GridLayout.LayoutParams params = (GridLayout.LayoutParams)
                                mGridCellsViews[(x * NUM_ROWS) + y].getLayoutParams();
                        params.width = width;
                        params.height = height;
                        mGridCellsViews[(x * NUM_ROWS) + y].setLayoutParams(params);
                    }
                }

                mGameboard.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        //SquareTetronimo squareTetronimo = new SquareTetronimo(mGridCellsViews);
    }
}
