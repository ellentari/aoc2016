package com.adventofcode.day21.op;

import java.util.Arrays;

class OpUtils {

    static int indexOf(char[] value, char ch) {
        for (int i = 0; i < value.length; i++) {
            if (value[i] == ch) {
                return i;
            }
        }

        throw new IllegalArgumentException("Letter " + ch + " not present in " + Arrays.toString(value));
    }

    static void rotateRight(char[] value, int times) {
        times = times % value.length;

        for (int i = 0; i < times; i++) {
            char last = value[value.length - 1];
            System.arraycopy(value, 0, value, 1, value.length - 1);
            value[0] = last;
        }
    }

    static void rotateLeft(char[] value, int times) {
        times = times % value.length;

        for (int i = 0; i < times; i++) {
            char first = value[0];
            System.arraycopy(value, 1, value, 0, value.length - 1);
            value[value.length - 1] = first;
        }
    }
    
    static void swap(char[] value, int x, int y) {
        char tmp = value[x];
        value[x] = value[y];
        value[y] = tmp;
    }
    
    static void reverse(char[] value, int x, int y) {
        while (x < y) {
            swap(value, x++, y--);
        }
    }

    static void move(char[] value, int x, int y) {
        char charAtX = value[x];
        System.arraycopy(value, x + 1, value, x, value.length - x - 1);
        System.arraycopy(value, y, value, y + 1, value.length - y - 1);
        value[y] = charAtX;
    }

    static boolean equals(char[] a1, char[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

}
