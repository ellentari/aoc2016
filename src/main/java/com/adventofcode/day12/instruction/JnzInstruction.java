package com.adventofcode.day12.instruction;

import com.adventofcode.day12.argument.Argument;

public class JnzInstruction extends AbstractInstruction implements TwoArgInstruction  {

    private Argument value;
    private Argument n;

    public JnzInstruction(Argument value, Argument n) {
        this.value = value;
        this.n = n;
    }

    @Override
    public Argument getArg1() {
        return value;
    }

    @Override
    public Argument getArg2() {
        return n;
    }

    @Override
    protected void doExecute(int startAddress) {
        // no op
    }

    @Override
    protected int returnAddress(int startAddress) {
        return startAddress + (value.getValue() != 0 ? n.getValue() : 1);
    }

    @Override
    public String toString() {
        return "jnz " + value + " " + n;
    }
}
