package com.adventofcode.day12.instruction;

import com.adventofcode.day12.Register;

import java.util.function.IntSupplier;

public class CopyInstruction extends AbstractInstruction {

    private IntSupplier from;
    private Register.Key to;

    public CopyInstruction(Register register, IntSupplier from, Register.Key to) {
        super(register);
        this.from = from;
        this.to = to;
    }

    @Override
    protected void doExecute() {
        register.set(to, from.getAsInt());
    }

    @Override
    public String toString() {
        return "cpy " + from.getAsInt() + " " + to;
    }
}
