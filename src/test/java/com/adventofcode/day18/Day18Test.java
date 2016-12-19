package com.adventofcode.day18;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day18Test {

    @Test
    public void testSolvePart1() {
        assertEquals(6, Day18.solvePart1("..^^.", 3));
        assertEquals(38, Day18.solvePart1(".^^.^.^^^^", 10));
    }

}