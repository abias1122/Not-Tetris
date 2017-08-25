package com.example.android.tetris.game_entities.TetronimoDispenser;

import android.util.Log;

import com.example.android.tetris.game_entities.GridCellView;
import com.example.android.tetris.game_entities.Tetronimoes.LTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.ReverseLTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.STetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.SquareTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.StraightTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.TTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.Tetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.ZTetronimo;
import com.example.android.tetris.game_entities.TetronimoDispenser.TetronimoLinkedList.Node;

import java.util.Random;

/**
 * Spawns tetronimos for players to manipulate. Also
 * populates a ListView for upcoming tetronimoes
 */

public class TetronimoDispenser {

    private TetronimoLinkedList mTetronimoList;
    private GridCellView[] mGameGridCells;

    private final int NUM_OF_TETRONIMOES = 4;

    private enum TetronimoType {
        SQUARE(0), T(1), STRAIGHT(2),
        Z(3), S(4), L(5), REVERSE_L(6);

        final int val;

        private TetronimoType(int val) {

            this.val = val;
        }

        public static TetronimoType getTetronimoType (int val) {
            for(TetronimoType t : TetronimoType.values()) {
                if(t.getVal() == val) {
                    return t;
                }
            }
            throw new IllegalArgumentException("Must use int between 0 and 6");
        }

        public int getVal() {

            return val;
        }
    }

    public TetronimoDispenser(GridCellView[] gameGridCells) {

        mGameGridCells = gameGridCells;
        mTetronimoList = new TetronimoLinkedList();

        for(int i = 0; i < NUM_OF_TETRONIMOES; i++) {

            Tetronimo tetronimo = generateRandomTetronimo();
            mTetronimoList.insertNode(tetronimo);
        }

        Node printNode = mTetronimoList.getHead();
        for(int i = 0; i < NUM_OF_TETRONIMOES; i++) {
            Tetronimo printTetron = printNode.getData();
            Log.i("Initialize list", printTetron.toString());
        }
    }

    private Tetronimo generateRandomTetronimo() {

        Random tetronimoGenerator = new Random();
        int varietiesOfTetronimo = TetronimoType.values().length;
        int typeNumber = tetronimoGenerator.nextInt(varietiesOfTetronimo);
        TetronimoType type = TetronimoType.getTetronimoType(typeNumber);

        switch(type) {
            case SQUARE : {return new SquareTetronimo(mGameGridCells);}

            case T : {return new TTetronimo(mGameGridCells);}

            case STRAIGHT : {return new StraightTetronimo(mGameGridCells);}

            case Z : {return new ZTetronimo(mGameGridCells);}

            case S : {return new STetronimo(mGameGridCells);}

            case L : {return new LTetronimo(mGameGridCells);}

            case REVERSE_L : {return new ReverseLTetronimo(mGameGridCells);}

            default : {
                throw new UnsupportedOperationException("Tetronimo type not recognized");}
        }
    }

    public Tetronimo dispense() {

        Node oldHead = mTetronimoList.getHead();
        Tetronimo data = generateRandomTetronimo();
        oldHead.setData(data);

        Node newHead = mTetronimoList.getNextHead();
        return newHead.getData();
    }
}
