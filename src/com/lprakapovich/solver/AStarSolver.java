package com.lprakapovich.solver;

import com.lprakapovich.puzzle.GoalBoard;
import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.util.ArrayUtil;
import com.lprakapovich.util.PrintUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class AStarSolver {

    private final Queue<Puzzle> openList = new LinkedList<>();
    private final ArrayList<Puzzle> explored = new ArrayList<>();
    private final HashMap<Puzzle, Integer> fScoreMap = new HashMap<>();

    private boolean isSolved;

    public void solve(Puzzle init) {
        openList.add(init);
        startHeuristic();
    }

    private void startHeuristic() {
        while (!isSolved) {

            Puzzle firstCandidate = openList.poll();
            if (firstCandidate.isGoalBoard()) {
                isSolved = true;
                PrintUtil.printResult(firstCandidate, 0);
                break;
            }

            explored.add(firstCandidate);
            addPossibleSolutionsToQueue(firstCandidate);
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
        openList.addAll(possibleSolutions);
    }

    private boolean isPuzzleExplored(Puzzle puzzle) {
        return explored.stream().anyMatch(exploredPuzzle ->
                ArrayUtil.arraysAreEqual(exploredPuzzle.getBoard(), puzzle.getBoard()));
    }

    private void determineHeuristic(Puzzle puzzle) {
        int heuristicFScore = puzzle.getDepth() + getManhattanDistance(puzzle.getBoard());
        fScoreMap.put(puzzle, heuristicFScore);
    }

    private int getManhattanDistance(int[][] board) {
        return ArrayUtil.calculateManhattanDistance(board, GoalBoard.getGoalBoard());
    }
}
