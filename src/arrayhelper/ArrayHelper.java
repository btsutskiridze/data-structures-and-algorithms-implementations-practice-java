package arrayhelper;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

public class ArrayHelper {

    public static void main(String[] args) {
        int[] arr = {
                1, 2, 3, 5, 5, 5, 5, 6, 7, 8, 9, 10,
        };
        System.out.println(findDuplicatesWithHashMap(arr));
        System.out.println(findDuplicatesWithHashSet(arr));
        System.out.println(findMissings(arr));
        System.out.println(findPairsWithSumInSortedArray(arr, 10));
        System.out.println(findMinAndMax(arr));
    }

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

    public static ArrayList<Pair<Integer, Integer>> findPairsWithSumSlow(int[] arr, int sum) {

        ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j < arr.length; j++) {
                if ((arr[i] + arr[j] == sum) && !pairs.contains(new Pair<>(arr[i], arr[j]))) {
                    pairs.add(new Pair<>(arr[i], arr[j]));
                }
            }
        }

        return pairs;
    }

    public static ArrayList<Pair<Integer, Integer>> findPairsWithSumFast(int[] arr, int sum) {
        HashSet<Integer> seen = new HashSet<>();
        ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();

        for (int it : arr) {
            if (seen.contains(sum - it) && !pairs.contains(new Pair<>(it, sum - it))) {
                pairs.add(new Pair<>(it, sum - it));
            }

            seen.add(it);
        }

        return pairs;
    }

    public static ArrayList<Pair<Integer, Integer>> findPairsWithSumInSortedArray(
            int[] arr, int sum) {

        int i = 0, j = arr.length - 1;
        ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();

        while (i < j) {
            if (arr[i] + arr[j] == sum && !pairs.contains(new Pair<>(arr[i], arr[j]))) {
                pairs.add(new Pair<>(arr[i], arr[j]));
                i++;
                j--;
            } else if (arr[i] + arr[j] > sum) {
                j--;
            } else {
                i++;
            }
        }

        return pairs;
    }

    public static Pair<Integer, Integer> findMinAndMax(int[] arr) {
        Pair<Integer, Integer> minMax = new Pair<>(arr[0], arr[0]);

        for (int num : arr) {
            if (minMax.first() > num) {
                minMax.setFirst(num);
            } else if (minMax.second() < num) {
                minMax.setSecond(num);
            }
        }

        return minMax;
    }
}
