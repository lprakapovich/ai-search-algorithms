package com.lprakapovich.solver;

import com.lprakapovich.puzzle.Puzzle;

import java.util.Collection;
import java.util.Stack;

public class DepthFirstSearchSolver extends UninformedGraphTraversalSolver {

    private final Stack<Puzzle> candidateStack = new Stack<>();

    @Override
    public Puzzle getFirstCandidate() {
        return candidateStack.pop();
    }

    @Override
    Collection<Puzzle> getCandidates() {
        return candidateStack;
    }
}
