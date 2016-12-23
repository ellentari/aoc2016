package com.adventofcode.day21;

import com.adventofcode.common.TokenInfo;
import com.adventofcode.common.Tokenizer;
import com.adventofcode.day21.op.LeftRotateOperation;
import com.adventofcode.day21.op.MoveOperation;
import com.adventofcode.day21.op.Operation;
import com.adventofcode.day21.op.ReverseOperation;
import com.adventofcode.day21.op.RightRotateOperation;
import com.adventofcode.day21.op.RightRotatePositionBasedOperation;
import com.adventofcode.day21.op.SwapLettersOperation;
import com.adventofcode.day21.op.SwapPosOperation;

import java.util.List;
import java.util.regex.Matcher;

import static java.util.Arrays.asList;

/**
 * --- Day 21: Scrambled Letters and Hash ---
 * <p>
 * The computer system you're breaking into uses a weird scrambling function to store its passwords.
 * It shouldn't be much trouble to create your own scrambled password so you can add it to the system;
 * you just have to implement the scrambler.
 * <p>
 * The scrambling function is a series of operations (the exact list is provided in your puzzle input).
 * Starting with the password to be scrambled, apply each operation in succession to the string.
 * The individual operations behave as follows:
 * <p>
 * swap position X with position Y means that the letters at indexes X and Y (counting from 0) should be swapped.
 * swap letter X with letter Y means that the letters X and Y should be swapped (regardless of where they appear
 * in the string).
 * rotate left/right X steps means that the whole string should be rotated; for example, one right rotation would
 * turn abcd into dabc.
 * rotate based on position of letter X means that the whole string should be rotated to the right based on the
 * index of letter X (counting from 0) as determined before this instruction does any rotations. Once the index
 * is determined, rotate the string to the right one time, plus a number of times equal to that index, plus one
 * additional time if the index was at least 4.
 * reverse positions X through Y means that the span of letters at indexes X through Y (including the letters at
 * X and Y) should be reversed in order.
 * move position X to position Y means that the letter which is at index X should be removed from the string,
 * then inserted such that it ends up at index Y.
 * For example, suppose you start with abcde and perform the following operations:
 * <p>
 * swap position 4 with position 0 swaps the first and last letters, producing the input for the next step, ebcda.
 * swap letter d with letter b swaps the positions of d and b: edcba.
 * reverse positions 0 through 4 causes the entire string to be reversed, producing abcde.
 * rotate left 1 step shifts all letters left one position, causing the first letter to wrap to the end of
 * the string: bcdea.
 * move position 1 to position 4 removes the letter at position 1 (c), then inserts it at position 4
 * (the end of the string): bdeac.
 * move position 3 to position 0 removes the letter at position 3 (a), then inserts it at position 0
 * (the front of the string): abdec.
 * rotate based on position of letter b finds the index of letter b (1), then rotates the string right once plus
 * a number of times equal to that index (2): ecabd.
 * rotate based on position of letter d finds the index of letter d (4), then rotates the string right once, plus
 * a number of times equal to that index, plus an additional time because the index was at least 4,
 * for a total of 6 right rotations: decab.
 * After these steps, the resulting scrambled password is decab.
 * <p>
 * Now, you just need to generate a new scrambled password and you can access the system.
 * Given the list of scrambling operations in your puzzle input, what is the result of scrambling abcdefgh?
 * <p>
 * --- Part Two ---
 * <p>
 * You scrambled the password correctly, but you discover that you can't actually modify the password file on the system.
 * You'll need to un-scramble one of the existing passwords by reversing the scrambling process.
 * <p>
 * What is the un-scrambled version of the scrambled password fbgdceah?
 */
public class Day21 {

    public static String solvePart1(String input, String inputOperations) {
        List<Operation> operations = parseOperations(inputOperations);

        char[] password = input.toCharArray();

        for (Operation operation : operations) {
            operation.apply(password);
        }

        return new String(password);
    }

    public static String solvePart2(String input, String inputOperations) {
        List<Operation> operations = parseOperations(inputOperations);

        char[] password = input.toCharArray();

        for (int i = operations.size() - 1; i >= 0; i--) {
            operations.get(i).revert(password);
        }

        return new String(password);
    }

    private static List<Operation> parseOperations(String input) {
        Tokenizer<Operation> tokenizer = new Tokenizer<>(asList(
                new TokenInfo<>("swap position (\\d+) with position (\\d+)", Day21::parseSwapPositionOp),
                new TokenInfo<>("swap letter (\\w) with letter (\\w)", Day21::parseSwapLettersOp),
                new TokenInfo<>("rotate left (\\d+) steps?", Day21::parseLeftRotateOp),
                new TokenInfo<>("rotate right (\\d+) steps?", Day21::parseRightRotateOp),
                new TokenInfo<>("rotate based on position of letter (\\w)", Day21::parseRightRotatePositionBasedOp),
                new TokenInfo<>("reverse positions (\\d+) through (\\d+)", Day21::parseReverseOp),
                new TokenInfo<>("move position (\\d+) to position (\\d+)", Day21::parseMoveOp)
        ));

        return tokenizer.parse(input);
    }

    // swap position (X) with position (Y)
    private static Operation parseSwapPositionOp(Matcher matcher) {
        return new SwapPosOperation(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }

    // swap letter (X) with letter (Y)
    private static Operation parseSwapLettersOp(Matcher matcher) {
        return new SwapLettersOperation(matcher.group(1).charAt(0), matcher.group(2).charAt(0));
    }

    // rotate left (X) steps
    private static Operation parseLeftRotateOp(Matcher matcher) {
        return new LeftRotateOperation(Integer.parseInt(matcher.group(1)));
    }

    // rotate right (X) steps
    private static Operation parseRightRotateOp(Matcher matcher) {
        return new RightRotateOperation(Integer.parseInt(matcher.group(1)));
    }

    // rotate based on position of letter (X)
    private static Operation parseRightRotatePositionBasedOp(Matcher matcher) {
        return new RightRotatePositionBasedOperation(matcher.group(1).charAt(0));
    }

    // reverse positions (X) through (Y)
    private static Operation parseReverseOp(Matcher matcher) {
        return new ReverseOperation(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }

    // move position (X) to position (Y)
    private static Operation parseMoveOp(Matcher matcher) {
        return new MoveOperation(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
    }

}
