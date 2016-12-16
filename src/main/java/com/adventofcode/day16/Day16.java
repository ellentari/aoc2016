package com.adventofcode.day16;

public class Day16 {

    public static void main(String[] args) {
        String input = "10001001100000001";
        int discSize = 35651584;

        System.out.println(solvePart1(input, discSize));
    }

    static String solvePart1(String input, int discSize) {
        String data = input;

        while (data.length() < discSize) {
            data = data + "0" + makeReverseInverted(data);
        }

        data = data.length() > discSize ? data.substring(0, discSize) : data;

        return checkSum(data);
    }

    private static String makeReverseInverted(String value) {
        StringBuilder result = new StringBuilder(value);
        result.reverse();
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '1') {
                result.setCharAt(i, '0');
            } else {
                result.setCharAt(i, '1');
            }
        }
        return result.toString();
    }

    private static String checkSum(String value) {
        StringBuilder checkSum;

        do {
            checkSum = new StringBuilder();
            for (int i = 0; i < value.length() - 1; i+=2) {
                if (value.charAt(i) == value.charAt(i + 1)) {
                    checkSum.append('1');
                } else {
                    checkSum.append('0');
                }
            }
            value = checkSum.toString();
        } while (checkSum.length() % 2 == 0);

        return checkSum.toString();
    }
}
