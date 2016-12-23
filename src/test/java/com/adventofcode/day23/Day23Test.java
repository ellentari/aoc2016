package com.adventofcode.day23;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day23Test {

    @Test
    public void testSolve() {
        String input = "cpy 2 a\n" +
                "tgl a\n" +
                "tgl a\n" +
                "tgl a\n" +
                "cpy 1 a\n" +
                "dec a\n" +
                "dec a";

        assertEquals(3, Day23.solve(input, 0));
    }

}