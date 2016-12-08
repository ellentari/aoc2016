package com.adventofcode.day8;

import com.adventofcode.ResourceUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * --- Day 8: Two-Factor Authentication ---
 * <p>
 * You come across a door implementing what you can only assume is an implementation of two-factor
 * authentication after a long game of requirements telephone.
 * <p>
 * To get past the door, you first swipe a keycard (no problem; there was one on a nearby desk). Then,
 * it displays a code on a little screen, and you type that code on a keypad. Then, presumably, the door unlocks.
 * <p>
 * Unfortunately, the screen has been smashed. After a few minutes, you've taken everything apart and
 * figured out how it works. Now you just have to work out what the screen would have displayed.
 * <p>
 * The magnetic strip on the card you swiped encodes a series of instructions for the screen;
 * these instructions are your puzzle input. The screen is 50 pixels wide and 6 pixels tall,
 * all of which start off, and is capable of three somewhat peculiar operations:
 * <p>
 * rect AxB turns on all of the pixels in a rectangle at the top-left of the screen which is A wide and B tall.
 * rotate row y=A by B shifts all of the pixels in row A (0 is the top row) right by B pixels.
 * Pixels that would fall off the right end appear at the left end of the row.
 * rotate column x=A by B shifts all of the pixels in column A (0 is the left column) down by B pixels.
 * Pixels that would fall off the bottom appear at the top of the column.
 * For example, here is a simple sequence on a smaller screen:
 * <p>
 * rect 3x2 creates a small rectangle in the top-left corner:
 * <p>
 * ###....
 * ###....
 * .......
 * rotate column x=1 by 1 rotates the second column down by one pixel:
 * <p>
 * #.#....
 * ###....
 * .#.....
 * rotate row y=0 by 4 rotates the top row right by four pixels:
 * <p>
 * ....#.#
 * ###....
 * .#.....
 * rotate column x=1 by 1 again rotates the second column down by one pixel, causing the bottom pixel
 * to wrap back to the top:
 * <p>
 * .#..#.#
 * #.#....
 * .#.....
 * As you can see, this display technology is extremely powerful, and will soon dominate the
 * tiny-code-displaying-screen market. That's what the advertisement on the back of the display
 * tries to convince you, anyway.
 * <p>
 * There seems to be an intermediate check of the voltage used by the display: after you swipe
 * your card, if the screen did work, how many pixels should be lit?
 * <p>
 * --- Part Two ---
 * <p>
 * You notice that the screen is only capable of displaying capital letters; in the font it uses,
 * each letter is 5 pixels wide and 6 tall.
 * <p>
 * After you swipe your card, what code is the screen trying to display?
 */
public class Day8 {

    private static final int SCREEN_WIDTH = 50;
    private static final int SCREEN_HEIGHT = 6;

    public static void main(String[] args) {
        List<String> input = ResourceUtils.readLines("day8.txt");

        System.out.println(solvePart1(input, SCREEN_WIDTH, SCREEN_HEIGHT));
        System.out.println(solvePart2(input, SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    static int solvePart1(List<String> input, int screenWidth, int screenHeight) {
        Screen screen = new Screen(screenWidth, screenHeight);

        parseScreenCommands(input).forEach(screenCommand -> screenCommand.execute(screen));

        return screen.getTurnedOnCount();

    }

    static String solvePart2(List<String> input, int screenWidth, int screenHeight) {
        Screen screen = new Screen(screenWidth, screenHeight);

        parseScreenCommands(input).forEach(screenCommand -> screenCommand.execute(screen));

        return screen.toString();
    }

    private static Stream<ScreenCommand> parseScreenCommands(List<String> input) {
        return input.stream().map(ScreenCommand::new);
    }


}
