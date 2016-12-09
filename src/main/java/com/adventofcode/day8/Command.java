package com.adventofcode.day8;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

public enum Command {

    rect("rect") {
        @Override
        void execute(Screen screen, int[] args) {
            screen.rectangle(args[0], args[1]);
        }

        @Override
        int[] parseArgs(String command) {
            String[] split = command.split("[ x]");

            return new int[] { parseInt(split[1]), parseInt(split[2]) };
        }
    },

    rotateRow("rotate row") {
        @Override
        void execute(Screen screen, int[] args) {
            screen.rotateRow(args[0], args[1]);
        }

        @Override
        int[] parseArgs(String command) {
            return parseRotateArgs(command);
        }
    },

    rotateColumn("rotate column") {
        @Override
        void execute(Screen screen, int[] args) {
            screen.rotateColumn(args[0], args[1]);
        }

        @Override
        int[] parseArgs(String command) {
            return parseRotateArgs(command);
        }
    };

    private final String name;

    Command(String name) {
        this.name = name;
    }

    abstract void execute(Screen screen, int[] args);
    abstract int[] parseArgs(String command);

    static Command parse(String command) {
        return stream(values())
                .filter(value -> command.startsWith(value.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(command));
    }

    private static int[] parseRotateArgs(String command) {
        String[] split = command.split("[ =]");

        return new int[] { parseInt(split[3]), parseInt(split[5]) };
    }
}
