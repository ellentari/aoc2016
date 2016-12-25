package com.adventofcode.day25.instruction;

import com.adventofcode.day12.argument.Argument;
import com.adventofcode.day12.instruction.AbstractInstruction;
import com.adventofcode.day12.instruction.OneArgInstruction;

import java.io.IOException;
import java.io.Writer;

public class OutInstruction extends AbstractInstruction implements OneArgInstruction {

    private Argument x;
    private Writer writer;

    public OutInstruction(Argument x, Writer writer) {
        this.x = x;
        this.writer = writer;
    }

    @Override
    public Argument getArg() {
        return x;
    }

    public Writer getWriter() {
        return writer;
    }

    @Override
    protected void doExecute(int startAddress) {
        try {
            writer.write(String.valueOf(x.getValue()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to writer signal", e);
        }
    }
}
