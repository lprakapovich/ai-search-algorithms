package com.lprakapovich.puzzle;

public class GoalBoard {

    private static final int[][] goalBoard = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    public static int[][] getGoalBoard() {
        return goalBoard;
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
}
