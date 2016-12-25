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

/**
 * --- Day 25: Clock Signal ---
 * <p>
 * You open the door and find yourself on the roof. The city sprawls away from you for miles and miles.
 * <p>
 * There's not much time now - it's already Christmas, but you're nowhere near the North Pole,
 * much too far to deliver these stars to the sleigh in time.
 * <p>
 * However, maybe the huge antenna up here can offer a solution. After all, the sleigh doesn't need the stars,
 * exactly; it needs the timing data they provide, and you happen to have a massive signal generator right here.
 * <p>
 * You connect the stars you have to your prototype computer, connect that to the antenna, and begin the transmission.
 * <p>
 * Nothing happens.
 * <p>
 * You call the service number printed on the side of the antenna and quickly explain the situation.
 * "I'm not sure what kind of equipment you have connected over there," he says, "but you need a clock signal."
 * You try to explain that this is a signal for a clock.
 * <p>
 * "No, no, a clock signal - timing information so the antenna computer knows how to read the data you're sending it.
 * An endless, alternating pattern of 0, 1, 0, 1, 0, 1, 0, 1, 0, 1...." He trails off.
 * <p>
 * You ask if the antenna can handle a clock signal at the frequency you would need to use for the data from the stars.
 * "There's no way it can! The only antenna we've installed capable of that is on top of a top-secret Easter
 * Bunny installation, and you're definitely not-" You hang up the phone.
 * <p>
 * You've extracted the antenna's clock signal generation assembunny code (your puzzle input);
 * it looks mostly compatible with code you worked on just recently.
 * <p>
 * This antenna code, being a signal generator, uses one extra instruction:
 * <p>
 * out x transmits x (either an integer or the value of a register) as the next value for the clock signal.
 * The code takes a value (via register a) that describes the signal to generate, but you're not sure how it's used.
 * You'll have to find the input to produce the right signal through experimentation.
 * <p>
 * What is the lowest positive integer that can be used to initialize register a and cause the code
 * to output a clock signal of 0, 1, 0, 1... repeating forever?
 */
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
                    if (output.matches("^(01)+$")) {
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
