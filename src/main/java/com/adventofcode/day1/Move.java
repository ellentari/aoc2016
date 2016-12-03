package com.adventofcode.day1;

class Move {

    final Direction direction;
    final int steps;

    Move(String move) {
        direction = Direction.valueOf(move.substring(0, 1));
        steps = Integer.parseInt(move.substring(1));
    }

    enum Direction {
        R {
            @Override
            com.adventofcode.day1.Direction turn(com.adventofcode.day1.Direction from) {
                return from.getRight();
            }
        },

        L {
            @Override
            com.adventofcode.day1.Direction turn(com.adventofcode.day1.Direction from) {
                return from.getLeft();
            }
        };

        abstract com.adventofcode.day1.Direction turn(com.adventofcode.day1.Direction from);
    }
}
