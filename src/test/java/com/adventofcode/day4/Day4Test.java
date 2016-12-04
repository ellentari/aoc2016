package com.adventofcode.day4;

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

}