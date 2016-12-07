package com.adventofcode.day7;

class Range {

    /**
     * start inclusive
     */
    private final int start;

    /**
     * end inclusive
     */
    private final int end;

    Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    boolean isIn(int value) {
        return start <= value && value <= end;
    }

    int getStart() {
        return start;
    }
}
