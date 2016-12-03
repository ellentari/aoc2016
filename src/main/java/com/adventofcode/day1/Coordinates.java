package com.adventofcode.day1;

import java.util.Objects;

class Coordinates {
    private final int x;
    private final int y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coordinates incX(int value) {
        return new Coordinates(x + value, y);
    }

    Coordinates incY(int value) {
        return new Coordinates(x, y + value);
    }

    int distance(Coordinates other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", x, y);
    }
}
