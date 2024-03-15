import java.util.Scanner;

/**
 * The ScannerInt class provides utility methods for reading integers from the console with input validation.
 */
public class ScannerInt {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads an integer from the console, with input validation.
     *
     * @return The integer entered by the user.
     */
    public static int getInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next(); // consume the invalid input
        }
        int userInput = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return userInput;
    }

    /**
     * Closes the scanner when it is no longer needed.
     */
    public static void closeScanner() {
        scanner.close();
    }
}
