package com.adventofcode.day12.instruction;

import com.adventofcode.day12.Register;

import java.util.function.IntSupplier;

public class JnzInstruction extends AbstractInstruction {

    private IntSupplier value;
    private int n;

    public JnzInstruction(Register register, IntSupplier value, int n) {
        super(register);
        this.value = value;
        this.n = n;
    }

    @Override
    protected void doExecute() {
        // no op
    }

    @Override
    protected int returnAddress(int startAddress) {
        return startAddress + (value.getAsInt() != 0 ? n : 1);
    }

    @Override
    public String toString() {
        return "jnz " + value.getAsInt() + " " + n;
    }
}
