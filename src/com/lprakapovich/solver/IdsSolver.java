package com.lprakapovich.solver;

import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.util.PrintUtil;

import static com.lprakapovich.solver.ProcessTimer.*;

public class IdsSolver {

    private final static int MINIMAL_DEPTH = 0;

    public void solve(Puzzle init) {
        int depth = MINIMAL_DEPTH;
        while (!iterativeDeepeningSearch(init, depth)) {
            iterativeDeepeningSearch(init, depth++);
        }
    }

    public boolean iterativeDeepeningSearch(Puzzle candidate, int maxDepth) {
        startExecutionTimer();
        for (int depth = 0; depth < maxDepth; ++depth) {
            if (depthLimitedSearch(candidate, depth)) {
                endExecutionTimer();
                PrintUtil.printResult(candidate, getProcessExecutionTime(), depth + 1);
                return true;
            }
        }
        return false;
    }

    private boolean depthLimitedSearch(Puzzle candidate, int depth) {
        if (candidate.isGoalBoard()) {
            return true;
        }

        if (depth == 0) {
            return false;
        }

        candidate.initChildren();
        for (Puzzle child : candidate.getChildrenToExplore()) {
            if (depthLimitedSearch(child, depth - 1)) {
                endExecutionTimer();
                candidate.getSolutionSteps().clear();
                candidate.setSolutionSteps(child.getSolutionSteps());
                return true;
            }
        }
        return false;
    }
}

