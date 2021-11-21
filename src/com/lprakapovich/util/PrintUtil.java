package com.lprakapovich.util;

import com.lprakapovich.puzzle.Puzzle;

public class PrintUtil {
    public static void printResult(Puzzle solution, long processExecutionTime) {

        System.out.println("\n ---- Solution Steps ---- \n");
        solution.getSolutionSteps().forEach(step -> System.out.println(step.getDescription()));

        System.out.println("\n ---- Execution time ---- \n");
        System.out.println(processExecutionTime + " millis\n\n");
    }

    public static void printResult(Puzzle solution, long processExecutionTime, int depth) {
        printResult(solution, processExecutionTime);

        System.out.println("---- Depth ---- \n");
        System.out.println(depth);
    }

//    public static void printBoard(Puzzle puzzle) {
//        for (int i = 0; i < puzzle.getWidth(); i++) {
//            System.out.println();
//            for (int j = 0; j < puzzle.getWidth(); j++) {
//                System.out.print(puzzle.getBoard()[i][j]);
//            }
//        }
//        System.out.println("\n------------------------------\n");
//
//        System.out.println("\n ---- Solution Steps ---- \n");
//        puzzle.getSolutionSteps().forEach(step -> System.out.println(step.getDescription()));
//    }
}
