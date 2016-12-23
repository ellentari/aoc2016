package com.adventofcode.day12.argument;

public class IntArgument implements Argument {

    private int value;

    public IntArgument(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void increment() {
        value++;
    }

    @Override
    public void decrement() {
        value--;
    }

    @Override
    public Argument copy() {
        return new IntArgument(value);
    }

    @Override
    public String toString() {
        return "IntArgument{" +
                "value=" + value +
                '}';
    }
}
