package com.lprakapovich.util;

import java.util.Arrays;

public class ArrayUtil {

    public static int[][] getArrayCopy(int[][] array) {
        return Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
    }
}
