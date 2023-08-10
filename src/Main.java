import java.util.ArrayList;
import java.util.BitSet;

public class Main {

    public static void main(String[] args) {
        //        VectorDriver.start();
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
}
