package com.adventofcode;

import java.util.Arrays;
import java.util.List;

public class Day8 {

    public static void main(String[] args) {
        List<String> input = ResourceUtils.readLines("day8.txt");

        int[][] matrix = new int[6][50];
        for (String line : input) {
            if (line.startsWith("rect")) {
                String[] split = line.split("[\\sx]");
                rect(matrix, Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            } else if (line.startsWith("rotate row")) {
                String[] split = line.split("[\\s=]");
                rotateRow(matrix, Integer.parseInt(split[3]), Integer.parseInt(split[5]));
            } else if (line.startsWith("rotate column")) {
                String[] split = line.split("[\\s=]");
                rotateColumn(matrix, Integer.parseInt(split[3]), Integer.parseInt(split[5]));
            }
        }

        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == 1) {
                    System.out.print(matrix[i][j] + " ");
                    count++;
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        System.out.println(count);
    }

    private static void rect(int[][] matrix, int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = 1;
            }
        }
    }

    private static void rotateRow(int[][] matrix, int row, int times) {
        for (int i = 0; i < times; i++) {
            int last = matrix[row][matrix[row].length - 1];
            for (int j = matrix[row].length - 1; j > 0; j--) {
                matrix[row][j] = matrix[row][j - 1];
            }
            matrix[row][0] = last;
        }
    }

    private static void rotateColumn(int[][] matrix, int column, int times) {
        for (int i = 0; i < times; i++) {
            int last = matrix[matrix.length - 1][column];
            for (int j = matrix.length - 1; j > 0; j--) {
                matrix[j][column] = matrix[j - 1][column];
            }
            matrix[0][column] = last;
        }
    }




}
