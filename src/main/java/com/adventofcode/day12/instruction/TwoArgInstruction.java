package com.adventofcode.day12.instruction;

import com.adventofcode.day12.argument.Argument;

public interface TwoArgInstruction extends Instruction {

    Argument getArg1();

    Argument getArg2();

}
