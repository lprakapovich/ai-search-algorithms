package com.lprakapovich;

import com.lprakapovich.solver.BfsSolver;
import com.lprakapovich.solver.DfsSolver;
import com.lprakapovich.puzzle.Puzzle;

public class Main {

    public static void main(String[] args) {
        int[][] initBoard = {
                {0, 2, 3},
                {1, 4, 5},
                {7, 8, 6}
        };

        System.out.println("\nDEPTH FIRST SEARCH");
        Puzzle puzzle = new Puzzle(initBoard);
        DfsSolver solver = new DfsSolver();
        solver.solve(puzzle);

        System.out.println("\nBREADTH FIRST SEARCH");
        Puzzle puzzle2 = new Puzzle(initBoard);
        BfsSolver solver2 = new BfsSolver();
        solver2.solve(puzzle2);
    }
}
