package com.adventofcode.day1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day1Test {

    @Test
    public void testSolvePart1() {
        assertEquals(5, Day1.solvePart1("R2, L3"));
        assertEquals(2, Day1.solvePart1("R2, R2, R2"));
        assertEquals(12, Day1.solvePart1("R5, L5, R5, R3"));
    }

    @Test
    public void testSolvePart2() {
        assertEquals(4, Day1.solvePart2("R8, R4, R4, R8"));
    }
}