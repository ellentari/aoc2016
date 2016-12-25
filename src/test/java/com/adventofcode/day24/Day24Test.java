package com.adventofcode.day24;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day24Test {

    @Test
    public void testSolvePart1() {
        String input = "###########\n" +
                "#0.1.....2#\n" +
                "#.#######.#\n" +
                "#4.......3#\n" +
                "###########";

        assertEquals(14, Day24.solvePart1(input));
    }

}