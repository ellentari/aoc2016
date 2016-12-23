package com.adventofcode.day12.instruction;

import com.adventofcode.day12.InstructionNotValidException;
import com.adventofcode.day12.Register;
import com.adventofcode.day12.argument.Argument;

import java.util.function.IntSupplier;

public class CopyInstruction extends AbstractInstruction implements TwoArgInstruction {

    private Argument from;
    private Argument to;

    public CopyInstruction(Argument from, Argument to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected void doExecute(int startAddress) {
        if (to instanceof IncInstruction) {
            throw new InstructionNotValidException("Can not copy to int value");
        }

        to.setValue(from.getValue());
    }

    @Override
    public String toString() {
        return "cpy " + from + " " + to;
    }

    @Override
    public Argument getArg1() {
        return from;
    }

    @Override
    public Argument getArg2() {
        return to;
    }
}
