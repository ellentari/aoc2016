package com.adventofcode.day20;

import com.adventofcode.common.TokenInfo;
import com.adventofcode.common.Tokenizer;

import java.util.PriorityQueue;
import java.util.regex.Matcher;

import static java.util.Collections.singletonList;

/**
 * --- Day 20: Firewall Rules ---
 * <p>
 * You'd like to set up a small hidden computer here so you can use it to get back into the network later.
 * However, the corporate firewall only allows communication with certain external IP addresses.
 * <p>
 * You've retrieved the list of blocked IPs from the firewall, but the list seems to be messy and poorly maintained,
 * and it's not clear which IPs are allowed. Also, rather than being written in dot-decimal notation,
 * they are written as plain 32-bit integers, which can have any value from 0 through 4294967295, inclusive.
 * <p>
 * For example, suppose only the values 0 through 9 were valid, and that you retrieved the following blacklist:
 * <p>
 * 5-8
 * 0-2
 * 4-7
 * The blacklist specifies ranges of IPs (inclusive of both the start and end value) that are not allowed. Then,
 * the only IPs that this firewall allows are 3 and 9, since those are the only numbers not in any range.
 * <p>
 * Given the list of blocked IPs you retrieved from the firewall (your puzzle input),
 * what is the lowest-valued IP that is not blocked?
 * <p>
 * --- Part Two ---
 * <p>
 * How many IPs are allowed by the blacklist?
 */
public class Day20 {

    public static long solvePart1(String input, long ipTo) {
        PriorityQueue<Range> ranges = parseRanges(input);

        Range first = new Range(0, 0);
        while (!ranges.isEmpty()) {
            first.end = Math.max(first.end, ranges.poll().end);

            if (ranges.peek() != null && first.end < ranges.peek().start - 1) {
                return first.end + 1;
            }
        }

        return first.end < ipTo ? first.end + 1 : -1;
    }

    public static long solvePart2(String input, long ipTo) {
        PriorityQueue<Range> ranges = parseRanges(input);
        int validIpsCount = 0;

        Range first = new Range(0, 0);
        while (!ranges.isEmpty()) {
            first.end = Math.max(first.end, ranges.poll().end);
            Range second = ranges.peek();

            if (second != null && first.end < second.start - 1) {
                validIpsCount += second.start - first.end - 1;
            }
        }

        validIpsCount += ipTo - first.end;

        return validIpsCount;
    }

    private static PriorityQueue<Range> parseRanges(String input) {
        Tokenizer<Range> tokenizer = new Tokenizer<>(singletonList(new TokenInfo<>("(\\d+)-(\\d+)",
                Day20::parseRange)));

        PriorityQueue<Range> rangesQueue = new PriorityQueue<>();

        tokenizer.parse(input, rangesQueue);

        return rangesQueue;
    }

    private static Range parseRange(Matcher matcher) {
        return new Range(Long.parseLong(matcher.group(1)), Long.parseLong(matcher.group(2)));
    }

    private static class Range implements Comparable<Range> {
        long start, end;

        Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Range o) {
            int startCmp = Long.compare(start, o.start);
            if (startCmp != 0) {
                return startCmp;
            }
            return Long.compare(end, o.end);
        }
    }
}
