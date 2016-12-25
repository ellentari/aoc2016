package com.adventofcode.day25;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day25Test {
    @Test
    public void testSolvePart1() throws Exception {
        assertEquals(158, Day25.solvePart1(ResourceUtils.read("day25.txt")));
    }

}