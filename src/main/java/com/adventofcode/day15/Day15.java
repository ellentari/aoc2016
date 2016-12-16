package com.adventofcode.day15;

import com.adventofcode.common.ResourceUtils;
import com.adventofcode.common.TokenInfo;
import com.adventofcode.common.Tokenizer;

import java.util.List;
import java.util.regex.Matcher;

import static java.util.Collections.singletonList;

/**
 * --- Day 15: Timing is Everything ---
 * <p>
 * The halls open into an interior plaza containing a large kinetic sculpture.
 * The sculpture is in a sealed enclosure and seems to involve a set of identical spherical capsules
 * that are carried to the top and allowed to bounce through the maze of spinning pieces.
 * <p>
 * Part of the sculpture is even interactive! When a button is pressed, a capsule is dropped and tries
 * to fall through slots in a set of rotating discs to finally go through a little hole at the bottom and come out
 * of the sculpture. If any of the slots aren't aligned with the capsule as it passes, the capsule bounces off
 * the disc and soars away. You feel compelled to get one of those capsules.
 * <p>
 * The discs pause their motion each second and come in different sizes; they seem to each have a fixed number
 * of positions at which they stop. You decide to call the position with the slot 0, and count up for each position
 * it reaches next.
 * <p>
 * Furthermore, the discs are spaced out so that after you push the button, one second elapses before the first disc
 * is reached, and one second elapses as the capsule passes from one disk to the one below it. So, if you push
 * the button at time=100, then the capsule reaches the top disc at time=101, the second disc at time=102,
 * the third disc at time=103, and so on.
 * <p>
 * The button will only drop a capsule at an integer time - no fractional seconds allowed.
 * <p>
 * For example, at time=0, suppose you see the following arrangement:
 * <p>
 * Disc #1 has 5 positions; at time=0, it is at position 4.
 * Disc #2 has 2 positions; at time=0, it is at position 1.
 * If you press the button exactly at time=0, the capsule would start to fall; it would reach the first disc
 * at time=1. Since the first disc was at position 4 at time=0, by time=1 it has ticked one position forward.
 * As a five-position disc, the next position is 0, and the capsule falls through the slot.
 * <p>
 * Then, at time=2, the capsule reaches the second disc. The second disc has ticked forward
 * two positions at this point: it started at position 1, then continued to position 0, and finally
 * ended up at position 1 again. Because there's only a slot at position 0, the capsule bounces away.
 * <p>
 * If, however, you wait until time=5 to push the button, then when the capsule reaches each disc,
 * the first disc will have ticked forward 5+1 = 6 times (to position 0), and the second disc will have
 * ticked forward 5+2 = 7 times (also to position 0). In this case, the capsule would fall through the
 * discs and come out of the machine.
 * <p>
 * However, your situation has more than two discs; you've noted their positions in your puzzle input.
 * What is the first time you can press the button to get a capsule?
 * <p>
 * --- Part Two ---
 * <p>
 * After getting the first capsule (it contained a star! what great fortune!), the machine detects your success
 * and begins to rearrange itself.
 * <p>
 * When it's done, the discs are back in their original configuration as if it were time=0 again, but a new disc
 * with 11 positions and starting at position 0 has appeared exactly one second below the previously-bottom disc.
 * <p>
 * With this new disc, and counting again starting from time=0 with the configuration in your puzzle input,
 * what is the first time you can press the button to get another capsule?
 */
public class Day15 {

    private static final String DISC_REGEXP = "Disc #(\\d+) has (\\d+) positions; at time=0, it is at position (\\d+)\\.";

    public static void main(String[] args) {
        String input = ResourceUtils.read("day15.txt");

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    static int solvePart1(String input) {
        List<Disc> discs = parseDiscs(input);

        for (int n = 0; ; n++) {
            if (areAllDiscsAt0(discs, n)) {
                return n;
            }
        }
    }

    private static int solvePart2(String input) {
        List<Disc> discs = parseDiscs(input);
        discs.add(new Disc(0, discs.get(discs.size() - 1).number + 1,  11));

        for (int n = 0; ; n++) {
            if (areAllDiscsAt0(discs, n)) {
                return n;
            }
        }
    }

    private static List<Disc> parseDiscs(String input) {
        Tokenizer<Disc> tokenizer = new Tokenizer<>(singletonList(new TokenInfo<>(DISC_REGEXP, Day15::parseDisc)));

        return tokenizer.parse(input);
    }

    private static Disc parseDisc(Matcher matcher) {
        return new Disc(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(2))
        );
    }

    private static boolean areAllDiscsAt0(List<Disc> discs, int n) {
        return discs.stream().allMatch(disc -> disc.isAt0AfterNSeconds(n));
    }

    private static class Disc {
        final int number;
        final int startingPosition;
        final int positionsCount;

        Disc(int number, int startingPosition, int positionsCount) {
            this.number = number;
            this.startingPosition = startingPosition;
            this.positionsCount = positionsCount;
        }

        boolean isAt0AfterNSeconds(int n) {
            return (startingPosition + number + n) % positionsCount == 0;
        }

    }
}
