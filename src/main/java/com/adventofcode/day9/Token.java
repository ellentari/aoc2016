package com.adventofcode.day9;

interface Token {

    void decompress(Marker marker);

    int getLength();

    long getDecompressedLength();

}
