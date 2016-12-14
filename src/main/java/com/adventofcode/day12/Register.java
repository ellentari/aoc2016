package com.adventofcode.day12;

public class Register {

    private final int[] registers;

    Register(int a, int b, int c, int d) {
        registers = new int[] { a, b, c, d };
    }

    Register() {
        this(0, 0, 0, 0);
    }

    public void set(Key key, int value) {
        this.registers[registerIdx(key)] = value;
    }

    public int get(Key key) {
        return this.registers[registerIdx(key)];
    }

    public void increment(Key key) {
        this.registers[registerIdx(key)]++;
    }

    public void decrement(Key key) {
        this.registers[registerIdx(key)]--;
    }

    private int registerIdx(Key key) {
        return key.key - 'a';
    }

    public static class Key {

        char key;

        Key(String key) {
            this(key.charAt(0));
        }

        Key(char key) {
            if (key < 'a' || key > 'd') {
                throw new IllegalArgumentException("Illegal register key: " + key);
            }
            this.key = key;
        }

        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }

}
