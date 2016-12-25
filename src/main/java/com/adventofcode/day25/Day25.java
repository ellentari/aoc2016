package com.adventofcode.day25;

import com.adventofcode.common.TokenInfo;
import com.adventofcode.common.Tokenizer;
import com.adventofcode.day12.Day12;
import com.adventofcode.day12.Register;
import com.adventofcode.day12.instruction.Instruction;
import com.adventofcode.day23.Day23;
import com.adventofcode.day25.instruction.OutInstruction;

import java.io.StringWriter;
import java.util.List;
import java.util.regex.Matcher;

import static com.adventofcode.day12.Day12.parseArgument;
import static java.util.Arrays.asList;

public class Day25 {

    public static int solvePart1(String input) {
        for (int a = 0; ; a++) {
            if (checkAProduces01Only(a, input)) {
                return a;
            }
        }
    }

    private static boolean checkAProduces01Only(int initA, String input) {
        Register register = new Register(initA, 0, 0, 0);

        List<Instruction> instructions = parseInstructions(input, register);

        int count = 0;
        for (int i = 0; i < instructions.size() && count < 1000; ) {
            Instruction instruction = instructions.get(i);
            i = instruction.execute(i);
            if (instruction instanceof OutInstruction) {
                String output = ((OutInstruction) instruction).getWriter().toString();
                if (output.length() % 2 == 0) {
                    if(output.matches("^(01)+$")) {
                        count++;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static List<Instruction> parseInstructions(String input, Register register) {
        Tokenizer<Instruction> tokenizer = new Tokenizer<>(asList(
                new TokenInfo<>("inc ([a-d])", m -> Day12.parseIncInstruction(m, register)),
                new TokenInfo<>("dec ([a-d])", m -> Day12.parseDecInstruction(m, register)),
                new TokenInfo<>("out ((?<regKey>[a-d])|(?<val>-?\\d+))", m -> parseOutInstruction(m, register)),
                new TokenInfo<>("tgl ((?<regKey>[a-d])|(?<val>-?\\d+))", (m, t) -> Day23.parseTglInstruction(m, t, register)),
                new TokenInfo<>("cpy ((?<regKey>[a-d])|(?<val>-?\\d+)) ([a-d])", m -> Day12.parseCopyInstruction(m, register)),
                new TokenInfo<>("jnz ((?<regKey1>[a-d])|(?<val1>-?\\d+)) ((?<regKey2>[a-d])|(?<val2>-?\\d+))",
                        m -> Day12.parseJnzInstruction(m, register))
        ));

        return tokenizer.parse(input);
    }

    private static Instruction parseOutInstruction(Matcher matcher, Register register) {
        return new OutInstruction(parseArgument(matcher, register, "regKey", "val"), new StringWriter());
    }
}
