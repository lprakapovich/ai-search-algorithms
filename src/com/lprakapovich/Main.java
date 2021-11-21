package com.lprakapovich;

import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.solver.IdsSolver;

public class Main {

    public static void main(String[] args) {
        int[][] initBoard = {
                {1, 3, 6},
                {5, 0, 2},
                {4, 7, 8}
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

        System.out.println("\nITERATIVE DEEPENING SEARCH");
        IdsSolver solver = new IdsSolver();
        solver.solve(new Puzzle(initBoard));
    }
}
