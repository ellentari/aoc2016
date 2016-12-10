package com.adventofcode.day9;

public class Sequence implements Token {

    private final String sequence;
    private Marker marker;

    Sequence(String sequence) {
        this.sequence = sequence;
        this.marker = new Marker(sequence.length(), 1);
    }

    @Override
    public void decompress(Marker marker) {
        this.marker = marker;
    }

    @Override
    public int getLength() {
        return sequence.length();
    }

    @Override
    public long getDecompressedLength() {
        if (sequence.length() < marker.getSubSequenceLength()) {
            return (long) marker.getDecompressedRepetitionsCount() * sequence.length();
        } else {
            return (long) marker.getDecompressedRepetitionsCount() * marker.getSubSequenceLength()
                    + sequence.length() - marker.getSubSequenceLength();
        }
    }

    @Override
    public String toString() {
        return sequence;
    }

}
