package com.adventofcode.day8;

class ScreenCommand {

    private final Command command;
    private final int[] args;

    ScreenCommand(String command) {
        this.command = Command.parse(command);
        this.args = this.command.parseArgs(command);
    }

    void execute(Screen screen) {
        command.execute(screen, args);
    }
}
