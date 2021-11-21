package com.lprakapovich.solver;

import com.lprakapovich.puzzle.Puzzle;
import com.lprakapovich.util.ArrayUtil;
import com.lprakapovich.util.PrintUtil;

import java.util.*;

public class IdfsSolver {

    private boolean isSolved;

    private Puzzle init;
    private int depthLimit;
    private int currentDepth;

    private final ArrayList<Puzzle> explored;
    private final Set<Puzzle> withUnexploredChildren;
    private final Map<Puzzle, Integer> puzzleLevel;

    public IdfsSolver() {
        explored = new ArrayList<>();
        withUnexploredChildren = new HashSet<>();
        puzzleLevel = new HashMap<>();
        isSolved = false;
        depthLimit = 3;
        currentDepth = 1;
    }

    private final Stack<Puzzle> candidateStack = new Stack<>();

    public Puzzle getFirstCandidate() {
        return candidateStack.pop();
    }

    public void solve(Puzzle puzzle) {
        init = new Puzzle(puzzle.getBoardCopy());
        candidateStack.add(puzzle);
        explore();
    }

    private void explore() {
        while (!isSolved) {
            while (!candidateStack.isEmpty()) {

                Puzzle candidate = getFirstCandidate();
                registerCandidateChildren(candidate);

                if (withUnexploredChildren.contains(candidate)) {
                    currentDepth = puzzleLevel.get(candidate);
                }

                if (!isPuzzleExplored(candidate)) {
                    explored.add(candidate);

                    if (candidate.isGoalBoard()) {
                        isSolved = true;
                        PrintUtil.printResult(candidate, 0);
                        break;
                    }

                    if (currentDepth < depthLimit) {
                        withUnexploredChildren.addAll(candidateStack);
                        addPossibleSolutionsToQueue(candidate);
                        currentDepth++;
                    } else if (withUnexploredChildren.contains(candidate)) {
                        addPossibleSolutionsToQueue(candidate);
                        withUnexploredChildren.remove(candidate);
                    }
                }
            }

            depthLimit++;
            currentDepth = 1;
            explored.clear();
            candidateStack.clear();
            withUnexploredChildren.clear();
            solve(init);
        }
    }

    private void registerCandidateChildren(Puzzle candidate) {
        candidate.initChildren();
        puzzleLevel.put(candidate, currentDepth);
        int nextDepth = currentDepth + 1;
        candidate.getChildrenToExplore().forEach(child -> {
            puzzleLevel.put(child, nextDepth);
        });
    }

    protected void addPossibleSolutionsToQueue(Puzzle candidate) {
        ArrayList<Puzzle> possibleSolutions = new ArrayList<>();
//        candidate.initChildren();
        candidate.getChildrenToExplore().forEach(solution -> {
            if (!isPuzzleExplored(solution)) {
                possibleSolutions.add(solution);
            }
        });
        candidateStack.addAll(possibleSolutions);
    }

    private boolean isPuzzleExplored(Puzzle puzzle) {
        if (explored.isEmpty()) {
            return false;
        }
        return explored.stream().anyMatch(exploredPuzzle -> ArrayUtil.arraysAreEqual(exploredPuzzle.getBoard(), puzzle.getBoard()));
    }
}
