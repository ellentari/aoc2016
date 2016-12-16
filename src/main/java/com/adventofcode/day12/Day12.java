package com.adventofcode.day12;

import com.adventofcode.common.ResourceUtils;
import com.adventofcode.common.TokenInfo;
import com.adventofcode.common.Tokenizer;
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

/**
 * --- Day 12: Leonardo's Monorail ---
 * <p>
 * You finally reach the top floor of this building: a garden with a slanted glass ceiling.
 * Looks like there are no more stars to be had.
 * <p>
 * While sitting on a nearby bench amidst some tiger lilies, you manage to decrypt some of the files you extracted
 * from the servers downstairs.
 * <p>
 * According to these documents, Easter Bunny HQ isn't just this building - it's a collection of buildings in the
 * nearby area. They're all connected by a local monorail, and there's another building not far from here!
 * Unfortunately, being night, the monorail is currently not operating.
 * <p>
 * You remotely connect to the monorail control systems and discover that the boot sequence expects a password.
 * The password-checking logic (your puzzle input) is easy to extract, but the code it uses is strange:
 * it's assembunny code designed for the new computer you just assembled. You'll have to execute the code
 * and get the password.
 * <p>
 * The assembunny code you've extracted operates on four registers (a, b, c, and d) that start at 0 and can
 * hold any integer. However, it seems to make use of only a few instructions:
 * <p>
 * cpy x y copies x (either an integer or the value of a register) into register y.
 * inc x increases the value of register x by one.
 * dec x decreases the value of register x by one.
 * jnz x y jumps to an instruction y away (positive means forward; negative means backward), but only if x is not zero.
 * The jnz instruction moves relative to itself: an offset of -1 would continue at the previous instruction,
 * while an offset of 2 would skip over the next instruction.
 * <p>
 * For example:
 * <p>
 * cpy 41 a
 * inc a
 * inc a
 * dec a
 * jnz a 2
 * dec a
 * The above code would set register a to 41, increase its value by 2, decrease its value by 1,
 * and then skip the last dec a (because a is not zero, so the jnz a 2 skips it), leaving register a at 42.
 * When you move past the last instruction, the program halts.
 * <p>
 * After executing the assembunny code in your puzzle input, what value is left in register a?
 * <p>
 * --- Part Two ---
 * <p>
 * As you head down the fire escape to the monorail, you notice it didn't start; register c needs to be
 * initialized to the position of the ignition key.
 * <p>
 * If you instead initialize register c to be 1, what value is now left in register a?
 */
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
