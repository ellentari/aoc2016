package com.adventofcode.day1;

import com.adventofcode.common.ResourceUtils;
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

    @Test
    public void solvePart1() {
        assertEquals(161, Day1.solvePart1(ResourceUtils.read("day1.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals(110, Day1.solvePart2(ResourceUtils.read("day1.txt")));
    }
}