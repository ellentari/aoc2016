package com.adventofcode.day17;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day17Test {

    @Test
    public void testSolvePart1() {
        assertEquals("no path", Day17.solvePart1("hijkl"));
        assertEquals("DDRRRD", Day17.solvePart1("ihgpwlah"));
        assertEquals("DDUDRLRRUDRD", Day17.solvePart1("kglvqrro"));
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", Day17.solvePart1("ulqzkmiv"));
    }

    @Test
    public void testSolvePart2() {
        assertEquals(370, Day17.solvePart2("ihgpwlah"));
        assertEquals(492, Day17.solvePart2("kglvqrro"));
        assertEquals(830, Day17.solvePart2("ulqzkmiv"));
    }

}