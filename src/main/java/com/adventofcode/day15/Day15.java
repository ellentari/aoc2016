package com.adventofcode.day15;

public class Day15 {

    public static void main(String[] args) {

        Disc[] discs = new Disc[] {
                new Disc(0, 1, 7),
                new Disc(0, 2, 13),
                new Disc(2, 3, 3),
                new Disc(2, 4, 5),
                new Disc(0, 5, 17),
                new Disc(7, 6, 19),
                new Disc(0, 7, 11)
        };


        int n;
        boolean found;
        for (n = 1; ; n++) {
            found = true;
            for (Disc disc : discs) {
                if (!disc.isAt0AfterNTime(n)) {
                    found = false;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        System.out.println(n);


    }

    private static class Disc {
        final int start;
        final int number;
        final int nPositions;

        Disc(int start, int number, int nPositions) {
            this.start = start;
            this.number = number;
            this.nPositions = nPositions;
        }

        boolean isAt0AfterNTime(int n) {
            return (start + n + number) % nPositions == 0;
        }

        int slotAfterN(int n) {
            return (start + n + number) % nPositions;
        }
    }
}
