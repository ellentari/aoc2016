package com.adventofcode.day10;

import java.util.function.BiConsumer;

public class BotGivesItsChips implements Instruction {

    private Bot bot;
    private Recipient lowRecipient;
    private Recipient highRecipient;

    private BiConsumer<Bot, int[]> onChipsGiven;

    BotGivesItsChips(Bot bot, Recipient lowRecipient, Recipient highRecipient, BiConsumer<Bot, int[]> onChipsGiven) {
        this.bot = bot;
        this.lowRecipient = lowRecipient;
        this.highRecipient = highRecipient;
        this.onChipsGiven = onChipsGiven;
    }

    @Override
    public void execute() {
        int[] chips = bot.giveChips(lowRecipient, highRecipient);
        onChipsGiven.accept(bot, chips);
    }

    @Override
    public int getPriority() {
        return bot.getChipsCount();
    }

    @Override
    public String toString() {
        return String.format("%s gives low to %s and high to %s", bot, lowRecipient, highRecipient);
    }
}
