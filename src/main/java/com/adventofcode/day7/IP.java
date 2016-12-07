package com.adventofcode.day7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class IP {

    private static final int ABBA_LENGTH = 4;
    private static final int ABA_LENGTH = 3;

    private final String ip;

    IP(String ip) {
        this.ip = ip;
    }

    boolean supportsTLS() {
        return hasAbbaOutsideBrackets(getBrackets());
    }

    private boolean hasAbbaOutsideBrackets(List<Range> brackets) {
        boolean abbaFound = false;

        for (int i = 0; i < ip.length() - ABBA_LENGTH + 1; i++) {
            if (hasAbbaAt(i)) {
                abbaFound = true;
                if (isAbbaWithinBrackets(brackets, i)) {
                    return false;
                }
            }
        }

        return abbaFound;
    }

    private boolean hasAbbaAt(int start) {
        return isAlphabeticPalindrome(ip, start, start + ABBA_LENGTH - 1);
    }

    private boolean isAbbaWithinBrackets(List<Range> brackets, int start) {
        return isInBrackets(brackets, start, start + ABBA_LENGTH - 1);
    }

    private List<Range> getBrackets() {
        List<Range> brackets = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < ip.length(); i++) {
            if (ip.charAt(i) == '[') {
                start = i;
            } else if (ip.charAt(i) == ']') {
                brackets.add(new Range(start, i));
            }
        }
        return brackets;
    }

    boolean supportsSSL() {
        return hasAbaOutsideBracketsAndBabWithinBrackets(getBrackets());
    }

    private boolean hasAbaOutsideBracketsAndBabWithinBrackets(List<Range> brackets) {
        for (int i = 0; i < ip.length() - ABA_LENGTH + 1; i++) {
            if (hasAbaAt(i) && !isAbaWithinBrackets(brackets, i) && hasBabWithinBrackets(i, brackets)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasAbaAt(int start) {
        return isAlphabeticPalindrome(ip, start, start + ABA_LENGTH - 1);
    }

    private boolean isAbaWithinBrackets(List<Range> brackets, int start) {
        return isInBrackets(brackets, start, start + ABA_LENGTH - 1);
    }

    private boolean hasBabWithinBrackets(int abaStart, List<Range> brackets) {
        String bab = reverseAba(abaStart);

        int i = -1;
        while ((i = ip.indexOf(bab, i + 1)) >= 0) {
            if (isInBrackets(brackets, i, i + ABA_LENGTH - 1)) {
                return true;
            }
        }

        return false;
    }

    private String reverseAba(int abaStart) {
        char[] reverse = new char[ABA_LENGTH];
        for (int i = 0, j = ABA_LENGTH / 2; i <= ABA_LENGTH / 2; i++, j--) {
            reverse[i] = ip.charAt(abaStart + j);
            reverse[ABA_LENGTH - i - 1] = reverse[i];
        }

        return new String(reverse);
    }

    private static boolean isAlphabeticPalindrome(String value, int start, int end) {
        int i = start, j = end;
        Set<Character> uniqueChars = new HashSet<>();
        while (i <= j) {
            char ch1 = value.charAt(i++);
            char ch2 = value.charAt(j--);
            if (!Character.isAlphabetic(ch1) || !Character.isAlphabetic(ch2) || ch1 != ch2) {
                return false;
            } else {
                uniqueChars.add(ch1);
            }
        }
        return uniqueChars.size() > 1;
    }

   private static boolean isInBrackets(List<Range> brackets, int start, int end) {
        for (Range bracketsRange : brackets) {
            if (bracketsRange.isIn(start) && bracketsRange.isIn(end)) {
                return true;
            } else if (start < bracketsRange.getStart()) {
                break;
            }
        }
        return false;
    }
}
