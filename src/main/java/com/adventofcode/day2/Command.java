package com.adventofcode.day2;

enum Command {
    Up {
        @Override
        Keypad.Position nextPosition(Keypad.Position position) {
            return new Keypad.Position(position.i - 1, position.j);
        }
    },
    Down {
        @Override
        Keypad.Position nextPosition(Keypad.Position position) {
            return new Keypad.Position(position.i + 1, position.j);
        }
    },
    Left {
        @Override
        Keypad.Position nextPosition(Keypad.Position position) {
            return new Keypad.Position(position.i, position.j - 1);
        }
    },
    Right {
        @Override
        Keypad.Position nextPosition(Keypad.Position position) {
            return new Keypad.Position(position.i, position.j + 1);
        }
    };

    abstract Keypad.Position nextPosition(Keypad.Position position);

    static Command parse(char command) {
        for (Command value : values()) {
            if (value.name().charAt(0) == command) {
                return value;
            }
        }

        throw new IllegalArgumentException("Illegal command: " + command);
    }
}
