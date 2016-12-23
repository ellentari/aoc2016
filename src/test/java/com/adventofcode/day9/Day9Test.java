package com.adventofcode.day9;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day9Test {

    @Test
    public void testSolvePart1() {
        assertEquals(6, Day9.solvePart1("ADVENT"));
        assertEquals(7, Day9.solvePart1("A(1x5)BC"));
        assertEquals(9, Day9.solvePart1("(3x3)XYZ"));
        assertEquals(11, Day9.solvePart1("A(2x2)BCD(2x2)EFG"));
        assertEquals(6, Day9.solvePart1("(6x1)(1x3)A"));
        assertEquals(18, Day9.solvePart1("X(8x2)(3x3)ABCY"));
    }

    @Test
    public void testSolvePart2() {
        assertEquals(9, Day9.solvePart2("(3x3)XYZ"));
        assertEquals(20, Day9.solvePart2("X(8x2)(3x3)ABCY"));
        assertEquals(241920, Day9.solvePart2("(27x12)(20x12)(13x14)(7x10)(1x12)A"));
        assertEquals(445, Day9.solvePart2("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"));
    }

    @Test
    public void solvePart1() {
        assertEquals(115118, Day9.solvePart1(ResourceUtils.read("day9.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals(11107527530L, Day9.solvePart2(ResourceUtils.read("day9.txt")));
    }

}