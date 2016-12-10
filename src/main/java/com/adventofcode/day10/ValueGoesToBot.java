package com.adventofcode.day10;

public class ValueGoesToBot implements Instruction, Comparable<Instruction> {

    private Bot bot;
    private Chip chip;

    ValueGoesToBot(Bot bot, Chip chip) {
        this.bot = bot;
        this.chip = chip;
    }

    @Override
    public void execute() {
        bot.receive(chip);
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return String.format("value %s goes to %s", chip, bot);
    }
}
