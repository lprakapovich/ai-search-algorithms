package com.lprakapovich.solver;

public abstract class ProcessTimer {

    private long executionStartTime;

    private long executionEndTime;

    public void startExecutionTimer() {
        this.executionStartTime = System.currentTimeMillis();
    }

    public void endExecutionTimer() {
        this.executionEndTime = System.currentTimeMillis();
    }

    public long getProcessExecutionTime() {
        return (this.executionEndTime - this.executionStartTime);
    }
}
