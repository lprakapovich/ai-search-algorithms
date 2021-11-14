package com.lprakapovich.bfs;

import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.util.PrintUtil;

import java.util.Collection;
import java.util.LinkedList;

public abstract class GraphTraversalSolver extends ProcessTimer implements PuzzleSolver {

    private Puzzle initialPuzzle;
    private boolean isSolved;
    private final LinkedList<Puzzle> exploredCandidates;

    public GraphTraversalSolver() {
        exploredCandidates = new LinkedList<>();
        isSolved = false;
    }

    abstract Puzzle getCandidateToExplore();

    abstract Collection<Puzzle> getCandidates();

    public void solve(Puzzle puzzle) {
        initialPuzzle = new Puzzle(puzzle.getBoardCopy());
        getCandidates().add(puzzle);
        startExecutionTimer();
        explore(puzzle);
    }

    private void explore(Puzzle puzzle) {

        while (!isSolved) {

            if (!isAlreadyExplored(puzzle)) {

                for (Puzzle candidate : getCandidates()) {
                    exploredCandidates.add(candidate);
                    if (candidate.isGoalBoard()) {
                        isSolved = true;
                        endExecutionTimer();
                        PrintUtil.printResult(candidate, getProcessExecutionTime());
                        break;
                    }
                }

                LinkedList<Puzzle> children = new LinkedList<>();
                if (!isSolved) {
                    getCandidates().forEach(candidate -> {
                        candidate.initChildren();
                        candidate.getChildrenToExplore().forEach(child -> {
                            if (!isAlreadyExplored(child)) {
                                children.add(child);
                            }
                        });
                    });

                    getCandidates().addAll(children);
                }
            }

            Puzzle nextCandidate = getCandidateToExplore();
            explore(nextCandidate);
        }
    }

    // TODO refactor
    private boolean isAlreadyExplored(Puzzle puzzle) {

        int[][] board = puzzle.getBoard();
        boolean isPresentInSeen = true;

        if (puzzle == initialPuzzle) {
            return true;
        }

        if (exploredCandidates.isEmpty()) {
            return false;
        }

        for (Puzzle seenPuzzle : exploredCandidates) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (seenPuzzle.getBoard()[i][j] != board[i][j]) {
                        isPresentInSeen = false;
                    }
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != initialPuzzle.getBoard()[i][j]) {
                    isPresentInSeen = false;
                }
            }
        }

        return isPresentInSeen;
    }
}
