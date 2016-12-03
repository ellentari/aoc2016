package com.adventofcode.day2;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Day2Test {

    @Test
    public void solvePart1() throws Exception {
        String input = "ULL\n" +
                "RRDDD\n" +
                "LURDL\n" +
                "UUUUD";

        assertEquals("1985", Day2.solvePart1(asList(input.split("\n"))));
    }

    @Test
    public void solvePart2() throws Exception {
        String input = "ULL\n" +
                "RRDDD\n" +
                "LURDL\n" +
                "UUUUD";

        assertEquals("5DB3", Day2.solvePart2(asList(input.split("\n"))));
    }

}