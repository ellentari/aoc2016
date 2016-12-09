package com.adventofcode.day8;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class Day8Test {

    @Test
    public void solvePart1() {
        String input = "rect 3x2\n" +
                "rotate column x=1 by 1\n" +
                "rotate row y=0 by 4\n" +
                "rotate column x=1 by 1\n";

        assertEquals(6, Day8.solvePart1(asList(input.split("\\n")), 7, 3));

    }

    @Test
    public void solvePart2() {
        String input = "rect 3x2\n" +
                "rotate column x=1 by 1\n" +
                "rotate row y=0 by 4\n" +
                "rotate column x=1 by 1\n";

        assertEquals(" #  # #\n" +
                        "# #    \n" +
                        " #     \n",
                Day8.solvePart2(asList(input.split("\\n")), 7, 3));
    }

}