package com.adventofcode.day21.op;


import static com.adventofcode.day21.op.OpUtils.indexOf;
import static com.adventofcode.day21.op.OpUtils.swap;

public class SwapLettersOperation implements Operation{

    private final char x;
    private final char y;

    public SwapLettersOperation(char x, char y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void apply(char[] toValue) {
        swap(toValue, indexOf(toValue, x), indexOf(toValue, y));
    }

    @Override
    public void revert(char[] toValue) {
        apply(toValue);
    }

}
