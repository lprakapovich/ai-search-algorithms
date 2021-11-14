package com.lprakapovich;

import com.lprakapovich.bfs.BfsSolver;
import com.lprakapovich.bfs.DfsSolver;
import com.lprakapovich.puzzle.Puzzle;

public class Main {

    public static void main(String[] args) {
        int[][] initBoard = {
                {1, 2, 3},
                {5, 0, 6},
                {4, 7, 8}
        };

        System.out.println("DFS");
        Puzzle puzzle = new Puzzle(initBoard);
        DfsSolver solver = new DfsSolver();
        solver.solve(puzzle);

        System.out.println("BFS");
        Puzzle puzzle2 = new Puzzle(initBoard);
        BfsSolver solver2 = new BfsSolver();
        solver2.solve(puzzle2);
    }
}
