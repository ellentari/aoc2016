package com.adventofcode.day18;

import com.adventofcode.common.ResourceUtils;

public class Day18 {

    public static void main(String[] args) {
        String input = ResourceUtils.read("day18.txt");

        System.out.println(solvePart1(input, 400000));
    }

    static int solvePart1(String input, int rowsCount) {
        boolean[][] tileMatrix = new boolean[rowsCount][input.length()];

        for (int i = 0; i < input.length(); i++) {
            tileMatrix[0][i] = input.charAt(i) == '.';
        }

        for (int i = 1; i < rowsCount; i++) {
            for (int j = 0; j < tileMatrix[i].length; j++) {
                boolean left = j <= 0 || tileMatrix[i - 1][j - 1];
                boolean center = tileMatrix[i - 1][j];
                boolean right = j >= tileMatrix[i - 1].length - 1 || tileMatrix[i - 1][j + 1];


                tileMatrix[i][j] = !((!left && !center && right)
                        || (!right && !center && left)
                        || (!left && center && right)
                        || (!right && center && left));
            }
        }


        int count = 0;
        for (int i = 0; i < tileMatrix.length; i++) {
            for (int j = 0; j < tileMatrix[i].length; j++) {
//                System.out.print(tileMatrix[i][j] ? '.' : '^');
                if (tileMatrix[i][j]) {
                    count++;
                }
            }
//            System.out.println();
        }

        return count;
    }
}
