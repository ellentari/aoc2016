package com.adventofcode.day24;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

/**
 * --- Day 24: Air Duct Spelunking ---
 * <p>
 * You've finally met your match; the doors that provide access to the roof are locked tight, and all of the controls
 * and related electronics are inaccessible. You simply can't reach them.
 * <p>
 * The robot that cleans the air ducts, however, can.
 * <p>
 * It's not a very fast little robot, but you reconfigure it to be able to interface with some of the exposed
 * wires that have been routed through the HVAC system. If you can direct it to each of those locations,
 * you should be able to bypass the security controls.
 * <p>
 * You extract the duct layout for this area from some blueprints you acquired and create a map with the relevant
 * locations marked (your puzzle input). 0 is your current location, from which the cleaning robot embarks;
 * the other numbers are (in no particular order) the locations the robot needs to visit at least once each.
 * Walls are marked as #, and open passages are marked as .. Numbers behave like open passages.
 * <p>
 * For example, suppose you have a map like the following:
 * <p>
 * ###########
 * #0.1.....2#
 * #.#######.#
 * #4.......3#
 * ###########
 * To reach all of the points of interest as quickly as possible, you would have the robot take the following path:
 * <p>
 * 0 to 4 (2 steps)
 * 4 to 1 (4 steps; it can't move diagonally)
 * 1 to 2 (6 steps)
 * 2 to 3 (2 steps)
 * Since the robot isn't very fast, you need to find it the shortest route. This path is the fewest steps
 * (in the above example, a total of 14) required to start at 0 and then visit every other location at least once.
 * <p>
 * Given your actual map, and starting from location 0, what is the fewest number of steps required to
 * visit every non-0 number marked on the map at least once?
 * <p>
 * --- Part Two ---
 * <p>
 * Of course, if you leave the cleaning robot somewhere weird, someone is bound to notice.
 * <p>
 * What is the fewest number of steps required to start at 0, visit every non-0 number marked on the map at least once,
 * and then return to 0?
 */
public class Day24 {

    public static int solvePart1(String input) {
        String[] rows = input.split("\\n");

        Map<Node, Map<Node, Integer>> shortestPaths = shortestPaths(rows);

        int shortestPath = Integer.MAX_VALUE;

        for (int[] path : new Permutation(range(1, shortestPaths.size()).toArray())) {
            int totalPathWeight = 0;

            // start from 0
            Node v0 = new Node(null, 0);
            for (int i = 0; i < path.length; i++) {
                Node v1 = new Node(null, path[i]);
                totalPathWeight += shortestPaths.get(v0).get(v1);
                v0 = v1;
            }

            if (totalPathWeight < shortestPath) {
                shortestPath = totalPathWeight;
            }
        }

        return shortestPath;
    }

    private static Map<Node, Map<Node, Integer>> shortestPaths(String[] rows) {
        Set<Node> vertices = new TreeSet<>();

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                char ch = rows[i].charAt(j);

                if (Character.isDigit(ch)) {
                    vertices.add(new Node(new IJ(i, j), Character.digit(ch, 10)));
                }
            }
        }


        Map<Node, Map<Node, Integer>> shortestPaths = new HashMap<>();

        for (Node vertex : vertices) {
            shortestPaths.put(vertex, new HashMap<>());
            Queue<Move> moves = new LinkedList<>();
            Set<IJ> visited = new HashSet<>();
            visited.add(vertex.ij);

            moves.addAll(possibleMoves(vertex.ij, 1, rows, visited));

            while (!moves.isEmpty()) {
                Move move = moves.poll();

                if (!visited.contains(move.ij)) {
                    visited.add(move.ij);
                    char ch = rows[move.ij.i].charAt(move.ij.j);
                    if (Character.isDigit(ch)) {
                        shortestPaths.get(vertex).put(new Node(move.ij, Character.digit(ch, 10)), move.step);
                    }

                    moves.addAll(possibleMoves(move.ij, move.step + 1, rows, visited));
                }
            }
        }

        return shortestPaths;
    }

    public static int solvePart2(String input) {
        String[] rows = input.split("\\n");

        Map<Node, Map<Node, Integer>> shortestPaths = shortestPaths(rows);

        int shortestPath = Integer.MAX_VALUE;

        for (int[] path : new Permutation(range(1, shortestPaths.size()).toArray())) {
            int totalPathWeight = 0;

            // start from 0
            Node v0 = new Node(null, 0);

            for (int i = 0; i < path.length; i++) {
                Node v1 = new Node(null, path[i]);
                totalPathWeight += shortestPaths.get(v0).get(v1);
                v0 = v1;
            }

            // return to 0
            totalPathWeight += shortestPaths.get(v0).get(new Node(null, 0));

            if (totalPathWeight < shortestPath) {
                shortestPath = totalPathWeight;
            }
        }

        return shortestPath;
    }

    private static List<Move> possibleMoves(IJ ij, int stepsAway, String[] rows, Set<IJ> visited) {
        List<Move> moves = new LinkedList<>();

        moves.add(new Move(ij.inc(1, 0), stepsAway));
        moves.add(new Move(ij.inc(0, 1), stepsAway));
        moves.add(new Move(ij.inc(-1, 0), stepsAway));
        moves.add(new Move(ij.inc(0, -1), stepsAway));

        return moves.stream().filter(move -> move.isValid(visited, rows)).collect(toList());

    }

    private static boolean isWall(String[] rows, IJ ij) {
        return '#' == rows[ij.i].charAt(ij.j);
    }

    private static class Move {
        final IJ ij;
        final int step;

        Move(IJ ij, int step) {
            this.ij = ij;
            this.step = step;
        }

        boolean isValid(Set<IJ> visited, String[] rows) {
            return !visited.contains(ij) && ij.isValid(rows) && !isWall(rows, ij);
        }
    }

    private static class IJ {
        final int i, j;

        IJ(int i, int j) {
            this.i = i;
            this.j = j;
        }

        IJ inc(int di, int dj) {
            return new IJ(i + di, j + dj);
        }

        boolean isValid(String[] rows) {
            return i >= 0 && i < rows.length && j >= 0 && j < rows[i].length();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IJ ij = (IJ) o;

            return i == ij.i && j == ij.j;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }

    private static class Node implements Comparable<Node> {
        final IJ ij;
        final int id;

        Node(IJ ij, int id) {
            this.ij = ij;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return id == node.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(id, o.id);
        }
    }
}
