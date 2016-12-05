package com.adventofcode.day6;

import com.adventofcode.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Comparator.comparingInt;

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
