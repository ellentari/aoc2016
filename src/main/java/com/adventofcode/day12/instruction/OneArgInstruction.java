package com.adventofcode.day12.instruction;

import com.adventofcode.day12.argument.Argument;

public interface OneArgInstruction extends Instruction {

    Argument getArg();

}
