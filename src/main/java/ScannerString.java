import java.util.Scanner;

/**
 * The ScannerString class provides utility methods for reading strings from the console.
 */
public class ScannerString {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Reads a string from the console.
     *
     * @return The string entered by the user.
     */
    public static String getString() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Closes the scanner when it is no longer needed.
     */
    public static void closeScanner() {
        scanner.close();
    }
}