package com.adventofcode.day21.op;

import static com.adventofcode.day21.op.OpUtils.move;

public class MoveOperation implements Operation {

    private final int x;
    private final int y;

    public MoveOperation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void apply(char[] toValue) {
        move(toValue, x, y);
    }

    @Override
    public void revert(char[] toValue) {
        move(toValue, y, x);
    }

}
