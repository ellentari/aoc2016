package com.adventofcode.day12.instruction;

import com.adventofcode.day12.argument.Argument;

public class DecInstruction extends AbstractInstruction implements OneArgInstruction {

    private Argument argument;

    public DecInstruction(Argument argument) {
        this.argument = argument;
    }

    @Override
    protected void doExecute(int startAddress) {
        argument.decrement();
    }

    @Override
    public String toString() {
        return "dec " + argument;
    }

    @Override
    public Argument getArg() {
        return argument;
    }
}
