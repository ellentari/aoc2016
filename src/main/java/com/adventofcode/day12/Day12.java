package com.adventofcode.day12;

import com.adventofcode.ResourceUtils;
import com.adventofcode.TokenInfo;
import com.adventofcode.Tokenizer;
import com.adventofcode.day12.instruction.CopyInstruction;
import com.adventofcode.day12.instruction.DecInstruction;
import com.adventofcode.day12.instruction.IncInstruction;
import com.adventofcode.day12.instruction.Instruction;
import com.adventofcode.day12.instruction.JnzInstruction;

import java.util.Collection;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.regex.Matcher;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Day12 {

    public static void main(String[] args) {
        List<String> input = ResourceUtils.readLines("day12.txt");

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    static int solvePart1(List<String> input) {
        Register register = new Register();

        List<Instruction> instructions = parseInstructions(input, register);

        for (int i = 0; i < instructions.size(); ) {
            i = instructions.get(i).execute(i);
        }

        return register.get(new Register.Key('a'));
    }

    private static int solvePart2(List<String> input) {
        Register register = new Register(0, 0, 1, 0);

        List<Instruction> instructions = parseInstructions(input, register);

        for (int i = 0; i < instructions.size(); ) {
            i = instructions.get(i).execute(i);
        }

        return register.get(new Register.Key('a'));
    }

    private static List<Instruction> parseInstructions(List<String> input, Register register) {
        Tokenizer<Instruction> tokenizer = new Tokenizer<>(asList(
                new TokenInfo<>("inc ([a-d])", m -> parseIncInstruction(m, register)),
                new TokenInfo<>("dec ([a-d])", m -> parseDecInstruction(m, register)),
                new TokenInfo<>("cpy ((?<regKey>[a-d])|(?<val>\\d+)) ([a-d])", m -> parseCopyInstruction(m, register)),
                new TokenInfo<>("jnz ((?<regKey>[a-d])|(?<val>\\d+)) (-?\\d+)", m -> parseJnzInstruction(m, register))
        ));

        return input.stream()
                .map(tokenizer::parse)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private static Instruction parseIncInstruction(Matcher matcher, Register register) {
        return new IncInstruction(register, new Register.Key(matcher.group(1)));
    }

    private static Instruction parseDecInstruction(Matcher matcher, Register register) {
        return new DecInstruction(register, new Register.Key(matcher.group(1)));
    }

    private static Instruction parseCopyInstruction(Matcher matcher, Register register) {
        String regKey = matcher.group("regKey");
        String val = matcher.group("val");

        int intVal = val != null ? Integer.parseInt(val) : -1;

        IntSupplier valSupplier;
        if (regKey != null) {
            valSupplier = () -> register.get(new Register.Key(regKey));
        } else {
            valSupplier = () -> intVal;
        }

        return new CopyInstruction(register, valSupplier, new Register.Key(matcher.group(4)));
    }

    private static Instruction parseJnzInstruction(Matcher matcher, Register register) {
        String regKey = matcher.group("regKey");
        String val = matcher.group("val");

        int intVal = val != null ? Integer.parseInt(val) : -1;

        IntSupplier valSupplier;
        if (regKey != null) {
            valSupplier = () -> register.get(new Register.Key(regKey));
        } else {
            valSupplier = () -> intVal;
        }

        return new JnzInstruction(register, valSupplier, Integer.parseInt(matcher.group(4)));
    }

}
