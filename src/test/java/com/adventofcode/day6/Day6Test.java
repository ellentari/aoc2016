package com.adventofcode.day6;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Day6Test {

    @Test
    public void testSolvePart1() {
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
    public void testSolvePart2() {
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

    @Test
    public void solvePart1() {
        assertEquals("qtbjqiuq", Day6.solvePart1(ResourceUtils.readLines("day6.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals("akothqli", Day6.solvePart2(ResourceUtils.readLines("day6.txt")));
    }

}