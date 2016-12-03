package com.adventofcode.day1;

enum Direction {

    NORTH {
        @Override
        Direction getRight() {
            return EAST;
        }

        @Override
        Direction getLeft() {
            return WEST;
        }

        @Override
        Coordinates increment(Coordinates coordinates, int value) {
            return coordinates.incY(value);
        }
    },

    SOUTH {
        @Override
        Direction getRight() {
            return WEST;
        }

        @Override
        Direction getLeft() {
            return EAST;
        }

        @Override
        Coordinates increment(Coordinates coordinates, int value) {
            return coordinates.incY(-value);
        }
    },

    EAST {
        @Override
        Direction getRight() {
            return SOUTH;
        }

        @Override
        Direction getLeft() {
            return NORTH;
        }

        @Override
        Coordinates increment(Coordinates coordinates, int value) {
            return coordinates.incX(value);
        }
    },

    WEST {
        @Override
        Direction getRight() {
            return NORTH;
        }

        @Override
        Direction getLeft() {
            return SOUTH;
        }

        @Override
        Coordinates increment(Coordinates coordinates, int value) {
            return coordinates.incX(-value);
        }
    };

    abstract Direction getRight();
    abstract Direction getLeft();
    abstract Coordinates increment(Coordinates coordinates, int value);
}
