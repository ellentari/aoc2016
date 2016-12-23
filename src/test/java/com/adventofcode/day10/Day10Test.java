package com.adventofcode.day10;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10Test {

    @Test
    public void solvePart1() {
        assertEquals(161, Day10.solvePart1(ResourceUtils.read("day10.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals(133163, Day10.solvePart2(ResourceUtils.read("day10.txt")));
    }

}