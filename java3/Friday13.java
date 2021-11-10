package jb5;

import java.time.Month;
import java.time.Year;
import java.time.LocalDate;

import java.time.DateTimeException;

//Input date as a string array, i.e. ["july" "26" "1996"]

public class Friday13 {

	public void Friday13Check(String[] input) {
		Month month = null;
		LocalDate date = null;
        LocalDate dat = null;
		if (input.length < 2) {
			System.out.printf("Date must have three components.");
			throw new IllegalArgumentException();
		}

		try {

			month = Month.valueOf(input[0].toUpperCase());
		} catch (IllegalArgumentException exc) {
			System.out.printf("%s is not a valid month name.%n", input[0]);
			throw exc;
		}

		int day = Integer.parseInt(input[1]); // Parses second element for day, i.e. "26" becomes day 26.
		int  inputyr = Integer.parseInt(input[2]); // Parses third element for year.
		try {
			
			dat = Year.now().atMonth(month).atDay(day);     // Get LocalDate object with current year, specified month and day.
			
			date = dat.withYear(inputyr);                   // Set the year to the given year.
					
		} catch (DateTimeException exc) {
			System.out.printf("%s %s is not a valid date.%n", month, day);
			throw exc;
		}

		System.out.println(date.query(new Friday13query()));
		// Friday13query() is the Overridden method from TemporalQuery interface to check if the date is a
		// Friday the 13th.

	}

}
