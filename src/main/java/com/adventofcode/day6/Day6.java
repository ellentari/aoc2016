package com.adventofcode.day6;

import com.adventofcode.common.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Comparator.comparingInt;

/**
 * --- Day 6: Signals and Noise ---
 * <p>
 * Something is jamming your communications with Santa. Fortunately, your signal is only partially jammed,
 * and protocol in situations like this is to switch to a simple repetition code to get the message through.
 * <p>
 * In this model, the same message is sent repeatedly. You've recorded the repeating message
 * signal (your puzzle input), but the data seems quite corrupted - almost too badly to recover. Almost.
 * <p>
 * All you need to do is figure out which character is most frequent for each position. For example,
 * suppose you had recorded the following messages:
 * <p>
 * eedadn
 * drvtee
 * eandsr
 * raavrd
 * atevrs
 * tsrnev
 * sdttsa
 * rasrtv
 * nssdts
 * ntnada
 * svetve
 * tesnvt
 * vntsnd
 * vrdear
 * dvrsen
 * enarar
 * The most common character in the first column is e; in the second, a; in the third, s, and so on.
 * Combining these characters returns the error-corrected message, easter.
 * <p>
 * Given the recording in your puzzle input, what is the error-corrected version of the message being sent?
 * <p>
 * --- Part Two ---
 * <p>
 * Of course, that would be the message - if you hadn't agreed to use a modified repetition code instead.
 * <p>
 * In this modified code, the sender instead transmits what looks like random data, but for each character,
 * the character they actually want to send is slightly less likely than the others. Even after
 * signal-jamming noise, you can look at the letter distributions in each column and choose the least common
 * letter to reconstruct the original message.
 * <p>
 * In the above example, the least common character in the first column is a; in the second, d, and so on.
 * Repeating this process for the remaining characters produces the original message, advent.
 * <p>
 * Given the recording in your puzzle input and this new decoding methodology, what is the original message
 * that Santa is trying to send?
 */
public class Day6 {

    public static void main(String[] args) {
        List<String> input = ResourceUtils.readLines("day6.txt");

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    static String solvePart1(List<String> input) {
        return errorCorrected(input, LetterFrequency::getMostFrequent);
    }

    static String solvePart2(List<String> input) {
        return errorCorrected(input, LetterFrequency::getLeastFrequent);
    }
    
    private static String errorCorrected(List<String> input, Function<LetterFrequency, Character> toLetter) {
        return decode(getLettersFrequencies(input), toLetter);
    }

    private static List<LetterFrequency> getLettersFrequencies(List<String> input) {
        List<LetterFrequency> frequencies = new ArrayList<>();
        for (String line : input) {
            for (int i = 0; i < line.length(); i++) {
                if (frequencies.size() <= i) {
                    frequencies.add(new LetterFrequency());
                }
                frequencies.get(i).addLetter(line.charAt(i));
            }
        }
        return frequencies;
    }

    private static String decode(List<LetterFrequency> frequencies, Function<LetterFrequency, Character> toLetter) {
        StringBuilder result = new StringBuilder(frequencies.size());

        frequencies.stream().map(toLetter).forEach(result::append);

        return result.toString();
    }

    private static class LetterFrequency {

        private Map<Character, Integer> frequencies = new HashMap<>();

        void addLetter(char letter) {
            frequencies.put(letter, frequencies.getOrDefault(letter, 0) + 1);
        }

        char getMostFrequent() {
            return frequencies.entrySet().stream()
                    .max(comparingInt(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .orElseThrow(() -> new IllegalStateException("frequencies is empty"));
        }

        char getLeastFrequent() {
            return frequencies.entrySet().stream()
                    .min(comparingInt(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .orElseThrow(() -> new IllegalStateException("frequencies is empty"));
        }
    }

}
