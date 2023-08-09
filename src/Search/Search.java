package Search;

public class Search implements ISearch {

    public static int binarySearch(int[] arr, int value) {
        return binarySearch(arr, value, "iterative");
    }

    public static int binarySearch(int[] arr, int value, String type) {
        return switch (type) {
            case "iterative" -> iterativeBinary(arr, value);
            case "recursive" -> recursiveBinary(arr, value);
            default -> throw new IllegalArgumentException("Invalid search type specified");
        };
    }

    public static int iterativeBinary(int[] arr, int value) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;

            if (value < arr[middle]) {
                end = middle - 1;
            } else if (value > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }

        return -1;
    }

    public static int recursiveBinary(int[] arr, int value) {
        int start = 0;
        int end = arr.length - 1;

        return recursiveBinaryHelper(arr, value, start, end);
    }

    private static int recursiveBinaryHelper(int[] arr, int value, int start, int end) {
        int middle = (start + end) / 2;

        if (start > end) {
            return -1;
        }

        if (value < arr[middle]) {
            return recursiveBinaryHelper(arr, value, start, middle - 1);
        }

        if (value > arr[middle]) {
            return recursiveBinaryHelper(arr, value, middle + 1, end);
        }

        return middle;
    }
}
