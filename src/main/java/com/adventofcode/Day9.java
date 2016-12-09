package com.adventofcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Day9 {

//    public static void main(String[] args) {
//        String input = ResourceUtils.read("day9.txt");
////        String input = "X(8x2)(3x3)ABCY";
//
//
//        Stack<Character> par = new Stack<>();
//        int chars = 0;
//        int times = 0;
//
//        Character delim = null;
//
//        StringBuilder result = new StringBuilder();
//
//        for (int i = 0; i < input.length(); i++) {
//            char ch = input.charAt(i);
//            if (ch == '(' && par.isEmpty()) {
//                par.push(ch);
//            } else if (Character.isDigit(ch) && !par.isEmpty()) {
//                if (delim == null) {
//                    chars = chars * 10 + Character.digit(ch, 10);
//                } else {
//                    times = times * 10 + Character.digit(ch, 10);
//                }
//            } else if (ch == 'x' && !par.isEmpty()) {
//                delim = ch;
//            } else if (ch == ')' && !par.isEmpty()) {
//                par.pop();
//
//                String substring = input.substring(i + 1, i + 1 + chars);
//                for (int j = 0; j < times; j++) {
//                    result.append(substring);
//                }
//
//                i += chars;
//                delim = null;
//                chars = 0;
//                times = 0;
//            } else {
//                result.append(ch);
//            }
//        }
//
//
//        System.out.println(result.toString().length());
//    }

//        public static void main(String[] args) {
//        String input = ResourceUtils.read("day9.txt");
////        String input = "(27x12)(20x12)(13x14)(7x10)(1x12)A";
//
//        Stack<Character> par = new Stack<>();
//        int chars = 0;
//        int times = 0;
//
//        Character delim = null;
//
//        StringBuilder result;
//
//        do {
//            result = new StringBuilder();
//
//            for (int i = 0; i < input.length(); i++) {
//                char ch = input.charAt(i);
//                if (ch == '(' && par.isEmpty()) {
//                    par.push(ch);
//                } else if (Character.isDigit(ch) && !par.isEmpty()) {
//                    if (delim == null) {
//                        chars = chars * 10 + Character.digit(ch, 10);
//                    } else {
//                        times = times * 10 + Character.digit(ch, 10);
//                    }
//                } else if (ch == 'x' && !par.isEmpty()) {
//                    delim = ch;
//                } else if (ch == ')' && !par.isEmpty()) {
//                    par.pop();
//
//                    String substring = input.substring(i + 1, i + 1 + chars);
//                    for (int j = 0; j < times; j++) {
//                        result.append(substring);
//                    }
//
//                    i += chars;
//                    delim = null;
//                    chars = 0;
//                    times = 0;
//                } else {
//                    result.append(ch);
//                }
//            }
//
//            input = result.toString();
//        } while (input.contains("("));
//
//
//        System.out.println(result.toString().length());
//    }

    public static void main(String[] args) {
        String input = ResourceUtils.read("day9.txt");
//        String input = "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN";

        List<Object> tokens = parse(input);



        long totalLength = 0;

        Marker lastMarker = null;
        for (int i = 0; i < tokens.size(); i++) {
            Object obj = tokens.get(i);
            if (obj instanceof Token) {
                Token token = (Token) obj;
                if (lastMarker == null) {
                    totalLength += token.token.length();
                } else {
                    totalLength += lastMarker.decompressedLength(token);
                    lastMarker = null;
                }
            } else if (obj instanceof Marker) {
                Marker marker = (Marker) obj;
                lastMarker = marker;
                int remainingLength = marker.chars;

                boolean tokenFound = true;
                for (int j = i + 1; remainingLength > 0 && j < tokens.size(); j++) {
                    Object o = tokens.get(j);
                    if (o instanceof Token) {
                        tokenFound = true;
                        remainingLength -= ((Token) o).token.length();
                    } else if (o instanceof Marker) {
                        Marker marker2 = (Marker) o;
                        remainingLength -= marker2.length();
                        if (tokenFound) {
                            marker2.update(marker);
                            tokenFound = false;
                        }
                    }
                }
            }
        }


        //188669492638 high
        //4047226649980435870 high
        System.out.println(totalLength);
    }

    /*

    For example:
(3x3)XYZ still becomes XYZXYZXYZ, as the decompressed section contains no markers.
X(8x2)(3x3)ABCY becomes XABCABCABCABCABCABCY, because the decompressed data from the (8x2) marker is then further
decompressed, thus triggering the (3x3) marker twice for a total of six ABC sequences.
(27x12)(20x12)(13x14)(7x10)(1x12)A decompresses into a string of A repeated 241920 times.
(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN becomes 445 characters long.
     */
    private static List<Object> parse(String input) {
        List<Object> tokens = new ArrayList<>();

        Marker marker = null;
        Character delim = null;
        Token token = null;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '(') {
                marker = new Marker();
                if (token != null) {
                    tokens.add(token);
                    token = null;
                }
            } else if (ch == ')') {
                tokens.add(marker);
                marker = null;
                delim = null;
            } else if (Character.isDigit(ch) && marker != null) {
                if (delim == null) {
                    marker.chars = marker.chars * 10 + Character.digit(ch, 10);
                } else {
                    marker.times = marker.times * 10 + Character.digit(ch, 10);
                    marker.updatedTimes = marker.times;
                }
            } else if (ch == 'x' && marker != null) {
                delim = ch;
            } else if (marker == null) {
                if (token == null) {
                    token = new Token();
                }
                token.token += ch;
            }
        }

        if (token != null) {
            tokens.add(token);
        }

        return tokens;
    }

    static class Marker {
        int chars;
        int times;
        long updatedTimes;

        @Override
        public String toString() {
            return "(" + chars + "x" + updatedTimes + ')';
        }

        int length() {
            return ("(" + chars + "x" + times + ')').length();
        }

        String repeat(Token token) {
            StringBuilder repeated = new StringBuilder();

            String sub = token.token.substring(0, chars);
            for (int i = 0; i < times; i++) {
                repeated.append(sub);
            }

            repeated.append(token.token.substring(chars));

            return repeated.toString();
        }

        long decompressedLength(Token token) {
            return (long) chars * updatedTimes + token.token.length() - chars;
        }

        void update(Marker from) {
            updatedTimes *= from.updatedTimes;
        }
    }

    static class Token {
        String token = "";

        Token() {
        }

        Token(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return token;
        }
    }
}
