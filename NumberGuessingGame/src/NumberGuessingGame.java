import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    // ANSI escape codes for text color and clearing the screen
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_CLEAR_SCREEN = "\033[H\033[2J";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numberToGuess = random.nextInt(1000) + 1;
        int numberOfAttempts = 0;
        int previousGuess = -1;
        int currentGuess = -1;

        clearScreen();
        System.out.println(ANSI_GREEN + "Welcome to the Number Guessing Game!" + ANSI_RESET);
        System.out.println("Guess a number between 1 and 1000:");

        while (currentGuess != numberToGuess) {
            System.out.print("Enter your guess: ");
            currentGuess = scanner.nextInt();
            numberOfAttempts++;

            if (currentGuess == numberToGuess) {
                System.out.println(ANSI_GREEN + "Congratulations! You've guessed the number in " + numberOfAttempts + " attempts." + ANSI_RESET);
            } else {
                if (previousGuess != -1) {
                    if (Math.abs(currentGuess - numberToGuess) < Math.abs(previousGuess - numberToGuess)) {
                        System.out.println(ANSI_YELLOW + "     Warmer" + ANSI_RESET);
                    } else {
                        System.out.println(ANSI_RED + "     Colder" + ANSI_RESET);
                    }
                } else {
                    System.out.println("Try again!");
                }

                if (currentGuess < numberToGuess) {
                    System.out.println("The number is higher than " + currentGuess + ".");
                } else {
                    System.out.println("The number is lower than " + currentGuess + ".");
                }

                previousGuess = currentGuess;
            }
        }

        scanner.close();
    }

    // Method to clear the screen
    public static void clearScreen() {
        System.out.print(ANSI_CLEAR_SCREEN);
        System.out.flush();
    }

    // Method to print text centered on the screen
    public static void printCentered(String text) {
        int width = 80;
        int padding = (width - text.length()) / 2;
        String format = "%" + padding + "s%s%" + padding + "s";
        System.out.println(String.format(format, "", text, ""));
    }
}
