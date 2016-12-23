package com.adventofcode.day23;

import com.adventofcode.common.ResourceUtils;
import com.adventofcode.common.TokenInfo;
import com.adventofcode.common.Tokenizer;
import com.adventofcode.day12.Day12;
import com.adventofcode.day12.Register;
import com.adventofcode.day12.instruction.Instruction;
import com.adventofcode.day23.instruction.TglInstruction;

import java.util.List;
import java.util.regex.Matcher;

import static java.util.Arrays.asList;

/**
 * --- Day 23: Safe Cracking ---
 * <p>
 * This is one of the top floors of the nicest tower in EBHQ. The Easter Bunny's private office is here, complete with
 * a safe hidden behind a painting, and who wouldn't hide a star in a safe behind a painting?
 * <p>
 * The safe has a digital screen and keypad for code entry. A sticky note attached to the safe has a password hint on
 * it: "eggs". The painting is of a large rabbit coloring some eggs. You see 7.
 * <p>
 * When you go to type the code, though, nothing appears on the display; instead, the keypad comes apart in your hands,
 * apparently having been smashed. Behind it is some kind of socket - one that matches a connector in your prototype
 * computer! You pull apart the smashed keypad and extract the logic circuit, plug it into your computer, and plug
 * your computer into the safe.
 * <p>
 * Now, you just need to figure out what output the keypad would have sent to the safe. You extract the assembunny code
 * from the logic chip (your puzzle input).
 * The code looks like it uses almost the same architecture and instruction set that the monorail computer used!
 * You should be able to use the same assembunny interpreter for this as you did there, but with one new instruction:
 * <p>
 * tgl x toggles the instruction x away (pointing at instructions like jnz does: positive means forward; negative
 * means backward):
 * <p>
 * For one-argument instructions, inc becomes dec, and all other one-argument instructions become inc.
 * For two-argument instructions, jnz becomes cpy, and all other two-instructions become jnz.
 * The arguments of a toggled instruction are not affected.
 * If an attempt is made to toggle an instruction outside the program, nothing happens.
 * If toggling produces an invalid instruction (like cpy 1 2) and an attempt is later made to execute that instruction,
 * skip it instead.
 * If tgl toggles itself (for example, if a is 0, tgl a would target itself and become inc a), the resulting
 * instruction is not executed until the next time it is reached.
 * For example, given this program:
 * <p>
 * cpy 2 a
 * tgl a
 * tgl a
 * tgl a
 * cpy 1 a
 * dec a
 * dec a
 * cpy 2 a initializes register a to 2.
 * The first tgl a toggles an instruction a (2) away from it, which changes the third tgl a into inc a.
 * The second tgl a also modifies an instruction 2 away from it, which changes the cpy 1 a into jnz 1 a.
 * The fourth line, which is now inc a, increments a to 3.
 * Finally, the fifth line, which is now jnz 1 a, jumps a (3) instructions ahead, skipping the dec a instructions.
 * In this example, the final value in register a is 3.
 * <p>
 * The rest of the electronics seem to place the keypad entry (the number of eggs, 7) in register a, run the code,
 * and then send the value left in register a to the safe.
 * <p>
 * What value should be sent to the safe?
 * <p>
 * --- Part Two ---
 * <p>
 * The safe doesn't open, but it does make several angry noises to express its frustration.
 * <p>
 * You're quite sure your logic is working correctly, so the only other thing is... you check the painting again.
 * As it turns out, colored eggs are still eggs. Now you count 12.
 * <p>
 * As you run the program with this new input, the prototype computer begins to overheat. You wonder what's taking so
 * long, and whether the lack of any instruction more powerful than "add one" has anything to do with it.
 * Don't bunnies usually multiply?
 * <p>
 * Anyway, what value should actually be sent to the safe?
 */
public class Day23 {

    public static void main(String[] args) {
        String input = ResourceUtils.read("day23.txt");

        System.out.println(solve(input, 7));
        System.out.println(solve(input, 12));
    }

    static int solve(String input, int initialA) {
        Register register = new Register(initialA, 0, 0, 0);

        List<Instruction> instructions = parseInstructions(input, register);

        for (int i = 0; i < instructions.size(); ) {
            i = instructions.get(i).execute(i);
        }

        return register.get(new Register.Key('a'));
    }

    private static List<Instruction> parseInstructions(String input, Register register) {
        Tokenizer<Instruction> tokenizer = new Tokenizer<>(asList(
                new TokenInfo<>("inc ([a-d])", m -> Day12.parseIncInstruction(m, register)),
                new TokenInfo<>("dec ([a-d])", m -> Day12.parseDecInstruction(m, register)),
                new TokenInfo<>("tgl ((?<regKey>[a-d])|(?<val>-?\\d+))", (m, t) -> parseTglInstruction(m, t, register)),
                new TokenInfo<>("cpy ((?<regKey>[a-d])|(?<val>-?\\d+)) ([a-d])", m -> Day12.parseCopyInstruction(m, register)),
                new TokenInfo<>("jnz ((?<regKey1>[a-d])|(?<val1>-?\\d+)) ((?<regKey2>[a-d])|(?<val2>-?\\d+))",
                        m -> Day12.parseJnzInstruction(m, register))
        ));

        return tokenizer.parse(input);
    }

    private static Instruction parseTglInstruction(Matcher matcher, List<Instruction> instructions, Register register) {
        return new TglInstruction(instructions, Day12.parseArgument(matcher, register, "regKey", "val"));
    }
}
