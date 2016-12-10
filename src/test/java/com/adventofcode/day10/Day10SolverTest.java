package com.adventofcode.day10;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day10SolverTest {

    @Test
    public void testSolve() {
        String input = "value 5 goes to bot 2\n" +
                "bot 2 gives low to bot 1 and high to bot 0\n" +
                "value 3 goes to bot 1\n" +
                "bot 1 gives low to output 1 and high to bot 0\n" +
                "bot 0 gives low to output 2 and high to output 0\n" +
                "value 2 goes to bot 2";

        Day10Solver day10Solver = new Day10Solver(input, 2, 5, 0, 1, 2);
        day10Solver.solve();

        assertEquals(2, day10Solver.getPart1Answer());
        assertEquals(30, day10Solver.getPart2Answer());
    }

}