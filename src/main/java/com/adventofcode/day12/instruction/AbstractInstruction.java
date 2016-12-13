package com.adventofcode.day12.instruction;

import com.adventofcode.day12.Register;

public abstract class AbstractInstruction implements Instruction {

    Register register;

    AbstractInstruction(Register register) {
        this.register = register;
    }

    @Override
    public int execute(int startAddress) {
        doExecute();

        return returnAddress(startAddress);
    }

    protected abstract void doExecute();

    protected int returnAddress(int startAddress) {
        return startAddress + 1;
    }
}
