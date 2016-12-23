package com.adventofcode.day21.op;

import static com.adventofcode.day21.op.OpUtils.reverse;

public class ReverseOperation implements Operation {

    private final int x;
    private final int y;

    public ReverseOperation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void apply(char[] toValue) {
        reverse(toValue, x, y);
    }

    @Override
    public void revert(char[] toValue) {
        apply(toValue);
    }

}
