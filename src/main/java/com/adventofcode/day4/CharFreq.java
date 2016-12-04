package com.adventofcode.day4;

class CharFreq implements Comparable<CharFreq> {
    private final char ch;
    private final int freq;

    CharFreq(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "" + ch;
    }

    @Override
    public int compareTo(CharFreq o) {
        int compare = Integer.compare(o.freq, freq);
        if (compare != 0) {
            return compare;
        }
        return Character.compare(ch, o.ch);
    }
}
