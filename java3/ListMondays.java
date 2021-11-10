package jb5;

import java.time.Month;
import java.time.Year;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

// Problem 6
public class ListMondays {
	public void ListMondays(String MonthStr) {
		Month month = null; // Initialize month from java.time.Month

		if (MonthStr.length() < 2) {
			System.out.printf("Month names have atleast 3 letters.");
			throw new IllegalArgumentException();
		}

		try {
			month = Month.valueOf(MonthStr.toUpperCase());
		} catch (IllegalArgumentException exc) {
			System.out.printf("%s is not a valid month.%n", MonthStr);
			throw exc; // Exception for string not being a month name.
		}

		System.out.printf("For the month of %s:%n", month);
		LocalDate date = Year.now().atMonth(month).atDay(1). // Initialize a LocalDate object with current year, specified month
				with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // Gets a new Date object which is the first date of specified month that is a MONDAY.
		Month mi = date.getMonth(); // Makes new month field.
		while (mi == month) { // While its the same month
			System.out.printf("%s%n", date); // Print the formatted date (first date is the first monday)
			date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); // Move the date object forward one week to the next monday.
			mi = date.getMonth(); // This line makes sure we don't pass to the next month, so mi is still equal to Month.valueOf(MonthStr)
		}
	}
}
