package com.adventofcode.day1;

import com.adventofcode.ResourceUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

/**
 * --- Day 1: No Time for a Taxicab ---
 * <p>
 * You're airdropped near Easter Bunny Headquarters in a city somewhere.
 * "Near", unfortunately, is as close as you can get - the instructions on the Easter Bunny Recruiting Document
 * the Elves intercepted start here, and nobody had time to work them out further.
 * <p>
 * The Document indicates that you should start at the given position (where you just landed) and face North.
 * Then, follow the provided sequence: either turn left (L) or right (R) 90 degrees, then walk forward the given
 * number of blocks, ending at a new intersection.
 * <p>
 * There's no time to follow such ridiculous instructions on foot, though, so you take a moment and work out the
 * destination. Given that you can only walk on the street grid of the city,
 * how far is the shortest path to the destination?
 * <p>
 * For example:
 * <p>
 * - Following R2, L3 leaves you 2 blocks East and 3 blocks North, or 5 blocks away.
 * - R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
 * - R5, L5, R5, R3 leaves you 12 blocks away.
 * <p>
 * How many blocks away is Easter Bunny HQ?
 * <p>
 * --- Part Two ---
 * <p>
 * Then, you notice the instructions continue on the back of the Recruiting Document.
 * Easter Bunny HQ is actually at the first location you visit twice.
 * <p>
 * For example, if your instructions are R8, R4, R4, R8, the first location you visit twice is 4 blocks away, due East.
 * <p>
 * How many blocks away is the first location you visit twice?
 */
public class Day1 {

    public static void main(String[] args) {
        String input = ResourceUtils.read("day1.txt");

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    static int solvePart1(String input) {
        Coordinates initPos = new Coordinates(0, 0);
        Coordinates finalPos = makeMoves(initPos, parseMoves(input));

        return initPos.distance(finalPos);
    }

    private static Coordinates makeMoves(Coordinates initPos, Stream<Move> moves) {
        Person person = new Person(initPos, Direction.NORTH);

        moves.forEach(person::makeMove);

        return person.position;
    }

    static int solvePart2(String input) {
        Coordinates initialPos = new Coordinates(0, 0);
        Optional<Coordinates> finalPos = findFirstVisitedTwice(initialPos, parseMoves(input));

        return finalPos.isPresent() ? initialPos.distance(finalPos.get()) : -1;
    }

    private static Optional<Coordinates> findFirstVisitedTwice(Coordinates initial, Stream<Move> moves) {
        Person person = new Person(initial, Direction.NORTH);
        Set<Coordinates> visited = new HashSet<>();

        visited.add(initial);

        for (Move move : moves.collect(Collectors.toList())) {
            person.turn(move.direction);

            for (int i = 0; i < move.steps; i++) {
                person.move(1);

                if (visited.contains(person.position)) {
                    return Optional.of(person.position);
                }

                visited.add(person.position);
            }
        }

        return Optional.empty();
    }

    private static Stream<Move> parseMoves(String moves) {
        return stream(moves.split(", ")).map(Move::new);
    }

    static class Person {

        Coordinates position;
        private Direction facingDirection;

        Person(Coordinates initialPosition, Direction facingDirection) {
            this.position = initialPosition;
            this.facingDirection = facingDirection;
        }

        void makeMove(Move move) {
            turn(move.direction);
            move(move.steps);
        }

        void turn(Move.Direction direction) {
            facingDirection = direction.turn(this.facingDirection);
        }

        void move(int steps) {
            position = facingDirection.increment(position, steps);
        }
    }
}
