package q1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Game game = new Game();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Bull Pgiaa game!");
		System.out.println("Try to guess the 4-digit number with unique digits.");

		while (true) {
			System.out.print("Enter your guess: ");
			String guess = scanner.nextLine();

			// Input validation
			if (guess.length() != 4 || !Game.isNumeric(guess) || game.hasDuplicateDigits(guess)) {
				System.out.println("Invalid input! Please enter a 4-digit number with unique digits.");
				continue;
			}

			game.incrementAttempts();
			int[] result = game.calculatePgiaasAndBulls(guess);
			int pgiaas = result[0];
			int bulls = result[1];

			System.out.println("Pgiaas: " + pgiaas + ", Bulls: " + bulls);

			if (bulls == 4) {
				System.out.println("Congratulations! You guessed the number in " + game.getNumberOfAttempts() + " attempts.");
				break;
			}
		}
		scanner.close();
	}
}
