package com.adventofcode.day6;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class Day6Test {

    @Test
    public void solvePart1() {
        String input = "eedadn\n" +
                "drvtee\n" +
                "eandsr\n" +
                "raavrd\n" +
                "atevrs\n" +
                "tsrnev\n" +
                "sdttsa\n" +
                "rasrtv\n" +
                "nssdts\n" +
                "ntnada\n" +
                "svetve\n" +
                "tesnvt\n" +
                "vntsnd\n" +
                "vrdear\n" +
                "dvrsen\n" +
                "enarar";

        assertEquals("easter", Day6.solvePart1(asList(input.split("\\n"))));
    }

    @Test
    public void solvePart2() {
        String input = "eedadn\n" +
                "drvtee\n" +
                "eandsr\n" +
                "raavrd\n" +
                "atevrs\n" +
                "tsrnev\n" +
                "sdttsa\n" +
                "rasrtv\n" +
                "nssdts\n" +
                "ntnada\n" +
                "svetve\n" +
                "tesnvt\n" +
                "vntsnd\n" +
                "vrdear\n" +
                "dvrsen\n" +
                "enarar";

        assertEquals("advent", Day6.solvePart2(asList(input.split("\\n"))));
    }

}