package com.lprakapovich.solver;

import com.lprakapovich.puzzle.GoalBoard;
import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.util.ArrayUtil;
import com.lprakapovich.util.PrintUtil;

import java.util.*;
import java.util.stream.Collectors;

import static com.lprakapovich.solver.ProcessTimer.*;

public class BestFirstSearchSolver {

    private List<Puzzle> candidates;
    private boolean solutionFound;

    private final List<Puzzle> explored;
    private final Map<Puzzle, Integer> puzzleHeuristicMap;
    private final Comparator<Puzzle> puzzleComparator;

    public BestFirstSearchSolver() {
        candidates = new ArrayList<>();
        explored = new ArrayList<>();
        puzzleHeuristicMap = new HashMap<>();
        puzzleComparator = (p1, p2) -> {
            Integer o1Heuristic = puzzleHeuristicMap.get(p1);
            Integer o2Heuristic = puzzleHeuristicMap.get(p2);
            return o1Heuristic > o2Heuristic ? 1
                    : o1Heuristic.equals(o2Heuristic) ? 0 : -1;
        };
    }

    public void solve(Puzzle init) {
        startExecutionTimer();
        candidates.add(init);
        startHeuristicSearch();

        if (!solutionFound) {
            PrintUtil.printFailure();
        }
    }

    private void startHeuristicSearch() {
        while (!candidates.isEmpty() || !solutionFound) {
            Puzzle candidate = candidates.get(0);
            if (!isPuzzleExplored(candidate)) {
                explored.add(candidate);
                if (candidate.isGoalBoard()) {
                    endExecutionTimer();
                    PrintUtil.printResult(candidate, getProcessExecutionTime());
                    solutionFound = true;
                    break;
                }

                candidates.remove(candidate);
                addPossibleSolutionsToQueue(candidate);
                startHeuristicSearch();
            }
        }
    }

    private void addPossibleSolutionsToQueue(Puzzle candidate) {
        ArrayList<Puzzle> possibleSolutions = new ArrayList<>();
        candidate.initChildren();
        candidate.getChildrenToExplore().forEach(solution -> {
            if (!isPuzzleExplored(solution)) {
                puzzleHeuristicMap.put(solution, GoalBoard.getHeuristic(solution.getBoard()));
                possibleSolutions.add(solution);
            }
        });
        candidates.addAll(possibleSolutions);
        candidates = candidates.stream().sorted(puzzleComparator).collect(Collectors.toList());
    }

    private boolean isPuzzleExplored(Puzzle puzzle) {
        return explored.stream().anyMatch(exploredPuzzle ->
                ArrayUtil.arraysAreEqual(exploredPuzzle.getBoard(), puzzle.getBoard()));
    }
}
