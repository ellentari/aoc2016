package com.adventofcode.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 {

    private static final  int INPUT = 1364;

    private static final int TARGET_X = 31;
    private static final int TARGET_Y = 39;

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        Coord current = new Coord(1, 1, 0);

        Set<XY> visited = new HashSet<>();

        PriorityQueue<Coord> coords = new PriorityQueue<>();
        coords.addAll(getReachable(current, visited));

        visited.add(current.xy);

        while (!current.is(TARGET_X, TARGET_Y)) {
            current = coords.poll();
            if (!visited.contains(current.xy)) {
//                System.out.println(current.x + " : " + current.y + "; " + current.stepsAway);
                visited.add(current.xy);
                coords.addAll(getReachable(current, visited));
            }
        }

        System.out.println(current.stepsAway);
    }

    private static void part2() {
        Coord current = new Coord(1, 1, 0);

        Set<XY> visited = new HashSet<>();

        PriorityQueue<Coord> coords = new PriorityQueue<>();
        coords.addAll(getReachable(current, visited));

        visited.add(current.xy);

        while (current.stepsAway < 50) {
            current = coords.poll();
            if (!visited.contains(current.xy)) {
//                System.out.println(current.x + " : " + current.y + "; " + current.stepsAway);
                visited.add(current.xy);
                coords.addAll(getReachable(current, visited));
            }
        }

        System.out.println(visited.size());
    }

    private static List<Coord> getReachable(Coord from, Set<XY> visited) {
        List<Coord> coords = new ArrayList<>();

        coords.add(new Coord(from.x + 1, from.y, from.stepsAway + 1));
        coords.add(new Coord(from.x - 1, from.y, from.stepsAway + 1));
        coords.add(new Coord(from.x, from.y + 1, from.stepsAway + 1));
        coords.add(new Coord(from.x, from.y - 1, from.stepsAway + 1));

        return coords.stream().filter(coord -> coord.isValid(visited)).collect(Collectors.toList());
    }

    private static boolean isWall(int x, int y) {
        int t = x*x + 3*x + 2*x*y + y + y*y + INPUT;
        return Integer.bitCount(t) % 2 != 0;
    }

    private static class Coord implements Comparable<Coord> {
        final int x;
        final int y;
        final XY xy;

        final int stepsAway;

        Coord(int x, int y, int stepsAway) {
            this.x = x;
            this.y = y;
            this.stepsAway = stepsAway;
            this.xy = new XY(x, y);
        }

        boolean isValid(Set<XY> visited) {
            return x >=0 && y >=0 && !isWall(x, y) && !visited.contains(xy);
        }

        boolean is(int x, int y) {
            return this.x == x && this.y == y;
        }

        @Override
        public int compareTo(Coord o) {
            return Integer.compare(stepsAway, o.stepsAway);
        }
    }

    private static class XY {
        final int x;
        final int y;

        XY(int x, int y) {
            this.x = x;
            this.y = y;
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
