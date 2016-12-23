package com.adventofcode.day5;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class Day5Test {

    @Test
    public void testSolvePart1() throws Exception {
        assertEquals("18f47a30", Day5.solvePart1("abc"));
    }

    @Test
    public void testSolvePart2() throws Exception {
        assertEquals("05ace8e3", Day5.solvePart2("abc"));
    }

    @Test
    public void solvePart1() throws NoSuchAlgorithmException {
        assertEquals("4543c154", Day5.solvePart1("ojvtpuvg"));
    }

    @Test
    public void solvePart2() throws NoSuchAlgorithmException {
        assertEquals("1050cbbd", Day5.solvePart2("ojvtpuvg"));
    }
}