package com.lprakapovich;

import com.lprakapovich.puzzle.GoalBoard;
import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.solver.BestFirstSearchSolver;
import com.lprakapovich.solver.BreadthFirstSearchSolver;
import com.lprakapovich.solver.DepthFirstSearchSolver;
import com.lprakapovich.solver.IterativeDeepeningSearchSolver;

import java.util.Scanner;

public class Main {

    private static Scanner in;

    public static void main(String[] args) {

        int [][] b15th = {
                {1, 2, 3, 4},
                {5, 10, 6, 8},
                {13, 9, 7, 11},
                {0, 14, 15, 12}
        };

        int [][] b15th2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 0, 14, 15}
        };

        if (args.length == 0) {
            System.out.println("\n-- ERROR ---");
            System.out.println("\nSearching algorithm must be specified");
            System.exit(1);
        }

        in = new Scanner(System.in);
        System.out.println("Enter the width of the board:  ");
        int width = in.nextInt();
        if (width < 3) {
            width = 3;
            System.out.println("Width is set to minimum: 3");
        }

        System.out.println("You entered the width " + width);
        System.out.println("Initializing the board... Done!");
        System.out.println("Goal board: ");

        GoalBoard.createGoalBoard(width);
        int[][] userBoard = getUserInputBoard(width);

        String algorithm = args[0];

        switch (algorithm) {
            case "all" -> compareAll(userBoard);
            case "bfs" -> solveBreadthFirstSearch(userBoard);
            case "dfs" -> solveDepthFirstSearch(userBoard);
            case "ids" -> solveIterativeDeepeningSearch(userBoard);
            case "heuristic-bfs" -> solveBestFirstSearch(userBoard);
            default -> System.out.println("Algorithm is unknown");
        }

        in.close();
    }

    private static int[][] getUserInputBoard(int width) {

        int[][] inputUserBoard = new int[width][width];
        System.out.println("\nEnter the numbers to fill the board");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                inputUserBoard[i][j] = in.nextInt();
            }
        }

        System.out.println("\nYour board: ");
        for (int i = 0; i < width; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                System.out.print(inputUserBoard[i][j] + " ");
            }
        }

        return inputUserBoard;
    }

    private static void compareAll(int[][] userBoard) {
        solveBreadthFirstSearch(userBoard);
        solveIterativeDeepeningSearch(userBoard);
        solveBestFirstSearch(userBoard);
        solveDepthFirstSearch(userBoard);
    }

    private static void solveBestFirstSearch(int[][] userBoard) {
        System.out.println("\n\nHEURISTIC GREEDY BEST FIRST SEARCH");
        BestFirstSearchSolver bfsSolver = new BestFirstSearchSolver();
        bfsSolver.solve(new Puzzle(userBoard));
    }

    private static void solveIterativeDeepeningSearch(int[][] userBoard) {
        System.out.println("\n\nITERATIVE DEEPENING SEARCH");
        IterativeDeepeningSearchSolver idsSolver = new IterativeDeepeningSearchSolver();
        idsSolver.solve(new Puzzle(userBoard));
    }

    private static void solveDepthFirstSearch(int[][] userBoard) {
        System.out.println("\n\nDEPTH FIRST SEARCH");
        DepthFirstSearchSolver dfsSolver = new DepthFirstSearchSolver();
        dfsSolver.solve(new Puzzle(userBoard));
    }

    private static void solveBreadthFirstSearch(int[][] userBoard) {
        System.out.println("\n\nBREADTH FIRST SEARCH");
        BreadthFirstSearchSolver bfsSolver = new BreadthFirstSearchSolver();
        bfsSolver.solve(new Puzzle(userBoard));
    }
}
