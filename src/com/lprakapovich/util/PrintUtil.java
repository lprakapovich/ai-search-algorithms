package com.lprakapovich.util;

import com.lprakapovich.puzzle.Puzzle;

public class PrintUtil {
    public static void printResult(Puzzle solution) {

        System.out.println("\n ---- Found Solution ---- ");

        for (int i = 0; i < solution.getWidth(); i++) {
            System.out.println();
            for (int j = 0; j < solution.getWidth(); j++) {
                System.out.print(solution.getBoard()[i][j]);
            }
        }

        System.out.println("\n\n ---- Solution Steps ---- \n");

        solution.getSolutionSteps().forEach(step -> System.out.println(step.getDescription()));
    }
}
