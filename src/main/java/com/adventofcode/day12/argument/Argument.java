package com.adventofcode.day12.argument;

public interface Argument {

    int getValue();

    void setValue(int value);

    void increment();

    void decrement();

    Argument copy();

}
