package com.lprakapovich.solver;

import com.lprakapovich.puzzle.Puzzle;

import java.util.Collection;
import java.util.Stack;

public class DfsSolver extends GraphTraversalSolver {

    Stack<Puzzle> candidateStack = new Stack<>();

    @Override
    Puzzle getCandidateToExplore() {
        return candidateStack.pop();
    }

    @Override
    Collection<Puzzle> getCandidates() {
        return candidateStack;
    }
}
