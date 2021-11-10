package jb3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class AppendTexttoFile {

	// Appending text to file function

	public static void AppendTexttofile(String filename, String text, boolean printtoscreen)
			throws FileNotFoundException {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));

			out.write(text); // Write to output stream
			out.close(); // Close the connection

		} catch (IOException e) {
			System.out.println("Exception in program." + e);
		}

		if (printtoscreen == true) {
			// Printing the file
			try {
				BufferedReader in = new BufferedReader(new FileReader(filename));
				String line = "";

				while ((line = in.readLine()) != null) {
					System.out.println(line);

				}
				in.close();

			}

			catch (IOException e) {
				System.out.println("Exception occured." + e);
			}

		}

	}

	// Driver
	public static void main(String[] args) throws FileNotFoundException {
		// Replace with path of file.
		String filename = "";

		// The text to append to the file.
		String text = "\n This is text, I am appending to an existing file.";

		// Second boolean controls printing the file to stream, .i.e. true = print to
		// screen.
		AppendTexttofile(filename, text, true);

	}
}
