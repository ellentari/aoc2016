package com.adventofcode.day10;

import java.util.PriorityQueue;

class Bot implements Recipient {

    private final int id;
    private final PriorityQueue<Chip> chips = new PriorityQueue<>();

    Bot(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    int[] giveChips(Recipient lowTo, Recipient highTo) {
        Chip low = chips.poll();
        Chip high = chips.poll();

        lowTo.receive(low);
        highTo.receive(high);

        return new int[] { low.getChip(), high.getChip() };
    }

    int getChipsCount() {
        return chips.size();
    }

    @Override
    public void receive(Chip chip) {
        chips.add(chip);
        System.out.println(this + " receives " + chip + ". Chips: " + chips);
    }

    @Override
    public String toString() {
        return "bot [" + id + ']';
    }
}
