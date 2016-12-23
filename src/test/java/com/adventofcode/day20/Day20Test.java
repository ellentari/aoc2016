package com.adventofcode.day20;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day20Test {

    @Test
    public void testSolvePart1() {
        String input = "5-8\n" +
                "0-2\n" +
                "4-7";

        assertEquals(3, Day20.solvePart1(input, 9L));
    }

    @Test
    public void testSolvePart2() {
        String input = "5-8\n" +
                "0-2\n" +
                "4-7";

        assertEquals(2, Day20.solvePart2(input, 9L));
    }

    @Test
    public void solvePart1() {
        assertEquals(31053880, Day20.solvePart1(ResourceUtils.read("day20.txt"), 4294967295L));
    }

    @Test
    public void solvePart2() {
        assertEquals(117, Day20.solvePart2(ResourceUtils.read("day20.txt"), 4294967295L));
    }

}