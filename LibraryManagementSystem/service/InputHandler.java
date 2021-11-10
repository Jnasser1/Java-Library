package lms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHandler {

	public static int InputInt(int minVal, int maxVal) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = 0;

		while (true) {
			try {
				input = Integer.parseInt(br.readLine());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				continue;
			} catch (NumberFormatException nfe) {
				System.out.println("Please enter a valid number.");
				continue;
			}
			// Valid input
			if (input >= minVal && input <= maxVal) {
				break;
			} else {
				System.out.println(
						"Invalid input, please enter a valid number between " + minVal + " and " + maxVal + ".");
			}
		}
		System.out.println("**************************");
		System.out.println("");

		return input;
	}

	public static String InputString() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";

		while (true) {
			try {
				input = br.readLine();
				break;
			} catch (IOException e) {
				System.out.println("Invalid Input, please enter a valid string.");
			} catch (Exception e) {
				System.out.println("Please try again.");
				continue;
			}
		}

		System.out.println("**************************");
		System.out.println("");

		return input;
	}

	public static int InputCardNum() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Cardnum = 0;

		while (true) {
			try {
				Cardnum = Integer.parseInt(br.readLine());
				break;
			} catch (IOException ioe) {
				System.out.println("Error in io");
				continue;
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid Input, please enter a valid card number.");
				continue;

			} catch (Exception e) {
				System.out.println("Please try again.");
				continue;
			}
		}
		System.out.println("**************************");
		System.out.println("");

		return Cardnum;
	}

	public static int Input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = 0;

		while (true) {
			try {
				input = Integer.parseInt(br.readLine());
				break;
			} catch (IOException ioe) {
				System.out.println("Error in io");
				ioe.printStackTrace();
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid Input, please enter a number");
				nfe.printStackTrace();
			}
		}
		System.out.println("");
		System.out.println("");
		return input;
	}
}