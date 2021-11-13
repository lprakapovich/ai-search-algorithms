package com.lprakapovich.puzzle;

import java.util.ArrayList;


public class Puzzle {

    private final int width = 3;
    private final int[][] board;
    private ArrayList<Move> moves = new ArrayList<>();

    public Puzzle upChild;
    public Puzzle downChild;
    public Puzzle leftChild;
    public Puzzle rightChild;

    public int[][] getBoard() {
        int[][] copy = new int[width][width];
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    public ArrayList<Move> getMoves() {
        ArrayList<Move> copy = new ArrayList<>();
        copy.addAll(moves);
        return copy;
    }

    public Puzzle(int[][] board) {
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        this.moves = new ArrayList<>();
    }

    public Puzzle(int[][] board, ArrayList<Move> moves) {
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = board[i][j];
            }
        }

        this.moves.addAll(moves);
    }

    public void registerMove(Move move) {
        this.moves.add(move);
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
        int x = movableTilePosition.x;
        int y = movableTilePosition.y;

        int value = this.board[x][y + 1];
        this.board[x][y] = value;
        this.board[x][y + 1] = 0;
        this.registerMove(Move.RIGHT);
    }

    public void moveLeft() {
        Position movableTilePosition = getMovableTilePosition();
        int x = movableTilePosition.x;
        int y = movableTilePosition.y;

        int value = this.board[x][y - 1];
        this.board[x][y] = value;
        this.board[x][y - 1] = 0;
        this.registerMove(Move.LEFT);
    }

    public void moveUp() {
        Position movableTilePosition = getMovableTilePosition();
        int x = movableTilePosition.x;
        int y = movableTilePosition.y;

        int value = this.board[x - 1][y];
        this.board[x][y] = value;
        this.board[x - 1][y] = 0;
        this.registerMove(Move.UP);
    }

    public void moveDown() {
        Position movableTilePosition = getMovableTilePosition();
        int x = movableTilePosition.x;
        int y = movableTilePosition.y;

        int value = this.board[x + 1][y];
        this.board[x][y] = value;
        this.board[x + 1][y] = 0;
        this.registerMove(Move.DOWN);
    }

    public void printBoard() {
        for (int i = 0; i < width; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
            }
        }
    }

    public boolean isAnyChildGoal() {
        return upChild.isGoalBoard() || downChild.isGoalBoard() || rightChild.isGoalBoard() || leftChild.isGoalBoard();
    }

    public void initChildren() {
        rightChild = new Puzzle(board, moves);
        leftChild = new Puzzle(board, moves);
        upChild = new Puzzle(board, moves);
        downChild = new Puzzle(board, moves);
    }

    public ArrayList<Puzzle> getAllValidChildren() {
        int emptyTileX = getMovableTilePosition().x;
        int emptyTileY = getMovableTilePosition().y;
        ArrayList<Puzzle> children = new ArrayList<>();

        if (emptyTileX > 0) {
            children.add(upChild);
            upChild.moveUp();
        }

        if (emptyTileX < width - 1) {
            children.add(downChild);
            downChild.moveDown();
        }

        if (emptyTileY > 0) {
            children.add(leftChild);
            leftChild.moveLeft();
        }

        if (emptyTileY < width - 1) {
            children.add(rightChild);
            rightChild.moveRight();
        }

        return children;
    }
}

