package com.example.android.tetris.game_entities;

/**
 * General purpose functions
 */

public class GeneralHelpers {
    public static void printArray(int[][] arr) {
        for (int[] array : arr) {
            System.out.printf("{%d, %d}\n", array[0], array[1]);
        }
    }
}
