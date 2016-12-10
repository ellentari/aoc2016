package com.adventofcode.day10;

import com.adventofcode.TokenInfo;
import com.adventofcode.Tokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static java.util.Arrays.asList;

class Day10Solver {

    private final int watchedLow;
    private final int watchedHigh;
    private final int[] outputs;

    private final String input;

    private Map<Integer, Bot> allBots = new HashMap<>();
    private Map<Integer, Output> allOutputs = new HashMap<>();

    private Bot botComparingWatchedChips;

    Day10Solver(String input, int watchedLow, int watchedHigh, int... outputs) {
        this.input = input;
        this.watchedLow = watchedLow;
        this.watchedHigh = watchedHigh;
        this.outputs = outputs;
    }

    void solve() {
        List<Instruction> instructions = parseInstructions();

        while (!instructions.isEmpty()) {
            Collections.sort(instructions);
            Instruction instruction = instructions.remove(0);
            System.out.println("Executing: " + instruction);
            instruction.execute();
        }
    }

    int getPart1Answer() {
        return botComparingWatchedChips.getId();
    }

    int getPart2Answer() {
        int multiplication = 1;

        for (int output : outputs) {
            multiplication *= allOutputs.get(output).gerFirstChip().getChip();
        }

        return multiplication;
    }

    private List<Instruction> parseInstructions() {
        ValueGoesToBotMapper valueGoesToBotMapper = new ValueGoesToBotMapper();
        TokenInfo<Instruction> valueGoesToBotTokenInfo = new TokenInfo<>(
                "value (?<chip>\\d+) goes to bot (?<bot>\\d+)",
                valueGoesToBotMapper::parse
        );

        BotGivesItsChipsMapper botGivesItsChipsMapper = new BotGivesItsChipsMapper();
        TokenInfo<Instruction> botGivesItsChipsTokenInfo = new TokenInfo<>(
                "bot (?<bot>\\d+) gives low to (?<lowRecipient>\\w+) (?<lowRecipientId>\\d+) " +
                        "and high to (?<highRecipient>\\w+) (?<highRecipientId>\\d+)",
                botGivesItsChipsMapper::parse
        );

        Tokenizer<Instruction> tokenizer = new Tokenizer<>(asList(valueGoesToBotTokenInfo, botGivesItsChipsTokenInfo));
        List<Instruction> instructions = new ArrayList<>();

        for (String line : input.split("\\n")) {
            instructions.addAll(tokenizer.parse(line));
        }

        return instructions;
    }

    private class BotGivesItsChipsMapper {
        Instruction parse(Matcher matcher) {
            String lowRecipient = matcher.group("lowRecipient");
            int lowRecipientId = Integer.parseInt(matcher.group("lowRecipientId"));

            Recipient lowRec = parseRecipient(lowRecipient, lowRecipientId);

            String highRecipient = matcher.group("highRecipient");
            int highRecipientId = Integer.parseInt(matcher.group("highRecipientId"));

            Recipient highRec = parseRecipient(highRecipient, highRecipientId);

            int bot = Integer.parseInt(matcher.group("bot"));
            allBots.putIfAbsent(bot, new Bot(bot));

            return new BotGivesItsChips(allBots.get(bot), lowRec, highRec, (b, chips) -> {
                if (chips[0] == watchedLow && chips[1] == watchedHigh) {
                    botComparingWatchedChips = b;
                }
            });
        }

        Recipient parseRecipient(String recipient, int recipientId) {
            Recipient rec;

            if ("bot".equals(recipient)) {
                allBots.putIfAbsent(recipientId, new Bot(recipientId));
                rec = allBots.get(recipientId);
            } else if ("output".equals(recipient)) {
                allOutputs.putIfAbsent(recipientId, new Output(recipientId));
                rec = allOutputs.get(recipientId);
            } else {
                throw new IllegalArgumentException("Illegal recipient to: " + recipient);
            }

            return rec;
        }
    }

    private class ValueGoesToBotMapper {
        Instruction parse(Matcher matcher) {
            int chip = Integer.parseInt(matcher.group("chip"));
            int bot = Integer.parseInt(matcher.group("bot"));

            allBots.putIfAbsent(bot, new Bot(bot));

            return new ValueGoesToBot(allBots.get(bot), new Chip(chip));
        }
    }
}
