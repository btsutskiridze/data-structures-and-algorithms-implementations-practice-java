package ArrayHelper;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

public class ArrayHelper {

    public static ArrayList<Integer> findMissings(int[] arr) {
        int maxNumber = max(arr);
        BitSet bitset = new BitSet(maxNumber);
        ArrayList<Integer> missingItems = new ArrayList<>();

        for (int j : arr) {
            bitset.set(j);
        }

        for (int i = 1; i <= maxNumber; i++) {
            if (!bitset.get(i)) {
                missingItems.add(i);
            }
        }

        return missingItems;
    }

    public static int max(int[] arr) {
        int max = arr[0];
        for (int it : arr) {
            if (it > max) max = it;
        }
        return max;
    }

    public static ArrayList<Integer> findDuplicatesWithHashMap(int[] arr) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        ArrayList<Integer> duplicates = new ArrayList<>();

        for (int it : arr) {
            seen.put(it, seen.getOrDefault(it, 0) + 1);
        }

        for (int it : seen.keySet()) {
            if (seen.get(it) > 1) {
                duplicates.add(it);
            }
        }

        return duplicates;
    }

    public static ArrayList<Integer> findDuplicatesWithHashSet(int[] arr) {
        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> added = new HashSet<>();
        ArrayList<Integer> duplicates = new ArrayList<>();

        for (int num : arr) {
            if (seen.contains(num) && !added.contains(num)) {
                duplicates.add(num);
                added.add(num);
            } else {
                seen.add(num);
            }
        }

        return duplicates;
    }
}
