package com.adventofcode.day21.op;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class OpUtilsTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testIndexOf() {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        assertEquals(0, OpUtils.indexOf(arr, 'a'));
        assertEquals(1, OpUtils.indexOf(arr, 'b'));
        assertEquals(2, OpUtils.indexOf(arr, 'c'));
        assertEquals(3, OpUtils.indexOf(arr, 'd'));

        exception.expect(IllegalArgumentException.class);
        OpUtils.indexOf(arr, 'o');
    }

    @Test
    public void testRotateRight1() {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.rotateRight(arr, 3);

        assertArrayEquals(new char[] {'b', 'c', 'd', 'a'}, arr);
    }

    @Test
    public void testRotateRight2() {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.rotateRight(arr, 7);

        assertArrayEquals(new char[] {'b', 'c', 'd', 'a'}, arr);
    }

    @Test
    public void testRotateLeft1() {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.rotateLeft(arr, 3);

        assertArrayEquals(new char[] { 'd', 'a', 'b', 'c'}, arr);
    }

    @Test
    public void testRotateLeft3() {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.rotateLeft(arr, 7);

        assertArrayEquals(new char[] { 'd', 'a', 'b', 'c'}, arr);
    }

    @Test
    public void testSwap() {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.swap(arr, 1, 2);

        assertArrayEquals(new char[] {'a', 'c', 'b', 'd'}, arr);
    }

    @Test
    public void testReverse() throws Exception {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.reverse(arr, 0, 2);

        assertArrayEquals(new char[] {'c', 'b', 'a', 'd'}, arr);

        OpUtils.reverse(arr, 0, 2);

        assertArrayEquals(new char[] {'a', 'b', 'c', 'd'}, arr);
    }

    @Test
    public void testMove1() throws Exception {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.move(arr, 1, 3);

        assertArrayEquals(new char[] {'a', 'c', 'd', 'b'}, arr);

        OpUtils.move(arr, 3, 1);

        assertArrayEquals(new char[] {'a', 'b', 'c', 'd'}, arr);
    }

    @Test
    public void testMove2() throws Exception {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.move(arr, 1, 1);

        assertArrayEquals(new char[] {'a', 'b', 'c', 'd'}, arr);
    }

    @Test
    public void testMove3() throws Exception {
        char[] arr = new char[] {'a', 'b', 'c', 'd'};

        OpUtils.move(arr, 0, 3);

        assertArrayEquals(new char[] {'b', 'c', 'd', 'a'}, arr);

        OpUtils.move(arr, 3, 0);

        assertArrayEquals(new char[] {'a', 'b', 'c', 'd'}, arr);
    }

}