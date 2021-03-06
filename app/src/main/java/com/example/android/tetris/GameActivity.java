package com.example.android.tetris;

import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;

import com.example.android.tetris.game_entities.Gameboard;
import com.example.android.tetris.game_entities.GridCellView;
import com.example.android.tetris.game_entities.TetronimoDispenser.TetronimoDispenser;
import com.example.android.tetris.game_entities.Tetronimoes.LTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.ReverseLTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.STetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.StraightTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.TTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.Tetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.ZTetronimo;

public class GameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private final String TAG = "GameActivity";
    private final String DEBUG_TAG = "Gesture detector";
    private GridLayout mGameGridLayout;
    private Gameboard mGameboard;
    private Handler mMoveDownHandler;
    private GestureDetectorCompat mGestureDetector;

    private TetronimoDispenser mDispenser;
    private Tetronimo tetronimoInPlay;

    private float mInitY;
    private float mInitX;
    private float mMovementThreshold;

    private final int DEFAULT_DELAY = 1000;
    private final int FAST_DELAY = 250;
    private int delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameGridLayout = (GridLayout) findViewById(R.id.gameboard);
        mGameboard = new Gameboard(this, mGameGridLayout);
        mGameGridLayout.setColumnCount(mGameboard.NUM_COLS);
        mGameGridLayout.setRowCount(mGameboard.NUM_ROWS);
        mGestureDetector = new GestureDetectorCompat(this, this);

        delay = DEFAULT_DELAY;

        mDispenser = new TetronimoDispenser(mGameboard);

        mGameGridLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int parentWidth = mGameGridLayout.getWidth();
                int parentHeight = mGameGridLayout.getHeight();
                int width = parentWidth / mGameboard.NUM_COLS;
                int height = parentHeight / mGameboard.NUM_ROWS;

                mMovementThreshold = width / 2;

                Log.i(TAG, "cell width: " + width);
                Log.i(TAG, "cell height:" + height);

                for(int x = 0; x < mGameboard.NUM_COLS; x++) {
                    for(int y = 0; y < mGameboard.NUM_ROWS; y++) {
                        GridLayout.LayoutParams params = (GridLayout.LayoutParams)
                               mGameboard.getGridCell(x, y).getLayoutParams();
                        params.width = width;
                        params.height = height;
                        mGameboard.getGridCell(x, y).setLayoutParams(params);
                    }
                }

                mGameGridLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        tetronimoInPlay = mDispenser.dispense();
        tetronimoInPlay.putInGame();

        mMoveDownHandler = new Handler();
        mMoveDownHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean canMove = tetronimoInPlay.moveDown();
                if(!canMove) {
                    tetronimoInPlay = null;
                    tetronimoInPlay = mDispenser.dispense();
                    tetronimoInPlay.putInGame();
                }
                mMoveDownHandler.postDelayed(this, delay);
            }
        }, delay);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        final int action = event.getActionMasked();
        switch(action) {
            case MotionEvent.ACTION_DOWN: {
                mInitX = event.getX();
                mInitY = event.getY();
                Log.d(DEBUG_TAG, "onDown: " + event.toString());
                return true;
            }

            case MotionEvent.ACTION_MOVE: {
                float x = event.getX();
                float y = event.getY();

                //Move left one column
                if((mInitX - mMovementThreshold) > x) {
                    Log.d(DEBUG_TAG, "move left: " + event.toString());
                    mInitX = x;
                    mInitY = y;

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            tetronimoInPlay.moveLeft();
                        }
                    });
                }
                //Move right one column
                else if ((mInitX + mMovementThreshold) < x) {
                    Log.d(DEBUG_TAG, "move right: " + event.toString());
                    mInitX = x;
                    mInitY = y;

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            tetronimoInPlay.moveRight();
                        }
                    });
                }
                //Moving up to reset fall speed if previously sped up
                else if((mInitY - mMovementThreshold) > y) {
                    if(delay == FAST_DELAY) {
                        delay = DEFAULT_DELAY;
                    }
                    Log.d(DEBUG_TAG, "move up: " + event.toString());
                    mInitX = x;
                    mInitY = y;
                }
                else if((mInitY + mMovementThreshold) < y) {
                    if(delay == DEFAULT_DELAY) {
                        delay = FAST_DELAY;
                    }
                    Log.d(DEBUG_TAG, "move down: " + event.toString());
                    mInitX = x;
                    mInitY = y;
                }
                return true;
            }

            case MotionEvent.ACTION_UP: {
                if(delay == FAST_DELAY) {
                    delay = DEFAULT_DELAY;
                }
                Log.d(DEBUG_TAG, "onUp: " + event.toString());
                return true;
            }

            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        //unused stub
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                tetronimoInPlay.rotate();
            }
        });
        Log.d(DEBUG_TAG, "rotate");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //unused stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        ///unused stub
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float vX, float vY) {
        final int velocityThreshold = 200;
        final float xThreshold = 200;
        final float xMovement = Math.abs(event1.getX() - event2.getX());

        if(vY > velocityThreshold && xMovement < xThreshold) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    tetronimoInPlay.moveToBottom();
                    tetronimoInPlay = null;
                    tetronimoInPlay = mDispenser.dispense();
                    tetronimoInPlay.putInGame();
                }
            });
            Log.d(DEBUG_TAG, "SWIPE DOWN!");
        }
        return false;
    }
}
