package com.lprakapovich.util;

import com.lprakapovich.puzzle.Puzzle;

public class PrintUtil {
    public static void printResult(Puzzle solution, long processExecutionTime) {

        System.out.println("\n ---- Solution Steps ---- \n");
        solution.getSolutionSteps().forEach(step -> System.out.println(step.getDescription()));

        System.out.println("\n ---- Execution time ---- \n");
        System.out.println(processExecutionTime + " millis\n\n");
    }
}
