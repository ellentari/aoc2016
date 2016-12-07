package com.adventofcode.day7;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day7Test {

    @Test
    public void testSolvePart1() {
        assertEquals(1, Day7.solvePart1("abba[mnop]qrst"));
        assertEquals(0, Day7.solvePart1("abcd[bddb]xyyx"));
        assertEquals(0, Day7.solvePart1("xyyx[bddb]abcd"));
        assertEquals(0, Day7.solvePart1("aaaa[qwer]tyui"));
        assertEquals(1, Day7.solvePart1("ioxxoj[asdfgh]zxcvbn"));
    }

    @Test
    public void testSolvePart2(){
        assertEquals(1, Day7.solvePart2("aba[bab]xyz"));
        assertEquals(0, Day7.solvePart2("xyx[xyx]xyx"));
        assertEquals(1, Day7.solvePart2("aaa[kek]eke"));
        assertEquals(1, Day7.solvePart2("zazbz[bzb]cdb"));
    }

}