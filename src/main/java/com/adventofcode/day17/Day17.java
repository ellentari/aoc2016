package com.adventofcode.day17;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.util.stream.Collectors.toList;

public class Day17 {

    private static final int MAZE_H = 4;
    private static final int MAZE_W = 4;

    public static void main(String[] args) {
        String input = "vkjiggvb";

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    static String solvePart1(String input) {
        int x = 0;
        int y = 0;

        Queue<Move> moves = new LinkedList<>();
        moves.addAll(possibleMoves(x, y, "", input));

        do {
            Move move = moves.poll();

            if (move.x == MAZE_H - 1 && move.y == MAZE_W - 1) {
                return move.path;
            }

            moves.addAll(possibleMoves(move.x, move.y, move.path, input));
        } while (!moves.isEmpty());

        return "no path";
    }

    private static List<Move> possibleMoves(int x, int y, String path, String input) {
        String hash = DigestUtils.md5Hex((input + path).getBytes());

        List<Move> moves = new ArrayList<>();
        moves.add(new Move(x + 1, y, "R", path + "R"));
        moves.add(new Move(x - 1, y, "L", path + "L"));
        moves.add(new Move(x, y + 1, "D", path + "D"));
        moves.add(new Move(x, y - 1, "U", path + "U"));

        return moves.stream().filter(move -> move.isValid(hash)).collect(toList());
    }

    static int solvePart2(String input) {
        int x = 0;
        int y = 0;

        Queue<Move> moves = new LinkedList<>();
        moves.addAll(possibleMoves(x, y, "", input));

        int longest = -1;

        do {
            Move move = moves.poll();

            if (move.x == MAZE_H - 1 && move.y == MAZE_W - 1) {
                longest = move.path.length();
            } else {
                moves.addAll(possibleMoves(move.x, move.y, move.path, input));
            }
        } while (!moves.isEmpty());

        return longest;
    }

    private static class Move {
        int x;
        int y;
        String direction;
        String path;

        Move(int x, int y, String direction, String path) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.path = path;
        }

        boolean isValid(String hash) {
            return x >= 0 && y >= 0 && x < MAZE_W && y < MAZE_H && isDoorOpened(hash);
        }

        private boolean isDoorOpened(String hash) {
            int i = hashIndex(direction);
            return Character.isLetter(hash.charAt(i)) && 'a' != hash.charAt(i);
        }

        private int hashIndex(String direction) {
            switch (direction) {
                case "U": return 0;
                case "D": return 1;
                case "L": return 2;
                case "R": return 3;
            }
            return -1;
        }
    }


}
