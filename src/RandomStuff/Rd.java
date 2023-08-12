package RandomStuff;

import java.util.Random;

public class Rd {
    public static final Random rd = new Random();

    public static void main(String[] args) {
        randomString(100);
    }

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
}
