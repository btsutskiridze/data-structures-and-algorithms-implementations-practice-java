import Vector.Vector;
import java.util.Scanner;

public class VectorDriver {

    private static final Scanner scanner = new Scanner(System.in);

    private static final int EXIT_NUMBER = 8;

    public static void start() {
        Vector vector = new Vector();

        while (true) {
            printMenu();
            int opt = getOption();

            if (opt == EXIT_NUMBER) {
                System.out.println("Exiting...");
                break;
            }

            handleOption(opt, vector);
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Insert elements");
        System.out.println("2. Insert element at index");
        System.out.println("3. Delete element");
        System.out.println("4. Delete element at index");
        System.out.println("5. Search for element");
        System.out.println("6. Reverse vector");
        System.out.println("7. Display vector");
        System.out.println(EXIT_NUMBER + ". Exit");
    }

    private static void handleOption(int opt, Vector vector) {

        switch (opt) {
            case 1 -> insertElements(vector);
            case 2 -> insertElement(vector);
            case 3 -> deleteElement(vector);
            case 4 -> deleteElementAt(vector);
            case 5 -> searchElement(vector);
            case 6 -> reverseVector(vector);
            case 7 -> vector.display();
            default -> System.out.println("Invalid option. Please enter a valid option.");
        }
    }

    private static int getOption() {
        while (scanner.hasNext()) {
            String opt = scanner.nextLine();

            try {
                return Integer.parseInt(opt);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        return 7;
    }

    private static void insertElements(Vector vector) {
        System.out.println("Enter elements to insert into first vector (type q to stop): ");
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                int element = scanner.nextInt();
                vector.push(element);
                continue;
            }

            if (scanner.hasNext("q")) {
                scanner.next();
                cleanScanner();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer or 'q' to stop.");
                scanner.next();
            }
        }
    }

    private static void insertElement(Vector vector) {
        int index, element;

        System.out.println("Enter element to insert: ");
        element = scanner.nextInt();

        System.out.println("Enter index to insert at: ");
        index = scanner.nextInt();
        cleanScanner();

        vector.insert(element, index);
    }

    private static void deleteElement(Vector vector) {
        int element;

        System.out.println("Enter element to delete: ");
        element = scanner.nextInt();
        cleanScanner();

        vector.delete(element);
    }

    private static void deleteElementAt(Vector vector) {
        int index;

        System.out.println("Enter index to delete at: ");
        index = scanner.nextInt();
        cleanScanner();

        vector.deleteAt(index);
    }

    private static void searchElement(Vector vector) {
        int element;

        System.out.println("Enter element to search for: ");
        element = scanner.nextInt();
        cleanScanner();

        int index = vector.search(element);

        if (index == -1) {
            System.out.println("Element not found.");
            return;
        }

        System.out.println("Element found at index: " + index);
    }

    private static void reverseVector(Vector vector) {
        vector.reverse();
    }

    private static void cleanScanner() {
        scanner.nextLine();
    }
}
