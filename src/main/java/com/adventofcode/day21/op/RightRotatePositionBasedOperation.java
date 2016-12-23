package com.adventofcode.day21.op;

import java.util.Arrays;

import static com.adventofcode.day21.op.OpUtils.indexOf;
import static com.adventofcode.day21.op.OpUtils.rotateLeft;
import static com.adventofcode.day21.op.OpUtils.rotateRight;

public class RightRotatePositionBasedOperation implements Operation {

    private final char x;

    public RightRotatePositionBasedOperation(char x) {
        this.x = x;
    }

    @Override
    public void apply(char[] toValue) {
        int position = indexOf(toValue, x);
        rotateRight(toValue, 1 + position + (position >= 4 ? 1 : 0));
    }

    @Override
    public void revert(char[] toValue) {
        char[] originalToValue = Arrays.copyOf(toValue, toValue.length);
        char[] toValueBuf;

        do {
            rotateLeft(toValue, 1);
            toValueBuf = Arrays.copyOf(toValue, toValue.length);
            apply(toValueBuf);
        } while (!OpUtils.equals(toValueBuf, originalToValue));
    }

}
