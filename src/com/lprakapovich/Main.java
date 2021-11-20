package com.lprakapovich;

import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.solver.BfsSolver;
import com.lprakapovich.solver.DfsSolver;

public class Main {

    public static void main(String[] args) {
        int[][] initBoard = {
                {1, 2, 3},
                {4, 0, 5},
                {7, 8, 6}
        };

        System.out.println("\nIDDFS FIRST SEARCH");
        Puzzle puzzle3 = new Puzzle(initBoard);
        DfsSolver solver3 = new DfsSolver();
        solver3.solve(puzzle3);

        System.out.println("\nIBFS FIRST SEARCH");
        Puzzle puzzle4 = new Puzzle(initBoard);
        BfsSolver solver4 = new BfsSolver();
        solver4.solve(puzzle4);
    }
}
