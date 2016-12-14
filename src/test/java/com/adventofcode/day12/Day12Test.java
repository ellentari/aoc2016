package com.adventofcode.day12;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class Day12Test {

    @Test
    public void solvePart1() {
        String input = "cpy 41 a\n" +
                "inc a\n" +
                "inc a\n" +
                "dec a\n" +
                "jnz a 2\n" +
                "dec a";

        assertEquals(42, Day12.solvePart1(asList(input.split("\\n"))));
    }

}