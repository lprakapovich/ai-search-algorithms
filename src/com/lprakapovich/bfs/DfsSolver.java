package com.lprakapovich.bfs;

import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.util.PrintUtil;

import java.util.LinkedList;
import java.util.Stack;

public class DfsSolver extends ProcessTimer implements PuzzleSolver {

    Stack<Puzzle> candidateQueue = new Stack<>();
    LinkedList<Puzzle> visitedCandidates = new LinkedList<>();
    Puzzle originalPuzzle;

    private boolean isGoalFound = false;

    public void solve(Puzzle puzzle) {
        originalPuzzle = new Puzzle(puzzle.getBoardCopy());
        candidateQueue.add(puzzle);
        startExecutionTimer();
        exploreNthLevel(puzzle);
    }

    private void exploreNthLevel(Puzzle puzzle) {

        while (!isGoalFound) {

            if (!isAlreadySeen(puzzle)) {

                for (Puzzle candidate : candidateQueue) {
                    visitedCandidates.add(candidate);
                    if (candidate.isGoalBoard()) {
                        isGoalFound = true;
                        endExecutionTimer();
                        PrintUtil.printResult(candidate, getProcessExecutionTime());
                        break;
                    }
                }

                LinkedList<Puzzle> children = new LinkedList<>();
                if (!isGoalFound) {
                    candidateQueue.forEach(candidate -> {
                        candidate.initChildren();
                        candidate.getAllValidChildren().forEach(child -> {
                            if (!isAlreadySeen(child)) {
                                children.add(child);
                            }
                        });
                    });

                    candidateQueue.addAll(children);
                }
            }

            Puzzle firstCandidate = candidateQueue.pop();
            exploreNthLevel(firstCandidate);
        }
    }

    // TODO refactor
    private boolean isAlreadySeen(Puzzle puzzle) {

        int[][] board = puzzle.getBoard();
        boolean isPresentInSeen = true;

        if (puzzle == originalPuzzle) {
            return true;
        }

        if (visitedCandidates.isEmpty()) {
            return false;
        }

        for (Puzzle seenPuzzle : visitedCandidates) {
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
                if (board[i][j] != originalPuzzle.getBoard()[i][j]) {
                    isPresentInSeen = false;
                }
            }
        }

        return isPresentInSeen;
    }
}
