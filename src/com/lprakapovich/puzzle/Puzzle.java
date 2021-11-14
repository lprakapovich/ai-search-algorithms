package com.lprakapovich.puzzle;

import com.lprakapovich.util.ArrayUtil;

import java.util.ArrayList;

public class Puzzle {

    private final int width = PuzzleConfig.DEFAULT_SIZE;
    private final int[][] board;
    private ArrayList<Step> solutionSteps = new ArrayList<>();

    public Puzzle upMoveChild;
    public Puzzle downMoveChild;
    public Puzzle leftMoveChild;
    public Puzzle rightMoveChild;

    public Puzzle(int[][] board) {
        this.board = ArrayUtil.getArrayCopy(board);
        this.solutionSteps = new ArrayList<>();
    }

    public Puzzle(int[][] board, ArrayList<Step> steps) {
        this.board = ArrayUtil.getArrayCopy(board);
        this.solutionSteps.addAll(steps);
    }

    public int[][] getBoard() {
        return ArrayUtil.getArrayCopy(board);
    }

    public int[][] getBoardCopy() {
        return ArrayUtil.getArrayCopy(board);
    }

    public ArrayList<Step> getSolutionSteps() {
        return solutionSteps;
    }

    public ArrayList<Step> getSolutionStepsCopy() {
        return new ArrayList<>(solutionSteps);
    }

    public void registerStep(Step step) {
        this.solutionSteps.add(step);
    }

    public boolean isGoalBoard() {
        int[][] goalBoard = GoalBoard.getGoalBoard();
        boolean isEqual = true;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (goalBoard[i][j] != board[i][j]) {
                    isEqual = false;
                    break;
                }
            }
        }

        return isEqual;
    }

    public int getWidth() { return this.width; }

    public boolean isMovableTile(int row, int column) {
        return board[row][column] == 0;
    }

    public Position getMovableTilePosition() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (isMovableTile(i, j)) {
                    return new Position(i, j);
                }
            }
        }
        return new Position(-1, -1);
    }

    public void moveRight() {
        Position movableTilePosition = getMovableTilePosition();
        int x = movableTilePosition.getX();
        int y = movableTilePosition.getY();

        int value = this.board[x][y + 1];
        this.board[x][y] = value;
        this.board[x][y + 1] = 0;
        this.registerStep(Step.RIGHT);
    }

    public void moveLeft() {
        Position movableTilePosition = getMovableTilePosition();
        int x = movableTilePosition.getX();
        int y = movableTilePosition.getY();

        int value = this.board[x][y - 1];
        this.board[x][y] = value;
        this.board[x][y - 1] = 0;
        this.registerStep(Step.LEFT);
    }

    public void moveUp() {
        Position movableTilePosition = getMovableTilePosition();
        int x = movableTilePosition.getX();
        int y = movableTilePosition.getY();

        int value = this.board[x - 1][y];
        this.board[x][y] = value;
        this.board[x - 1][y] = 0;
        this.registerStep(Step.UP);
    }

    public void moveDown() {
        Position movableTilePosition = getMovableTilePosition();
        int x = movableTilePosition.getX();
        int y = movableTilePosition.getY();

        int value = this.board[x + 1][y];
        this.board[x][y] = value;
        this.board[x + 1][y] = 0;
        this.registerStep(Step.DOWN);
    }

    // TODO refactor to use java-8 streams
    public void initChildren() {
        rightMoveChild = new Puzzle(board, solutionSteps);
        leftMoveChild = new Puzzle(board, solutionSteps);
        upMoveChild = new Puzzle(board, solutionSteps);
        downMoveChild = new Puzzle(board, solutionSteps);
    }

    public ArrayList<Puzzle> getAllValidChildren() {
        int emptyTileX = getMovableTilePosition().getX();
        int emptyTileY = getMovableTilePosition().getY();
        ArrayList<Puzzle> children = new ArrayList<>();

        if (emptyTileX > 0) {
            children.add(upMoveChild);
            upMoveChild.moveUp();
        }

        if (emptyTileX < width - 1) {
            children.add(downMoveChild);
            downMoveChild.moveDown();
        }

        if (emptyTileY > 0) {
            children.add(leftMoveChild);
            leftMoveChild.moveLeft();
        }

        if (emptyTileY < width - 1) {
            children.add(rightMoveChild);
            rightMoveChild.moveRight();
        }

        return children;
    }
}

