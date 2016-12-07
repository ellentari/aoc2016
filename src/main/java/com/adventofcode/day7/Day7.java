package com.adventofcode.day7;

import com.adventofcode.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Comparator.comparingInt;

public class Day7 {

    public static void main(String[] args) {
        String input = ResourceUtils.read("day7.txt");

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    static int solvePart1(String input) {
        Pattern p1 = Pattern.compile("(\\w)(\\w)\\2\\1");
//        Pattern p2 = Pattern.compile("\\[.*?(.)(.)\\2\\1.*?]");

        int count = 0;

        o: for (String line : input.split("\\n")) {
            line = line.trim();
            Matcher m1 = p1.matcher(line);
            List<int[]> brackets = brackets(line);
            boolean found = false;
            while (m1.find() && !m1.group(1).equals(m1.group(2))) {
                found = true;
//                System.out.println(line);
                System.out.println("found: " + m1.group());
                if (isInBrackets(m1, brackets)) {
                    System.out.println("not support:" + line);
                    continue o;
                }
            }

            if (found) {
                System.out.println("supports:" + line);
                count++;
            }
        }

        return count;
    }

    static List<int[]> brackets(String input) {
        List<int[]> brackets = new ArrayList<>();
        int[] curr = new int[2];
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '[') {
                curr = new int[2];
                curr[0] = i;
            } else if (input.charAt(i) == ']') {
                curr[1] = i;
                brackets.add(curr);
            }
        }
        return brackets;
    }

    static boolean isInBrackets(Matcher m, List<int[]> brackets) {
        for (int[] bracket : brackets) {
            int start = m.start();
            int end = m.end();

            if (bracket[0] < start && end - 1 < bracket[1] ) {
                return true;
            }
        }

        return false;
    }

    static int solvePart2(String input) {
        Pattern p1 = Pattern.compile("(\\w)(\\w)\\1");
//        Pattern p2 = Pattern.compile("\\[.*?(.)(.)\\2\\1.*?]");

        int count = 0;

        o: for (String line : input.split("\\n")) {
            line = line.trim();
            Matcher m1 = p1.matcher(line);

            List<int[]> brackets = brackets(line);

            while (m1.find() && !m1.group(1).equals(m1.group(2))) {
                if (!isInBrackets(m1, brackets)) {
                    Pattern p2 = Pattern.compile(m1.group(2) + m1.group(1) + m1.group(2));
                    Matcher m2 = p2.matcher(line);

                    if (m2.find() && isInBrackets(m2, brackets)) {
                        count++;
                        continue o;
                    }
                }

            }

        }

        return count;
    }

}
