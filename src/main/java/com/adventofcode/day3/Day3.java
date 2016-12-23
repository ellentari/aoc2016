package com.adventofcode.day3;

import com.adventofcode.common.ResourceUtils;

import java.util.List;

import static java.util.Arrays.stream;

/**
 * --- Day 3: Squares With Three Sides ---
 * <p>
 * Now that you can think clearly, you move deeper into the labyrinth of hallways and office furniture that makes
 * up this part of Easter Bunny HQ. This must be a graphic design department; the walls are covered in
 * specifications for triangles.
 * <p>
 * Or are they?
 * <p>
 * The design document gives the side lengths of each triangle it describes, but... 5 10 25? Some of these aren't
 * triangles. You can't help but mark the impossible ones.
 * <p>
 * In a valid triangle, the sum of any two sides must be larger than the remaining side. For example,
 * the "triangle" given above is impossible, because 5 + 10 is not larger than 25.
 * <p>
 * In your puzzle input, how many of the listed triangles are possible?
 * <p>
 * --- Part Two ---
 * <p>
 * Now that you've helpfully marked up their design documents, it occurs to you that triangles are specified
 * in groups of three vertically. Each set of three numbers in a column specifies a triangle. Rows are unrelated.
 * <p>
 * For example, given the following specification, numbers with the same hundreds digit would be part of the
 * same triangle:
 * <p>
 * 101 301 501
 * 102 302 502
 * 103 303 503
 * 201 401 601
 * 202 402 602
 * 203 403 603
 * <p>
 * In your puzzle input, and instead reading by columns, how many of the listed triangles are possible?
 */
public class Day3 {

    public static void main(String[] args) {
        List<String> input = ResourceUtils.readLines("day3.txt");

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    public static int solvePart1(List<String> input) {
        return (int) input.stream()
                .map(Day3::parseRow)
                .filter(Day3::isValidTriangle)
                .count();
    }

    public static int solvePart2(List<String> input) {
        int count = 0;

        for (int i = 0; i < input.size() - 2; i += 3) {
            int[] row1 = parseRow(input.get(i));
            int[] row2 = parseRow(input.get(i + 1));
            int[] row3 = parseRow(input.get(i + 2));

            for (int j = 0; j < 3; j++) {
                if (isValidTriangle(row1[j], row2[j], row3[j])) {
                    count++;
                }
            }
        }

        return count;
    }

    private static int[] parseRow(String row) {
        return stream(row.trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static boolean isValidTriangle(int[] triangleSides) {
        return isValidTriangle(triangleSides[0], triangleSides[1], triangleSides[2]);
    }

    private static boolean isValidTriangle(int a, int b, int c) {
        return a + b > c && b + c > a && a + c > b;
    }

}