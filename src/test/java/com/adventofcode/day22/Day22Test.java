package com.adventofcode.day22;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day22Test {

    @Test
    public void testSolvePart2() {
        String input = "/dev/grid/node-x0-y0   10T    8T     2T   80%\n" +
                "/dev/grid/node-x0-y1   11T    6T     5T   54%\n" +
                "/dev/grid/node-x0-y2   32T   28T     4T   87%\n" +
                "/dev/grid/node-x1-y0    9T    7T     2T   77%\n" +
                "/dev/grid/node-x1-y1    8T    0T     8T    0%\n" +
                "/dev/grid/node-x1-y2   11T    7T     4T   63%\n" +
                "/dev/grid/node-x2-y0   10T    6T     4T   60%\n" +
                "/dev/grid/node-x2-y1    9T    8T     1T   88%\n" +
                "/dev/grid/node-x2-y2    9T    6T     3T   66%";

        assertEquals(7, Day22.solvePart2(input));
    }

    @Test
    public void solvePart1() {
        assertEquals(888, Day22.solvePart1(ResourceUtils.read("day22.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals(236, Day22.solvePart2(ResourceUtils.read("day22.txt")));
    }

}