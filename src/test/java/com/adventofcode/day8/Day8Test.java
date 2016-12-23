package com.adventofcode.day8;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class Day8Test {

    private static final int SCREEN_WIDTH = 50;
    private static final int SCREEN_HEIGHT = 6;

    @Test
    public void testSolvePart1() {
        String input = "rect 3x2\n" +
                "rotate column x=1 by 1\n" +
                "rotate row y=0 by 4\n" +
                "rotate column x=1 by 1\n";

        assertEquals(6, Day8.solvePart1(asList(input.split("\\n")), 7, 3));

    }

    @Test
    public void testSolvePart2() {
        String input = "rect 3x2\n" +
                "rotate column x=1 by 1\n" +
                "rotate row y=0 by 4\n" +
                "rotate column x=1 by 1\n";

        assertEquals(" #  # #\n" +
                        "# #    \n" +
                        " #     \n",
                Day8.solvePart2(asList(input.split("\\n")), 7, 3));
    }

    @Test
    public void solvePart1() {
        assertEquals(110, Day8.solvePart1(ResourceUtils.readLines("day8.txt"), SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    @Test
    public void solvePart2() {
        String expected =
                "####   ## #  # ###  #  #  ##  ###  #    #   #  ## \n" +
                "   #    # #  # #  # # #  #  # #  # #    #   #   # \n" +
                "  #     # #### #  # ##   #    #  # #     # #    # \n" +
                " #      # #  # ###  # #  #    ###  #      #     # \n" +
                "#    #  # #  # # #  # #  #  # #    #      #  #  # \n" +
                "####  ##  #  # #  # #  #  ##  #    ####   #   ##  \n";

        assertEquals(expected, Day8.solvePart2(ResourceUtils.readLines("day8.txt"), SCREEN_WIDTH, SCREEN_HEIGHT));
    }
}