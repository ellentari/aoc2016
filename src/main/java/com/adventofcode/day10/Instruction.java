package com.adventofcode.day10;

interface Instruction extends Comparable<Instruction> {

    void execute();

    int getPriority();

    @Override
    default int compareTo(Instruction o) {
        return Integer.compare(o.getPriority(), getPriority());
    }
}
