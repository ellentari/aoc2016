package com.adventofcode.day12.instruction;

import com.adventofcode.day12.Register;

public class IncInstruction extends AbstractInstruction {

    private Register.Key key;

    public IncInstruction(Register register, Register.Key key) {
        super(register);
        this.key = key;
    }

    @Override
    protected void doExecute() {
        register.increment(key);
    }

    @Override
    public String toString() {
        return "inc " + key;
    }
}
