package com.adventofcode.day4;

import com.adventofcode.common.ResourceUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day4Test {

    @Test
    public void testSolvePart1() {
        assertEquals(0, Day4.solvePart1("totally-real-room-200[decoy]"));
        assertEquals(404, Day4.solvePart1("not-a-real-room-404[oarel]"));
        assertEquals(987, Day4.solvePart1("a-b-c-d-e-f-g-h-987[abcde]"));
        assertEquals(123, Day4.solvePart1("aaaaa-bbb-z-y-x-123[abxyz]"));
    }

    @Test
    public void testDecrypt() {
        Room room = new Room("qzmt-zixmtkozy-ivhz-343[any]");

        assertEquals("very encrypted name", room.decryptName());
    }

    @Test
    public void solvePart1() {
        assertEquals(137896, Day4.solvePart1(ResourceUtils.read("day4.txt")));
    }

    @Test
    public void solvePart2() {
        assertEquals(501, Day4.solvePart2(ResourceUtils.read("day4.txt")));
    }

}