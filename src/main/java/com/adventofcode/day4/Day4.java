package com.adventofcode.day4;

import com.adventofcode.common.ResourceUtils;

import java.util.stream.Stream;

import static java.util.Arrays.stream;

/**
 * --- Day 4: Security Through Obscurity ---
 * <p>
 * Finally, you come across an information kiosk with a list of rooms. Of course, the list is encrypted and full
 * of decoy data, but the instructions to decode the list are barely hidden nearby. Better remove the decoy data first.
 * <p>
 * Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID,
 * and a checksum in square brackets.
 * <p>
 * A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order,
 * with ties broken by alphabetization. For example:
 * <p>
 * - aaaaa-bbb-z-y-x-123[abxyz] is a real room because the most common letters are a (5), b (3), and then a tie
 * between x, y, and z, which are listed alphabetically.
 * - a-b-c-d-e-f-g-h-987[abcde] is a real room because although the letters are all tied (1 of each), the first
 * five are listed alphabetically.
 * - not-a-real-room-404[oarel] is a real room.
 * - totally-real-room-200[decoy] is not.
 * Of the real rooms from the list above, the sum of their sector IDs is 1514.
 * <p>
 * What is the sum of the sector IDs of the real rooms?
 * <p>
 * --- Part Two ---
 * <p>
 * With all the decoy data out of the way, it's time to decrypt this list and get moving.
 * <p>
 * The room names are encrypted by a state-of-the-art shift cipher, which is nearly unbreakable without the right
 * software. However, the information kiosk designers at Easter Bunny HQ were not expecting to deal with a master
 * cryptographer like yourself.
 * <p>
 * To decrypt a room name, rotate each letter forward through the alphabet a number of times equal to the room's
 * sector ID. A becomes B, B becomes C, Z becomes A, and so on. Dashes become spaces.
 * <p>
 * For example, the real name for qzmt-zixmtkozy-ivhz-343 is very encrypted name.
 * <p>
 * What is the sector ID of the room where North Pole objects are stored?
 */
public class Day4 {

    public static void main(String[] args) {
        String input = ResourceUtils.read("day4.txt");

        System.out.println(solvePart1(input));
        System.out.println(solvePart2(input));
    }

    static int solvePart1(String input) {
        return parseRooms(input)
                .filter(Room::isReal)
                .mapToInt(Room::getSectorId)
                .sum();
    }

    private static int solvePart2(String input) {
        return parseRooms(input)
                .filter(Room::isReal)
                .filter(room -> room.decryptName().contains("northpole object"))
                .map(Room::getSectorId)
                .findFirst()
                .orElse(-1);
    }

    private static Stream<Room> parseRooms(String input) {
        return stream(input.split("\\n")).map(Room::new);
    }

}
