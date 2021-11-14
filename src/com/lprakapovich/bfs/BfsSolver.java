package com.lprakapovich.bfs;

import com.lprakapovich.puzzle.Puzzle;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class BfsSolver extends GraphTraversalSolver {

    Queue<Puzzle> candidateQueue = new LinkedList<>();

    @Override
    Puzzle getCandidateToExplore() {
        return candidateQueue.poll();
    }

    @Override
    Collection<Puzzle> getCandidates() {
        return candidateQueue;
    }
}
