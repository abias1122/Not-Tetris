package com.example.android.tetris.RotationTests;

import com.example.android.tetris.game_entities.Gameboard;
import com.example.android.tetris.game_entities.GeneralHelpers;
import com.example.android.tetris.game_entities.GridCellView;
import com.example.android.tetris.game_entities.Tetronimoes.STetronimo;
import com.example.android.tetris.game_entities.Tetronimoes.Tetronimo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Test rotation of STetronimo
 */

public class STetronimoRotateTest {
    private Gameboard gameboard;
    private STetronimo testTetronimo;

    @Before
    public void before() {
        gameboard = new Gameboard();
        testTetronimo = new STetronimo(gameboard);
    }

    /*0 degree to 90 degree tests*/
    @Test
    public void normalCaseZeroDeg(){
        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 1};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 0}, {2, 1}, {2, 2}, {1, 1}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 1};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalZeroDegRotate1(){
        gameboard.getGridCell(1, 0).setOccupied(true);
        gameboard.getGridCell(1, 1).setOccupied(true);

        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 1};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{3, 3}, {2, 1}, {2, 2}, {3, 2}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {3, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalZeroDegRotate2(){
        gameboard.getGridCell(1, 0).setOccupied(true);

        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 1};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 2}, {1, 1}, {2, 2}, {2, 3}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalZeroDegRotate3(){
        gameboard.getGridCell(1, 0).setOccupied(true);
        gameboard.getGridCell(2, 3).setOccupied(true);
        gameboard.getGridCell(3, 3).setOccupied(true);

        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 1};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{2, 0}, {2, 1}, {3, 2}, {3, 1}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {3, 1};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void noZeroDegRotate(){
        gameboard.getGridCell(1, 0).setOccupied(true);
        gameboard.getGridCell(2, 0).setOccupied(true);
        gameboard.getGridCell(2, 3).setOccupied(true);
        gameboard.getGridCell(3, 3).setOccupied(true);

        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 1};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 1};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    /*90 deg to 180 deg tests*/
    @Test
    public void normalCaseNinetyDeg(){
        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.NINETY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{2, 1}, {1, 2}, {2, 2}, {3, 1}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalNinetyDegRotate1(){
        gameboard.getGridCell(3, 1).setOccupied(true);

        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.NINETY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 1}, {1, 2}, {2, 1}, {0, 2}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {1, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalNinetyDegRotate2(){
        gameboard.getGridCell(3, 1).setOccupied(true);
        gameboard.getGridCell(2, 1).setOccupied(true);

        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.NINETY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 3}, {1, 2}, {2, 2}, {0, 3}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {1, 3};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalNinetyDegRotate3(){
        gameboard.getGridCell(3, 1).setOccupied(true);
        gameboard.getGridCell(0, 2).setOccupied(true);
        gameboard.getGridCell(0, 3).setOccupied(true);

        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.NINETY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 3}, {3, 2}, {2, 2}, {2, 3}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 3};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void noNinetyDegRotate(){
        gameboard.getGridCell(3, 1).setOccupied(true);
        gameboard.getGridCell(0, 2).setOccupied(true);
        gameboard.getGridCell(0, 3).setOccupied(true);
        gameboard.getGridCell(1, 3).setOccupied(true);

        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.NINETY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    /*180 deg to 270 deg tests*/
    @Test
    public void normalCaseOneEightyDeg(){
        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.ONE_EIGHTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{3, 3}, {2, 1}, {2, 2}, {3, 2}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalOneEightyDegRotate1(){
        gameboard.getGridCell(3, 2).setOccupied(true);

        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.ONE_EIGHTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 1}, {2, 1}, {2, 2}, {1, 0}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {1, 1};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalOneEightyDegRotate2(){
        gameboard.getGridCell(1, 0).setOccupied(true);
        gameboard.getGridCell(3, 3).setOccupied(true);

        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.ONE_EIGHTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 2}, {1, 1}, {2, 2}, {2, 3}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {1, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalOneEightyDegRotate3(){
        gameboard.getGridCell(1, 1).setOccupied(true);
        gameboard.getGridCell(3, 3).setOccupied(true);

        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.ONE_EIGHTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{3, 2}, {2, 1}, {2, 0}, {3, 1}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 1};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void noOneEightyDegRotate(){
        gameboard.getGridCell(1, 1).setOccupied(true);
        gameboard.getGridCell(3, 3).setOccupied(true);
        gameboard.getGridCell(2, 0).setOccupied(true);

        int[][] initCoords = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};
        int[] initAxisCoords = {2, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.ONE_EIGHTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 2}, {2, 1}, {2, 2}, {3, 1}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    /*270 deg to 0 deg tests*/
    @Test
    public void normalCaseTwoSeventyDeg(){
        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {1, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.TWO_SEVENTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 3}, {1, 2}, {2, 2}, {0, 3}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {1, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalTwoSeventyDegRotate1(){
        gameboard.getGridCell(0, 3).setOccupied(true);

        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {1, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.TWO_SEVENTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{3, 2}, {1, 3}, {2, 2}, {2, 3}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalTwoSeventyDegRotate2(){
        gameboard.getGridCell(0, 3).setOccupied(true);
        gameboard.getGridCell(1, 3).setOccupied(true);

        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {1, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.TWO_SEVENTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 1}, {1, 2}, {0, 2}, {2, 1}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {1, 1};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }

    @Test
    public void abnormalTwoSeventyDegRotate3(){
        gameboard.getGridCell(0, 3).setOccupied(true);
        gameboard.getGridCell(0, 2).setOccupied(true);
        gameboard.getGridCell(1, 3).setOccupied(true);

        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {1, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.TWO_SEVENTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{2, 1}, {1, 2}, {2, 2}, {3, 1}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {2, 1};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }
    @Test
    public void noTwoSeventyDegRotate(){
        gameboard.getGridCell(0, 3).setOccupied(true);
        gameboard.getGridCell(0, 2).setOccupied(true);
        gameboard.getGridCell(1, 3).setOccupied(true);
        gameboard.getGridCell(3, 1).setOccupied(true);

        int[][] initCoords = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
        int[] initAxisCoords = {1, 2};
        testTetronimo.setComponentCells(initCoords);
        testTetronimo.setAxisCell(initAxisCoords);
        testTetronimo.setCurrentState(Tetronimo.RotState.TWO_SEVENTY_DEG);
        testTetronimo.rotate();

        int[][] expectedCoordsArr = {{1, 1}, {1, 2}, {2, 2}, {2, 3}};

        int[][] actualCoordsArr = new int[4][2];
        for(int i = 0; i < actualCoordsArr.length; i++) {
            GridCellView componentCell = testTetronimo.getComponentCells()[i];
            int[] coords = {componentCell.getXPos(), componentCell.getYPos()};
            actualCoordsArr[i] = coords;
        }

//        GeneralHelpers.printArray(expectedCoordsArr);
//        System.out.println();
//        GeneralHelpers.printArray(actualCoordsArr);
        assertArrayEquals(expectedCoordsArr, actualCoordsArr);

        int[] expectedAxisCoords = {1, 2};
        int[] actualAxisCoords = {testTetronimo.getAxisCell().getXPos(), testTetronimo.getAxisCell().getYPos()};
        assertArrayEquals(expectedAxisCoords, actualAxisCoords);
    }
}
