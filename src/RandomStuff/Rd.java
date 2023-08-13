package RandomStuff;

import java.util.HashSet;
import java.util.Random;

public class Rd {
    public static final Random rd = new Random();

    public static void main(String[] args) {}

    public static void randomString(int len) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char nextChar;
            char lastChar = sb.length() > 0 ? sb.charAt(sb.length() - 1) : ' ';

            do {
                nextChar = (char) randomInt(33, 126);
            } while (nextChar == lastChar);

            sb.append(nextChar);
        }

        System.out.println(sb);
    }

    public static int randomInt(int min, int max) {
        return rd.nextInt(max - min + 1) + min;
    }

    public static HashSet<Character> findDuplicates(String str) {
        int h = 0;
        HashSet<Character> dup = new HashSet<>();

        for (int c : str.toCharArray()) {
            int x = 1;
            int shiftBit = (c - 97);
            x = x << shiftBit;
            if ((x & h) > 0) {
                dup.add((char) c);
            }

            h = h | x;
        }

        return dup;
    }
}
