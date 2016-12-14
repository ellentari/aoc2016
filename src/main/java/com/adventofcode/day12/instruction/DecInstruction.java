package com.adventofcode.day12.instruction;

import com.adventofcode.day12.Register;

public class DecInstruction extends AbstractInstruction {

    private Register.Key key;

    public DecInstruction(Register register, Register.Key key) {
        super(register);
        this.key = key;
    }

    @Override
    protected void doExecute() {
        register.decrement(key);
    }

    @Override
    public String toString() {
        return "dec " + key;
    }
}
