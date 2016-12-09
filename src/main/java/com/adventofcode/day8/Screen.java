package com.adventofcode.day8;

class Screen {

    private final int width;
    private final int height;
    private final boolean[][] screen;

    Screen(int width, int height) {
        this.width = width;
        this.height = height;
        screen = new boolean[height][width];
    }

    void rectangle(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                screen[i][j] = true;
            }
        }
    }

    void rotateRow(int row, int times) {
        int rotateTimes = times % width;

        for (int i = 0; i < rotateTimes; i++) {
            boolean last = screen[row][width - 1];
            System.arraycopy(screen[row], 0, screen[row], 1, width - 1);
            screen[row][0] = last;
        }
    }

    void rotateColumn(int column, int times) {
        int rotateTimes = times % height;

        for (int i = 0; i < rotateTimes; i++) {
            boolean last = screen[height - 1][column];
            for (int j = height - 1; j > 0; j--) {
                screen[j][column] = screen[j - 1][column];
            }
            screen[0][column] = last;
        }
    }

    int getTurnedOnCount() {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (screen[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (screen[i][j]) {
                    result.append("#");
                } else {
                    result.append(" ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}
