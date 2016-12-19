package com.adventofcode;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 19: An Elephant Named Joseph ---
 * <p>
 * The Elves contact you over a highly secure emergency channel. Back at the North Pole,
 * the Elves are busy misunderstanding White Elephant parties.
 * <p>
 * Each Elf brings a present. They all sit in a circle, numbered starting with position 1.
 * Then, starting with the first Elf, they take turns stealing all the presents from the Elf to their left.
 * An Elf with no presents is removed from the circle and does not take turns.
 * <p>
 * For example, with five Elves (numbered 1 to 5):
 * <p>
 * 1
 * 5   2
 * 4 3
 * Elf 1 takes Elf 2's present.
 * Elf 2 has no presents and is skipped.
 * Elf 3 takes Elf 4's present.
 * Elf 4 has no presents and is also skipped.
 * Elf 5 takes Elf 1's two presents.
 * Neither Elf 1 nor Elf 2 have any presents, so both are skipped.
 * Elf 3 takes Elf 5's three presents.
 * So, with five Elves, the Elf that sits starting in position 3 gets all the presents.
 * <p>
 * With the number of Elves given in your puzzle input, which Elf gets all the presents?
 * <p>
 * --- Part Two ---
 * <p>
 * Realizing the folly of their present-exchange rules, the Elves agree to instead steal presents
 * from the Elf directly across the circle. If two Elves are across the circle, the one on the left
 * (from the perspective of the stealer) is stolen from. The other rules remain unchanged: Elves with
 * no presents are removed from the circle entirely, and the other elves move in slightly to keep the
 * circle evenly spaced.
 * <p>
 * For example, with five Elves (again numbered 1 to 5):
 * <p>
 * The Elves sit in a circle; Elf 1 goes first:
 * 1
 * 5   2
 * 4 3
 * Elves 3 and 4 are across the circle; Elf 3's present is stolen, being the one to the left. Elf 3 leaves
 * the circle, and the rest of the Elves move in:
 * 1           1
 * 5   2  -->  5   2
 * 4 -          4
 * Elf 2 steals from the Elf directly across the circle, Elf 5:
 * 1         1
 * -   2  -->     2
 * 4         4
 * Next is Elf 4 who, choosing between Elves 1 and 2, steals from Elf 1:
 * -          2
 * 2  -->
 * 4          4
 * Finally, Elf 2 steals from Elf 4:
 * 2
 * -->  2
 * -
 * So, with five Elves, the Elf that sits starting in position 2 gets all the presents.
 * <p>
 * With the number of Elves given in your puzzle input, which Elf now gets all the presents?
 */
public class Day19 {

    public static void main(String[] args) {
        int input = 3014387;

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    /**
     * @param n the number of people standing in the circle
     * @return the safe position who will survive the execution
     * f(N) = 2L + 1 where N =2^M + L and 0 <= L < 2^M
     */
    private static int solvePart1(int n) {
        int valueOfL = n - Integer.highestOneBit(n);
        int safePosition = 2 * valueOfL + 1;

        return safePosition;
    }

    private static int solvePart2(int n) {
        List<Integer> elves = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            elves.add(i);
        }

        int first = 0;
        while (elves.size() != 1) {
            int nMinusOne = n - 1;
            int index = (first + nMinusOne / 2 + nMinusOne % 2) % n;
            elves.remove(index);
            first = index >= first ? (first + 1) % n : first % nMinusOne;
            n--;
        }

        return elves.get(0);
    }
}
