package jb5;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.DateTimeException;

// Problem 5
public class LengthofMonthsinYear {
	public void LengthofMonthsinYr(int input) {

		if (input <= 0) {
			System.out.printf("Year must be a positive number");
			throw new IllegalArgumentException();
		}

		try {
			Year test = Year.of(input);
		} catch (DateTimeException exc) {
			System.out.printf("%d is not a valid year.%n", input);
			throw exc;
		}

		System.out.printf("For the year %d:%n", input);
		for (Month month : Month.values()) {
			YearMonth ym = YearMonth.of(input, month);
			System.out.printf("%s: %d days%n", month, ym.lengthOfMonth());
		}
	}

}
