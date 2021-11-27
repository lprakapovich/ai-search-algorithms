package com.lprakapovich.util;

import com.lprakapovich.puzzle.Position;

import java.util.Arrays;
import java.util.function.BiFunction;

import static java.lang.Math.abs;

public class ArrayUtil {

    public static int[][] getArrayCopy(int[][] array) {
        return Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
    }

    public static boolean arraysAreEqual(int[][] ar1, int[][] ar2) {
        for (int i = 0; i < ar1.length; i++) {
            for (int j = 0; j < ar1.length; j++) {
                if (ar1[i][j] != ar2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int calculateManhattanDistance(int[][] board, int[][] goalBoard) {
        int manhattanDistance = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (goalBoard[i][j] != board[i][j] && board[i][j] != 0) {
                    Position position = findPositionInArray(board[i][j], goalBoard);
                    int rowDiff = abs(position.getX() - i);
                    int colDiff = abs(position.getY() - j);
                    manhattanDistance += rowDiff + colDiff;
                }
            }
        }

        return manhattanDistance;
    }

    public static Position findPositionInArray(int value, int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] == value) {
                    return new Position(i, j);
                }
            }
        }
        return new Position(-1, -1);
    }

    public static Position findInArray(BiFunction<Integer, Integer, Boolean> valueTester, int[][]array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (valueTester.apply(i, j)) {
                    return new Position(i, j);
                }
            }
        }
        return new Position(-1, -1);
    }
}
