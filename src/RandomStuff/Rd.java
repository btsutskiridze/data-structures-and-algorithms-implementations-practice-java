package RandomStuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Rd {
    public static final Random rd = new Random();

    public static void main(String[] args) {
        String wrd = "AAB";

        System.out.println(strPermutations(wrd));
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

    public static boolean areAnagrams(String str1, String str2) {
        HashMap<Character, Integer> hash = new HashMap<>();

        for (int c : str1.toCharArray()) {
            hash.put((char) c, hash.getOrDefault((char) c, 0) + 1);
        }

        for (int c : str2.toCharArray()) {
            hash.put((char) c, hash.getOrDefault((char) c, 0) - 1);

            if (hash.get((char) c) < 0) {
                return false;
            }
        }

        return true;
    }

    public static ArrayList<String> strPermutations(String str) {
        char[] strArr = str.toCharArray();
        byte[] helper = new byte[strArr.length];
        char[] result = new char[strArr.length];
        ArrayList<String> perms = new ArrayList<>();

        permHelperWithSwap(strArr, 0, strArr.length - 1, perms);

        return perms;
    }

    public static void permHelper(
            char[] str, byte[] helper, char[] result, int k, ArrayList<String> perms) {

        if (k == str.length) {
            if (!perms.contains(String.valueOf(result))) perms.add(new String(result));

            return;
        }

        for (int i = 0; i < str.length; i++) {
            if (helper[i] == 0) {
                result[k] = str[i];
                helper[i] = 1;
                permHelper(str, helper, result, k + 1, perms);
                helper[i] = 0;
            }
        }
    }

    public static void permHelperWithSwap(char[] str, int low, int high, ArrayList<String> perms) {
        if (low == high) {
            if (!perms.contains(String.valueOf(str))) perms.add(new String(str));
            return;
        }

        for (int i = low; i <= high; i++) {
            swap(str, low, i);
            permHelperWithSwap(str, low + 1, high, perms);
            swap(str, low, i);
        }
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
