package com.adventofcode.day9;

class Marker implements Token {

    private final int subSequenceLength;
    private final int repetitionsCount;

    private int decompressedRepetitionsCount;

    private boolean asSequence;

    private Marker marker;

    Marker(int subSequenceLength, int repetitionsCount) {
        this.subSequenceLength = subSequenceLength;
        this.repetitionsCount = repetitionsCount;
        this.decompressedRepetitionsCount = repetitionsCount;
    }

    @Override
    public void decompress(Marker marker) {
        if (!asSequence) {
            decompressedRepetitionsCount *= marker.repetitionsCount;
        } else {
            this.marker = marker;
        }
    }

    @Override
    public int getLength() {
        return toString().length();
    }

    @Override
    public long getDecompressedLength() {
        if (toString().length() < marker.getSubSequenceLength()) {
            return (long) marker.getDecompressedRepetitionsCount() * toString().length();
        } else {
            return (long) marker.getDecompressedRepetitionsCount() * marker.getSubSequenceLength()
                    + toString().length() - marker.getSubSequenceLength();
        }
    }

    @Override
    public final String toString() {
        return String.format("(%dx%d)", subSequenceLength, repetitionsCount);
    }

    int getSubSequenceLength() {
        return subSequenceLength;
    }

    int getRepetitionsCount() {
        return repetitionsCount;
    }

    int getDecompressedRepetitionsCount() {
        return decompressedRepetitionsCount;
    }

    void setAsSequence() {
        this.asSequence = true;
    }

    boolean isAsSequence() {
        return asSequence;
    }

}
