package q1;

import java.util.Random;

public class Game {
	private String secretNumber;
	private int attempts;

	public Game() {
		this.secretNumber = generateSecretNumber();
		this.attempts = 0;
	}
	
	public void incrementAttempts() {
		this.attempts++;
	}
	
	public int getNumberOfAttempts() {
		return this.attempts;
	}

	private String generateSecretNumber() {
		Random random = new Random();
		char[] digits = new char[4];
		boolean[] usedDigits = new boolean[10]; // Array to track digits from 0-9

		for (int i = 0; i < 4; i++) {
			int randomDigit;
			do {
				randomDigit = random.nextInt(10); // Generate a random digit from 0 to 9
			} while (usedDigits[randomDigit]); // Repeat if this digit has already been used

			digits[i] = (char) (randomDigit + '0'); // Convert digit to char
			usedDigits[randomDigit] = true; // Mark this digit as used
		}

		return new String(digits);
	}

	// Calculate pgiaas and bulls for a given guess
	public int[] calculatePgiaasAndBulls(String guess) {
		int pgiaas = 0;
		int bulls = 0;
		boolean[] usedInSecret = new boolean[4];
		boolean[] usedInGuess = new boolean[4];
		// First pass to calculate bulls
		for (int i = 0; i < 4; i++) {
			if (guess.charAt(i) == secretNumber.charAt(i)) {
				bulls++;
				usedInSecret[i] = true;
				usedInGuess[i] = true;
			}
		}
		// Second pass to calculate pgiaas
		for (int i = 0; i < 4; i++) {
			if (!usedInGuess[i]) { // if this position in guess was not used as a bull
				for (int j = 0; j < 4; j++) {
					// Look for this digit in other positions of the secret number
					if (!usedInSecret[j] && guess.charAt(i) == secretNumber.charAt(j)) {
						pgiaas++;
						usedInSecret[j] = true; // Mark as used to avoid double counting
						break;
					}
				}
			}
		}
		return new int[] { pgiaas, bulls };
	}

	

	// Check if the input contains only digits
	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	// Check if a string has duplicate digits
	public boolean hasDuplicateDigits(String number) {
		for (int i = 0; i < number.length(); i++) {
			for (int j = i + 1; j < number.length(); j++) {
				if (number.charAt(i) == number.charAt(j)) {
					return true;
				}
			}
		}
		return false;
	}


}
