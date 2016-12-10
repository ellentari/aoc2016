package com.adventofcode.day10;

public class Chip implements Comparable<Chip> {

    private final int chip;

    Chip(int chip) {
        this.chip = chip;
    }

    int getChip() {
        return chip;
    }

    @Override
    public int compareTo(Chip o) {
        return Integer.compare(chip, o.chip);
    }

    @Override
    public String toString() {
        return String.valueOf(chip);
    }
}
