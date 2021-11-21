package com.lprakapovich.solver;

public class ProcessTimer {

    private static long executionStartTime;

    private static long executionEndTime;

    public static void startExecutionTimer() {
        executionStartTime = System.currentTimeMillis();
    }

    public static void endExecutionTimer() {
        executionEndTime = System.currentTimeMillis();
    }

    public static long getProcessExecutionTime() {
        return (executionEndTime - executionStartTime);
    }
}
