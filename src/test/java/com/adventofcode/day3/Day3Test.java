package com.adventofcode.day3;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Day3Test {

    @Test
    public void testSolvePart1() throws Exception {
        String input = "5 10 25";

        assertEquals(0, Day3.solvePart1(asList(input.split("\n"))));
    }

    @Test
    public void testSolvePart2() throws Exception {
        String input = "101 301 501\n" +
                "102 302 502\n" +
                "103 303 503\n" +
                "201 401 601\n" +
                "202 402 602\n" +
                "203 403 603";

        assertEquals(6, Day3.solvePart2(asList(input.split("\n"))));
    }

    @Test
    public void solvePart1() {
        assertEquals(917, Day3.solvePart1(ResourceUtils.readLines("day3.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals(1649, Day3.solvePart2(ResourceUtils.readLines("day3.txt")));
    }
}