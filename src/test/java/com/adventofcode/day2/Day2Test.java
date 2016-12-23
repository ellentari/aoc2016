package com.adventofcode.day2;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Day2Test {

    @Test
    public void testSolvePart1() throws Exception {
        String input = "ULL\n" +
                "RRDDD\n" +
                "LURDL\n" +
                "UUUUD";

        assertEquals("1985", Day2.solvePart1(asList(input.split("\n"))));
    }

    @Test
    public void testSolvePart2() throws Exception {
        String input = "ULL\n" +
                "RRDDD\n" +
                "LURDL\n" +
                "UUUUD";

        assertEquals("5DB3", Day2.solvePart2(asList(input.split("\n"))));
    }

    @Test
    public void solvePart1() {
        assertEquals("76792", Day2.solvePart1(ResourceUtils.readLines("day2.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals("A7AC3", Day2.solvePart2(ResourceUtils.readLines("day2.txt")));
    }
}