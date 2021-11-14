package com.lprakapovich.puzzle;

public enum Step {
    UP("Up"),
    DOWN("Down"),
    RIGHT("Right"),
    LEFT("Left");

    private final String description;

    Step(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
