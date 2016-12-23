package com.adventofcode.day23.instruction;

import com.adventofcode.day12.argument.Argument;
import com.adventofcode.day12.instruction.AbstractInstruction;
import com.adventofcode.day12.instruction.CopyInstruction;
import com.adventofcode.day12.instruction.DecInstruction;
import com.adventofcode.day12.instruction.IncInstruction;
import com.adventofcode.day12.instruction.Instruction;
import com.adventofcode.day12.instruction.JnzInstruction;
import com.adventofcode.day12.instruction.OneArgInstruction;
import com.adventofcode.day12.instruction.TwoArgInstruction;

import java.util.List;

public class TglInstruction extends AbstractInstruction implements OneArgInstruction {

    private Argument n;
    private List<Instruction> instructions;

    public TglInstruction(List<Instruction> instructions, Argument n) {
        this.instructions = instructions;
        this.n = n;
    }

    @Override
    protected void doExecute(int startAddress) {
        int iToToggle = startAddress + n.getValue();

        if (iToToggle >= 0 && iToToggle < instructions.size()) {
            instructions.set(iToToggle, toggle(instructions.get(iToToggle)));
        }
    }

    private Instruction toggle(Instruction toToggle) {
        Instruction toggled;
        if (toToggle instanceof OneArgInstruction) {
            toggled = toggleOneArg((OneArgInstruction) toToggle);
        } else if (toToggle instanceof TwoArgInstruction) {
            toggled = toggleTwoArg((TwoArgInstruction) toToggle);
        } else {
            toggled = toToggle;
        }
        return toggled;
    }

    private Instruction toggleOneArg(OneArgInstruction toToggle) {
        Instruction toggled;
        if (toToggle instanceof IncInstruction) {
            toggled = new DecInstruction(toToggle.getArg());
        } else {
            toggled = new IncInstruction(toToggle.getArg());
        }
        return toggled;
    }

    private Instruction toggleTwoArg(TwoArgInstruction toToggle) {
        Instruction toggled;
        if (toToggle instanceof JnzInstruction) {
            toggled = new CopyInstruction(toToggle.getArg1(), toToggle.getArg2());
        } else {
            toggled = new JnzInstruction(toToggle.getArg1(), toToggle.getArg2());
        }
        return toggled;
    }

    @Override
    public Argument getArg() {
        return n;
    }

    @Override
    public String toString() {
        return "tgl " + n;
    }
}
