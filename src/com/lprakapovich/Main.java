package com.lprakapovich;

import com.lprakapovich.bfs.BfsSolver;
import com.lprakapovich.puzzle.Puzzle;

public class Main {

    public static void main(String[] args) {
        int[][] initBoard = {
                {2, 3, 0},
                {1, 5, 6},
                {4, 7, 8}
        };
        Puzzle puzzle = new Puzzle(initBoard);
        BfsSolver solver = new BfsSolver();
        solver.solve(puzzle);
    }
}
