package com.adventofcode.day7;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day7Test {
    @Test
    public void solvePart1() {
        assertEquals(1, Day7.solvePart1("abba[mnop]qrst"));
        assertEquals(0, Day7.solvePart1("abcd[bddb]xyyx"));
        assertEquals(0, Day7.solvePart1("xyyx[bddb]abcd"));
        assertEquals(0, Day7.solvePart1("aaaa[qwer]tyui"));
        assertEquals(1, Day7.solvePart1("ioxxoj[asdfgh]zxcvbn"));
    }

    @Test
    public void solvePart1_2() {
        String input = "abba[mnop]qrst\n" +
                "abcd[bddb]xyyx\n" +
                "xyyx[bddb]abcd\n" +
                "aaaa[qwer]tyui\n" +
                "ioxxoj[asdfgh]zxcvbn\n";

        assertEquals(2, Day7.solvePart1(input));

    }

    @Test
    public void solvePart2(){
        String input = "";

        assertEquals("easter", Day7.solvePart2(input));
    }

}