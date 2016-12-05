package com.adventofcode.day5;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;

public class Day5 {

    private static final int PASSWORD_LENGTH = 8;
    private static final String HEX_PREFIX  = "00000";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String doorId = "ojvtpuvg";

        System.out.println(solvePart1(doorId));
        System.out.println(solvePart2(doorId));
    }

    static String solvePart1(String doorId) throws NoSuchAlgorithmException {
        String password = "";

        for (int i = 0; password.length() < PASSWORD_LENGTH; i++) {
            String md5hex = DigestUtils.md5Hex(doorId + i);
            if (md5hex.startsWith(HEX_PREFIX)) {
                password += md5hex.charAt(HEX_PREFIX.length());
            }
        }

        return password;
    }

    static String solvePart2(String doorId) throws NoSuchAlgorithmException {
        char[] password = new char[PASSWORD_LENGTH];
        int found = 0;

        for (int i = 0; found < PASSWORD_LENGTH; i++) {
            String md5hex = DigestUtils.md5Hex(doorId + i);
            if (md5hex.startsWith(HEX_PREFIX)) {

                int position = Character.digit(md5hex.charAt(HEX_PREFIX.length()), 16);

                if (position < PASSWORD_LENGTH && password[position] == '\0')  {
                    found++;
                    password[position] = md5hex.charAt(HEX_PREFIX.length() + 1);
                }
            }
        }

        return new String(password);
    }

}
