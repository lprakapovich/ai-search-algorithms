package com.lprakapovich;

import com.lprakapovich.bfs.BfsSolver;
import com.lprakapovich.puzzle.Puzzle;

public class Main {

    public static void main(String[] args) {
        int[][] initBoard = {
                {1, 2, 3},
                {4, 5, 6},
                {0, 7, 8}
        };

        Puzzle puzzle = new Puzzle(initBoard);
        BfsSolver solver = new BfsSolver();
        solver.solve(puzzle);
    }
}
