package com.lprakapovich.bfs;

import com.lprakapovich.puzzle.Position;
import com.lprakapovich.puzzle.Puzzle;

import java.util.LinkedList;

public class BfsSolver implements PuzzleSolver {

    LinkedList<Puzzle> candidateQueue = new LinkedList<>();
    LinkedList<Puzzle> seen = new LinkedList<>();
    Puzzle originalPuzzle;

    private boolean isGoalFound = false;

    public void solve(Puzzle puzzle) {
        originalPuzzle = new Puzzle(puzzle.getBoard());
        candidateQueue.add(puzzle);
        exploreNthLevel(puzzle);
    }

    private void exploreNthLevel(Puzzle puzzle) {

        while (!isGoalFound) {

            if (!isAlreadySeen(puzzle)) {

                for (Puzzle candidate : candidateQueue) {
                    seen.add(candidate);
                    if (candidate.isGoalBoard()) {
                        isGoalFound = true;
                        System.out.println("found solution");
                        candidate.printBoard();
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

            Puzzle firstCandidate = candidateQueue.pollFirst();
            exploreNthLevel(firstCandidate);
        }
    }

    private boolean isAlreadySeen(Puzzle puzzle) {
        int[][] board = puzzle.getBoard();
        boolean isPresentInSeen = true;

        if (puzzle == originalPuzzle) {
            return true;
        }

        if (seen.isEmpty()) {
            return false;
        }

        for (Puzzle seenPuzzle: seen) {
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
