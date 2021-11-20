package com.lprakapovich.util;

import java.util.Arrays;

public class ArrayUtil {

    public static int[][] getArrayCopy(int[][] array) {
        return Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
    }

    public static boolean arraysAreEqual(int[][] ar1, int[][] ar2) {
        for (int i = 0; i < ar1.length; i++) {
            for (int j = 0; j < ar1.length; j++) {
                if (ar1[i][j] != ar2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
