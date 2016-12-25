package com.adventofcode.day24;

import com.adventofcode.common.ResourceUtils;
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

    @Test
    public void solvePart1() {
        assertEquals(474, Day24.solvePart1(ResourceUtils.read("day24.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals(696, Day24.solvePart2(ResourceUtils.read("day24.txt")));
    }

}