package com.adventofcode.day12.instruction;

import com.adventofcode.day12.argument.Argument;

public class IncInstruction extends AbstractInstruction implements OneArgInstruction  {

    private Argument argument;

    public IncInstruction(Argument argument) {
        this.argument = argument;
    }

    @Override
    protected void doExecute(int startAddress) {
        argument.increment();
    }

    @Override
    public String toString() {
        return "inc " + argument;
    }

    @Override
    public Argument getArg() {
        return argument;
    }
}
