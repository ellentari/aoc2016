package com.adventofcode.day21;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day21Test {

    @Test
    public void testSolvePart1() {
        String input = "abcde";
        String inputOps = "swap position 4 with position 0\n" +
                "swap letter d with letter b\n" +
                "reverse positions 0 through 4\n" +
                "rotate left 1 step\n" +
                "move position 1 to position 4\n" +
                "move position 3 to position 0\n" +
                "rotate based on position of letter b\n" +
                "rotate based on position of letter d";

        assertEquals("decab", Day21.solvePart1(input, inputOps));
    }

    @Test
    public void testSolvePart2() {
        String input = "decab";
        String inputOps = "swap position 4 with position 0\n" +
                "swap letter d with letter b\n" +
                "reverse positions 0 through 4\n" +
                "rotate left 1 step\n" +
                "move position 1 to position 4\n" +
                "move position 3 to position 0\n" +
                "rotate based on position of letter b\n" +
                "rotate based on position of letter d";

        assertEquals("abcde", Day21.solvePart2(input, inputOps));
    }

    @Test
    public void solvePart1() {
        assertEquals("cbeghdaf", Day21.solvePart1("abcdefgh", ResourceUtils.read("day21.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals("bacdefgh", Day21.solvePart2("fbgdceah", ResourceUtils.read("day21.txt")));
    }
}