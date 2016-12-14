package com.adventofcode.day14;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 14: One-Time Pad ---
 * <p>
 * In order to communicate securely with Santa while you're on this mission, you've been using a one-time pad that
 * you generate using a pre-agreed algorithm. Unfortunately, you've run out of keys in your one-time pad, and so you
 * need to generate some more.
 * <p>
 * To generate keys, you first get a stream of random data by taking the MD5 of a pre-arranged salt (your puzzle input)
 * and an increasing integer index (starting with 0, and represented in decimal); the resulting MD5 hash should be
 * represented as a string of lowercase hexadecimal digits.
 * <p>
 * However, not all of these MD5 hashes are keys, and you need 64 new keys for your one-time pad. A hash is a key
 * only if:
 * <p>
 * It contains three of the same character in a row, like 777. Only consider the first such triplet in a hash.
 * One of the next 1000 hashes in the stream contains that same character five times in a row, like 77777.
 * Considering future hashes for five-of-a-kind sequences does not cause those hashes to be skipped; instead,
 * regardless of whether the current hash is a key, always resume testing for keys starting with the very next hash.
 * <p>
 * For example, if the pre-arranged salt is abc:
 * <p>
 * The first index which produces a triple is 18, because the MD5 hash of abc18 contains ...cc38887a5.... However,
 * index 18 does not count as a key for your one-time pad, because none of the next thousand hashes (index 19
 * through index 1018) contain 88888.
 * The next index which produces a triple is 39; the hash of abc39 contains eee. It is also the first key: one of
 * the next thousand hashes (the one at index 816) contains eeeee.
 * None of the next six triples are keys, but the one after that, at index 92, is: it contains 999 and index 200
 * contains 99999.
 * Eventually, index 22728 meets all of the criteria to generate the 64th key.
 * So, using our example salt of abc, index 22728 produces the 64th key.
 * <p>
 * Given the actual salt in your puzzle input, what index produces your 64th one-time pad key?
 * <p>
 * --- Part Two ---
 * <p>
 * Of course, in order to make this process even more secure, you've also implemented key stretching.
 * <p>
 * Key stretching forces attackers to spend more time generating hashes. Unfortunately, it forces everyone else
 * to spend more time, too.
 * <p>
 * To implement key stretching, whenever you generate a hash, before you use it, you first find the MD5 hash of
 * that hash, then the MD5 hash of that hash, and so on, a total of 2016 additional hashings. Always use
 * lowercase hexadecimal representations of hashes.
 * <p>
 * For example, to find the stretched hash for index 0 and salt abc:
 * <p>
 * Find the MD5 hash of abc0: 577571be4de9dcce85a041ba0410f29f.
 * Then, find the MD5 hash of that hash: eec80a0c92dc8a0777c619d9bb51e910.
 * Then, find the MD5 hash of that hash: 16062ce768787384c81fe17a7a60c7e3.
 * ...repeat many times...
 * Then, find the MD5 hash of that hash: a107ff634856bb300138cac6568c0f24.
 * So, the stretched hash for index 0 in this situation is a107ff.... In the end, you find the original hash
 * (one use of MD5), then find the hash-of-the-previous-hash 2016 times, for a total of 2017 uses of MD5.
 * <p>
 * The rest of the process remains the same, but now the keys are entirely different. Again for salt abc:
 * <p>
 * The first triple (222, at index 5) has no matching 22222 in the next thousand hashes.
 * The second triple (eee, at index 10) hash a matching eeeee at index 89, and so it is the first key.
 * Eventually, index 22551 produces the 64th key (triple fff with matching fffff at index 22859.
 * Given the actual salt in your puzzle input and using 2016 extra MD5 calls of key stretching, what index now
 * produces your 64th one-time pad key?
 */
public class Day14 {

    private static final int N = 64;
    private static final int STRETCH_TIMES = 2016;
    private static final int KEYS_LIMIT = 1000;

    private static Pattern tripletPattern = Pattern.compile("(\\w)\\1\\1");
    private static Pattern fiveInARowPattern = Pattern.compile("(\\w)\\1\\1\\1\\1");

    public static void main(String[] args) {
        String input = "jlmsuwbz";

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    static int solvePart1(String input) {

        List<Key> possibleKeys = new LinkedList<>();
        List<Key> keys = new ArrayList<>();

        for (int i = 0; keys.size() < N; i++) {
            String s = input + i;
            String hash = hash(s, 0);

            Optional<Triplet> triplet = findFirstTriplet(hash);

            if (triplet.isPresent()) {
                Key possibleKey = new Key(i, hash, triplet.get());
                keys.addAll(findValidKeysAndRemove(possibleKey, possibleKeys));
                possibleKeys.add(possibleKey);
            }
        }

        return keys.get(N - 1).i;
    }

    static int solvePart2(String input) {

        List<Key> possibleKeys = new LinkedList<>();
        List<Key> keys = new ArrayList<>();

        for (int i = 0; keys.size() < N; i++) {
            String s = input + i;
            String hash = hash(s, STRETCH_TIMES);

            Optional<Triplet> triplet = findFirstTriplet(hash);

            if (triplet.isPresent()) {
                Key possibleKey = new Key(i, hash, triplet.get());
                keys.addAll(findValidKeysAndRemove(possibleKey, possibleKeys));
                possibleKeys.add(possibleKey);
            }
        }

        return keys.get(N - 1).i;
    }

    private static String hash(String original, int stretchTimes) {
        String hash = DigestUtils.md5Hex(original.getBytes());

        for (int i = 0; i < stretchTimes; i++) {
            hash = DigestUtils.md5Hex(hash.getBytes());
        }

        return hash;

    }

    private static Optional<Triplet> findFirstTriplet(String md5ex) {
        Matcher matcher = tripletPattern.matcher(md5ex);

        return matcher.find() ?
                Optional.of(new Triplet(matcher.start(), matcher.group(1).charAt(0))) : Optional.empty();
    }

    private static List<Key> findValidKeysAndRemove(Key toCheck, List<Key> possibleKeys) {
        Set<Character> all5InARow = findAll5InARow(toCheck.md5Hex);

        List<Key> valid = new LinkedList<>();

        for (Iterator<Key> i = possibleKeys.iterator(); i.hasNext(); ) {
            Key possibleKey = i.next();
            if (possibleKey.i < toCheck.i - KEYS_LIMIT) {
                i.remove();
            } else if (all5InARow.contains(possibleKey.triplet.tripletChar)) {
                System.out.printf("Found valid key at i=%d%n", possibleKey.i);
                valid.add(possibleKey);
                i.remove();
            }
        }

        return valid;
    }

    private static Set<Character> findAll5InARow(String md5ex) {
        Matcher matcher = fiveInARowPattern.matcher(md5ex);
        Set<Character> fives = new HashSet<>();
        while (matcher.find()) {
            fives.add(matcher.group(1).charAt(0));
        }
        return fives;
    }

    private static class Key {
        final int i;
        final String md5Hex;

        final Triplet triplet;

        Key(int i, String md5Hex, Triplet triplet) {
            this.i = i;
            this.md5Hex = md5Hex;
            this.triplet = triplet;
        }
    }

    private static class Triplet {
        final int start;
        final char tripletChar;

        Triplet(int start, char tripletChar) {
            this.start = start;
            this.tripletChar = tripletChar;
        }
    }
}
