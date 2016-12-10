package com.adventofcode.day10;

import java.util.ArrayList;
import java.util.List;

class Output implements Recipient {

    private final int id;
    private List<Chip> chips = new ArrayList<>();

    Output(int id) {
        this.id = id;
    }

    public void receive(Chip chip) {
        chips.add(chip);
        System.out.println(this + " receives " + chip + ". Chips: " + chips);
    }

    Chip gerFirstChip() {
        return chips.stream().findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "output [" + id + "]";
    }
}
