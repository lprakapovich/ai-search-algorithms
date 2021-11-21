package com.lprakapovich.solver;

import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.util.ArrayUtil;
import com.lprakapovich.util.PrintUtil;

import java.util.ArrayList;
import java.util.Collection;

import static com.lprakapovich.solver.ProcessTimer.*;

public abstract class UninformedGraphTraversalSolver implements PuzzleSolver {

    private boolean isSolved;

    protected final ArrayList<Puzzle> explored;

    public UninformedGraphTraversalSolver() {
        explored = new ArrayList<>();
        isSolved = false;
    }

    abstract Puzzle getFirstCandidate();

    abstract Collection<Puzzle> getCandidates();

    @Override
    public void solve(Puzzle puzzle) {
        getCandidates().add(puzzle);
        startExecutionTimer();
        explore();
    }

    private void explore() {
        while (!getCandidates().isEmpty() || !isSolved) {
            Puzzle candidate = getFirstCandidate();
            if (!isPuzzleExplored(candidate)) {
                explored.add(candidate);
                if (candidate.isGoalBoard()) {
                    isSolved = true;
                    endExecutionTimer();
                    PrintUtil.printResult(candidate, getProcessExecutionTime());
                    break;
                }

                addPossibleSolutionsToQueue(candidate);
            }
        }
    }

    protected void addPossibleSolutionsToQueue(Puzzle candidate) {
        ArrayList<Puzzle> possibleSolutions = new ArrayList<>();
        candidate.initChildren();
        candidate.getChildrenToExplore().forEach(solution -> {
            if (!isPuzzleExplored(solution)) {
                possibleSolutions.add(solution);
            }
        });
        getCandidates().addAll(possibleSolutions);
    }

    private boolean isPuzzleExplored(Puzzle puzzle) {
        if (explored.isEmpty()) {
            return false;
        }
        return explored.stream().anyMatch(exploredPuzzle -> ArrayUtil.arraysAreEqual(exploredPuzzle.getBoard(), puzzle.getBoard()));
    }
}
