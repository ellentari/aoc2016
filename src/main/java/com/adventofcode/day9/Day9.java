package com.adventofcode.day9;

import com.adventofcode.common.TokenInfo;
import com.adventofcode.common.Tokenizer;

import java.util.List;
import java.util.regex.Matcher;

import static java.util.Arrays.asList;

public class Day9 {

    private static final TokenInfo<Token> SEQ_TOKEN_INFO
            = new TokenInfo<>("\\w+", Day9::parseSequence);

    private static final TokenInfo<Token> MARKER_TOKEN_INFO
            = new TokenInfo<>("\\((?<first>\\d+)x(?<second>\\d+)\\)", Day9::parseMarker);

    public static long solvePart1(String input) {
        List<Token> tokens = new Tokenizer<>(asList(SEQ_TOKEN_INFO, MARKER_TOKEN_INFO)).parse(input);

        long decompressedLength = 0;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            if (token instanceof Sequence) {
                decompressedLength += token.getDecompressedLength();
            } else if (token instanceof Marker) {
                Marker marker = (Marker) token;

                if (marker.isAsSequence()) {
                    decompressedLength += token.getDecompressedLength();
                } else {
                    int remainingLength = marker.getSubSequenceLength();
                    for (int j = i + 1; remainingLength > 0 && j < tokens.size(); j++) {
                        Token tokenWithin = tokens.get(j);

                        if (tokenWithin instanceof Marker) {
                            ((Marker) tokenWithin).setAsSequence();
                        }

                        tokenWithin.decompress(new Marker(remainingLength, marker.getRepetitionsCount()));
                        remainingLength -= tokenWithin.getLength();
                    }
                }
            }
        }

        return decompressedLength;
    }

    public static long solvePart2(String input) {
        List<Token> tokens = new Tokenizer<>(asList(SEQ_TOKEN_INFO, MARKER_TOKEN_INFO)).parse(input);

        long decompressedLength = 0;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            if (token instanceof Sequence) {
                if (i > 0 && tokens.get(i - 1) instanceof Marker) {
                    token.decompress((Marker) tokens.get(i - 1));
                }
                decompressedLength += token.getDecompressedLength();
            } else if (token instanceof Marker) {
                Marker marker = (Marker) token;

                int remainingLength = marker.getSubSequenceLength();
                for (int j = i + 1; remainingLength > 0 && j < tokens.size(); j++) {
                    Token tokenWithin = tokens.get(j);

                    if (tokenWithin instanceof Marker) {
                        tokenWithin.decompress(marker);
                    }

                    remainingLength -= tokenWithin.getLength();
                }
            }
        }

        return decompressedLength;
    }

    private static Sequence parseSequence(Matcher matcher) {
        return new Sequence(matcher.group());
    }

    private static Marker parseMarker(Matcher matcher) {
        int subSequenceLength = Integer.parseInt(matcher.group("first"));
        int repetitionsCount = Integer.parseInt(matcher.group("second"));

        return new Marker(subSequenceLength, repetitionsCount);
    }
}
