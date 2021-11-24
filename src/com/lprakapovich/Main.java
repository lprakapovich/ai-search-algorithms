package com.lprakapovich;

import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.solver.BestFirstSearchSolver;

public class Main {

    public static void main(String[] args) {
        int[][] initBoard = {
                {4, 1, 2},
                {5, 8, 3},
                {7, 0, 6}
        };

//        System.out.println("\nIDDFS FIRST SEARCH");
//        Puzzle puzzle3 = new Puzzle(initBoard);
//        DfsSolver solver3 = new DfsSolver();
//        solver3.solve(puzzle3);
//
//        System.out.println("\nIBFS FIRST SEARCH");
//        Puzzle puzzle4 = new Puzzle(initBoard);
//        BfsSolver solver4 = new BfsSolver();
//        solver4.solve(puzzle4);

//        Puzzle p5 = new Puzzle(initBoard);
//        IdfsSolver s5 = new IdfsSolver();
//        s5.solve(p5);
//

//        System.out.println("\nITERATIVE DEEPENING SEARCH");
//        IdsSolver solver = new IdsSolver();
//        solver.solve(new Puzzle(initBoard));

        System.out.println("\n HEURISTIC GREEDY BEST FIRST SEARCH");
        BestFirstSearchSolver d = new BestFirstSearchSolver();
        Puzzle p6 = new Puzzle(initBoard);
        d.solve(p6);
    }
}
