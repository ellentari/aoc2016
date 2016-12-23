package com.adventofcode.day21.op;

import static com.adventofcode.day21.op.OpUtils.rotateLeft;
import static com.adventofcode.day21.op.OpUtils.rotateRight;

public class RightRotateOperation implements Operation {

    private final int times;

    public RightRotateOperation(int times) {
        this.times = times;
    }

    @Override
    public void apply(char[] toValue) {
        rotateRight(toValue, times);
    }

    @Override
    public void revert(char[] toValue) {
        rotateLeft(toValue, times);
    }

}
