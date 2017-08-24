package com.example.android.tetris.game_entities.TetronimoDispenser;

import com.example.android.tetris.game_entities.GridCellView;
import com.example.android.tetris.game_entities.Tetronimoes.LTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.ReverseLTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.STetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.SquareTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.StraightTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.TTetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.Tetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.ZTetronimo;

import java.util.Random;

import static com.example.android.tetris.game_entities.TetronimoDispenser.TetronimoDispenserr.TetronimoType.SQUARE;

/**
 * Spawns tetronimos for players to manipulate. Also
 * populates a ListView for upcoming tetronimoes
 */

public class TetronimoDispenserr {

    private TetronimoLinkedList mTetronimoList;
    private GridCellView[] mGameGridCells;

    private enum TetronimoType {
        SQUARE(0), T(1), STRAIGHT(2),
        Z(3), S(4), L(5), REVERSE_L(6);

        final int val;

        private TetronimoType(int val) {

            this.val = val;
        }

        public static TetronimoType getTetronimoType (int val) {
            for(TetronimoType t : TetronimoType.values()) {
                if(t.val == val) {
                    return t;
                }
            }
            throw new IllegalArgumentException("Must use int between 0 and 6");
        }

        public int getVal() {

            return val;
        }
    }

    public TetronimoDispenserr() {

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

            default : {throw new UnsupportedOperationException("Tetronimo type not recognized");}
        }
    }
}
