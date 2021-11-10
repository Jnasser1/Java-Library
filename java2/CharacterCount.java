package jb3;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.InputMismatchException;

public class CharacterCount {

	public static void CharacterCount(char character, File filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new FileReader(filename));
		int counter = 0;
		String line = ""; // initialize line to be searched.

		try {
			while (reader.hasNextLine()) {
				line = reader.nextLine();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == character) {
						counter++;
					}
				}

			}
			System.out
					.println("The character " + character + " appeared " + counter + " times in the file " + filename);
		} catch (Exception FileNotFoundException) {
			System.out.println("file could not be found.");
		}

		finally {
			if (reader != null) {
				reader.close();
			}
		}
		reader.close();
	}

	public static void main(String[] args) throws IOException {
		// Replace with the path of the file.
		String filename = "C:\\Users\\jihad\\Desktop\\JavaPrograms\\InputAddition.java";

		File file = new File(filename);
		char character = 'i';
		Scanner reader = new Scanner(System.in);

		for (int j = 0; j < 50; j++) {
			System.out.println("Please input the character to search, i.e. 'x'");
			character = reader.next().charAt(0);
			CharacterCount(character, file);

			System.out.println("");
			System.out.println("Would you like to search another character? y/n");
			char input = reader.next().charAt(0);
			try {
				if (input == 'y') {
					continue;

				}
				if (input == 'n') {
					reader.close();
					break;
				}
			} catch (Exception e) {

				e.printStackTrace();

			}
		}

	}
}