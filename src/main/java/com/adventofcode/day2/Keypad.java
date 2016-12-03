package com.adventofcode.day2;

class Keypad {

    private Character[][] keypad;

    Keypad(Character[][] keypad) {
        this.keypad = keypad;
    }

    boolean isValidCommand(Command command, Position current) {
        boolean valid = false;

        switch (command) {
            case Down: valid = isDownValid(current); break;
            case Up: valid = isUpValid(current); break;
            case Left: valid = isLeftValid(current); break;
            case Right: valid = isRightValid(current); break;
        }

        return valid;
    }

    private boolean isUpValid(Position pos) {
        return pos.i > 0 && keypad[pos.i - 1][pos.j] != null;
    }

    private boolean isDownValid(Position pos) {
        return pos.i < keypad.length - 1 && keypad[pos.i + 1][pos.j] != null;
    }

    private boolean isLeftValid(Position pos) {
        return pos.j > 0 && keypad[pos.i][pos.j - 1] != null;
    }

    private boolean isRightValid(Position pos) {
        return pos.j < keypad[pos.i].length - 1 && keypad[pos.i][pos.j + 1] != null;
    }

    Character at(Position position) {
        return keypad[position.i][position.j];
    }

    static class Position {
        final int i;
        final int j;

        Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
