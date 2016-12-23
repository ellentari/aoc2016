package com.adventofcode.day21.op;

import static com.adventofcode.day21.op.OpUtils.swap;

public class SwapPosOperation implements Operation {

    private final int x;
    private final int y;

    public SwapPosOperation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void apply(char[] toValue) {
        swap(toValue, x, y);
    }

    @Override
    public void revert(char[] toValue) {
        apply(toValue);
    }

}
