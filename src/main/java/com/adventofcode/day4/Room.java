package com.adventofcode.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

class Room {

    private final String name;
    private final int sectorId;
    private final String checkSum;

    private static final int CHECK_SUM_LENGTH = 5;
    private static final int LETTERS_COUNT = 26;
    private static Pattern p = Pattern.compile("(.*?)-(\\d+)\\[(.*?)]");

    Room(String room) {
        Matcher matcher = p.matcher(room);
        if (matcher.find()) {
            name = matcher.group(1);
            sectorId = Integer.parseInt(matcher.group(2));
            checkSum = matcher.group(3);
        } else {
            throw new IllegalArgumentException(room);
        }
    }

    int getSectorId() {
        return sectorId;
    }

    boolean isReal() {
        return checkSum().equals(checkSum);
    }

    private String checkSum() {
        return checkSum(nameLettersFreq());
    }

    private Map<Character, Integer> nameLettersFreq() {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (ch != '-') {
                freq.putIfAbsent(ch, 0);
                freq.put(ch, freq.get(ch) + 1);
            }
        }
        return freq;
    }

    private String checkSum(Map<Character, Integer> freq) {
        return freq.entrySet().stream()
                .map(e -> new CharFreq(e.getKey(), e.getValue()))
                .sorted()
                .map(CharFreq::toString)
                .limit(CHECK_SUM_LENGTH)
                .collect(joining());
    }

    String decryptName() {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (ch == '-') {
                decrypted.append(' ');
            } else {
                decrypted.append(rotate(ch));
            }
        }
        return decrypted.toString();
    }

    private char rotate(char ch) {
        return (char) ('a' + ((ch + sectorId - 'a') % LETTERS_COUNT));
    }
}
