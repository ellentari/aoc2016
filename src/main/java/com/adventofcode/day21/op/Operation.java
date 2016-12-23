package com.adventofcode.day21.op;

public interface Operation {

    void apply(char[] toValue);

    void revert(char[] toValue);

}
