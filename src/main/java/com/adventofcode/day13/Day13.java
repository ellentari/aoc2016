package com.adventofcode.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * --- Day 13: A Maze of Twisty Little Cubicles ---
 * <p>
 * You arrive at the first floor of this new building to discover a much less welcoming environment than
 * the shiny atrium of the last one. Instead, you are in a maze of twisty little cubicles, all alike.
 * <p>
 * Every location in this area is addressed by a pair of non-negative integers (x,y). Each such coordinate
 * is either a wall or an open space. You can't move diagonally. The cube maze starts at 0,0 and seems to extend
 * infinitely toward positive x and y; negative values are invalid, as they represent a location outside the building.
 * You are in a small waiting area at 1,1.
 * <p>
 * While it seems chaotic, a nearby morale-boosting poster explains, the layout is actually quite logical.
 * You can determine whether a given x,y coordinate will be a wall or an open space using a simple system:
 * <p>
 * Find x*x + 3*x + 2*x*y + y + y*y.
 * Add the office designer's favorite number (your puzzle input).
 * Find the binary representation of that sum; count the number of bits that are 1.
 * If the number of bits that are 1 is even, it's an open space.
 * If the number of bits that are 1 is odd, it's a wall.
 * For example, if the office designer's favorite number were 10, drawing walls as # and open spaces as .,
 * the corner of the building containing 0,0 would look like this:
 * <p>
 * 0123456789
 * 0 .#.####.##
 * 1 ..#..#...#
 * 2 #....##...
 * 3 ###.#.###.
 * 4 .##..#..#.
 * 5 ..##....#.
 * 6 #...##.###
 * Now, suppose you wanted to reach 7,4. The shortest route you could take is marked as O:
 * <p>
 * 0123456789
 * 0 .#.####.##
 * 1 .O#..#...#
 * 2 #OOO.##...
 * 3 ###O#.###.
 * 4 .##OO#OO#.
 * 5 ..##OOO.#.
 * 6 #...##.###
 * Thus, reaching 7,4 would take a minimum of 11 steps (starting from your current location, 1,1).
 * <p>
 * What is the fewest number of steps required for you to reach 31,39?
 * <p>
 * --- Part Two ---
 * <p>
 * How many locations (distinct x,y coordinates, including your starting location) can you reach in at most 50 steps?
 */
public class Day13 {

    private static final int INPUT = 1364;
    private static final int X = 31;
    private static final int Y = 39;
    private static final int MAX_STEPS_AWAY = 50;

    public static void main(String[] args) {
        System.out.println(solvePart1(INPUT, new XY(X, Y)));
        System.out.println(solvePart2(INPUT, MAX_STEPS_AWAY));
    }

    static int solvePart1(int input, XY xy) {
        Queue<Coordinate> toVisit = new LinkedList<>();
        Set<XY> visited = new HashSet<>();

        Coordinate current = new Coordinate(new XY(1, 1), 0);
        toVisit.add(current);

        do {
            current = toVisit.poll();
            if (!visited.contains(current.xy)) {
                visited.add(current.xy);
                toVisit.addAll(getReachableFrom(current, visited, input));
            }
        } while (!current.xy.equals(xy));

        return current.stepsAway;
    }

    private static int solvePart2(int input, int maxStepsAway) {
        Queue<Coordinate> toVisit = new LinkedList<>();
        Set<XY> visited = new HashSet<>();

        Coordinate current = new Coordinate(new XY(1, 1), 0);
        toVisit.add(current);

        do {
            current = toVisit.poll();
            if (!visited.contains(current.xy)) {
                visited.add(current.xy);
                toVisit.addAll(getReachableFrom(current, visited, input));
            }
        } while (current.stepsAway < maxStepsAway);

        return visited.size();
    }

    private static List<Coordinate> getReachableFrom(Coordinate from, Set<XY> visited, int input) {
        List<Coordinate> coordinates = new ArrayList<>();

        int stepsAway = from.stepsAway + 1;
        coordinates.add(new Coordinate(from.xy.inc(1, 0), stepsAway));
        coordinates.add(new Coordinate(from.xy.inc(-1, 0), stepsAway));
        coordinates.add(new Coordinate(from.xy.inc(0, 1), stepsAway));
        coordinates.add(new Coordinate(from.xy.inc(0, -1), stepsAway));

        return coordinates.stream()
                .filter(coordinate -> coordinate.isValid(visited, input))
                .collect(toList());
    }

    private static boolean isWall(XY xy, int input) {
        int result = xy.x * xy.x + 3 * xy.x + 2 * xy.x * xy.y + xy.y + xy.y * xy.y + input;

        return Integer.bitCount(result) % 2 != 0;
    }

    private static class Coordinate {
        final XY xy;
        final int stepsAway;

        Coordinate(XY xy, int stepsAway) {
            this.xy = xy;
            this.stepsAway = stepsAway;
        }

        boolean isValid(Set<XY> visited, int input) {
            return xy.isValid() && !visited.contains(xy) && !isWall(xy, input);
        }
    }

    static class XY {
        final int x;
        final int y;

        XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isValid() {
            return x >=0 && x >= 0;
        }

        XY inc(int dx, int dy) {
            return new XY(x + dx, y + dy);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            XY xy = (XY) o;

            return x == xy.x && y == xy.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
