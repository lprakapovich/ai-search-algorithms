package com.lprakapovich.puzzle;

import com.lprakapovich.util.PrintUtil;

import java.util.Arrays;

public class GoalBoard {

    private static int width;

    private static int[][] goalBoard;

//    = {
//            {1, 2, 3},
//            {4, 5, 6},
//            {7, 8, 0}
//    };

    public static int[][] getGoalBoard() {
        return goalBoard;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeuristic(int[][] boardToCompare) {
        int heuristic = 0;
        for (int i = 0; i < goalBoard.length; i++) {
            for (int j = 0; j < goalBoard.length; j++) {
                if (boardToCompare[i][j] != 0 && boardToCompare[i][j] != goalBoard[i][j]) {
                    heuristic++;
                }
            }
        }

        return heuristic;
    }

    public static void createGoalBoard(int w) {
        width = w;
        goalBoard = new int[w][w];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < w; j++) {
                goalBoard[i][j] = (w * i) + (j % w) + 1;
            }
        }

        goalBoard[w - 1][w - 1] = 0;
        for (int i = 0; i < w; i++) {
            System.out.println();
            for (int j = 0; j < w; j++) {
                System.out.print(goalBoard[i][j] + " ");
            }
        }

        System.out.println();
    }

    public static boolean boardIsSolvable(int[][] board) {
        return Arrays.stream(goalBoard).allMatch(value -> Arrays.asList(board).contains(value));
    }
}
