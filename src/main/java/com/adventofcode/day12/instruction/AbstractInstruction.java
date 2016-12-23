package com.adventofcode.day12.instruction;

public abstract class AbstractInstruction implements Instruction {

    @Override
    public int execute(int startAddress) {
        doExecute(startAddress);

        return returnAddress(startAddress);
    }

    protected abstract void doExecute(int startAddress);

    protected int returnAddress(int startAddress) {
        return startAddress + 1;
    }
}
