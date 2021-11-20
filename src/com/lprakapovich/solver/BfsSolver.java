package com.lprakapovich.solver;

import com.lprakapovich.puzzle.Puzzle;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BfsSolver extends UninformedGraphTraversalSolver {

    private final Queue<Puzzle> candidateQueue = new LinkedList<>();

    @Override
    public Puzzle getFirstCandidate() {
        return candidateQueue.poll();
    }

    @Override
    Collection<Puzzle> getCandidates() {
        return candidateQueue;
    }
}

