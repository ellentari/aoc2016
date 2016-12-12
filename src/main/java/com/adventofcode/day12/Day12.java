package com.adventofcode.day12;

import com.adventofcode.ResourceUtils;

import java.util.List;

public class Day12 {

    public static void main(String[] args) {
        List<String> input = ResourceUtils.readLines("day12.txt");

        int[] registers = {0, 0, 1, 0};

        for (int i = 0; i < input.size(); ) {
            String line = input.get(i);
            String[] split = line.split("\\s+");

            if ("cpy".equals(split[0])) {
                String to = split[2];
                String from = split[1];

                int toRegister = parseRegister(to);
                int fromRegister = parseRegister(from);

                if (fromRegister != -1) {
                    registers[toRegister] = registers[fromRegister];
                } else {
                    registers[toRegister] = Integer.parseInt(from);
                }
            } else if ("inc".equals(split[0])) {
                registers[parseRegister(split[1])]++;
            } else if ("dec".equals(split[0])) {
                registers[parseRegister(split[1])]--;
            } else if ("jnz".equals(split[0])) {
                int register = parseRegister(split[1]);
                int regValue = register != -1 ? registers[register] : Integer.parseInt(split[1]);

                if (regValue != 0) {
                    i += Integer.parseInt(split[2]);
                    continue;
                }
            }
            i++;
        }

        System.out.println(registers[0]);

    }


    private static int parseRegister(String register) {
        if (register.length() > 1) {
            return -1;
        }

        char c = register.charAt(0);
        if (Character.isLetter(c)) {
            return c - 'a';
        }

        return -1;
    }

}
