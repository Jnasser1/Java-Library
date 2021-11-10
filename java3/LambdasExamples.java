package jb5;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LambdasExamples {
	public static List<String> StrArr = new ArrayList<>(
			Arrays.asList("string", "array", "this", "is", "a", "JAVA", "k", "E", "for each", "JAVA", "PEARL", "C++"));

	public static List<String> SortLengthAsc(List<String> StrList) {

		StrList.sort((a, b) -> Integer.compare(a.length(), b.length()));

		return StrList;
	}

	public static List<String> SortLengthDesc(List<String> StrList) {

		StrList.sort((a, b) -> Integer.compare(b.length(), a.length()));
		return StrList;
	}

	public static List<String> SortAlphabetFirstChar(List<String> StrList) {
		StrList.sort((a, b) -> a.charAt(0) - b.charAt(0));
		return StrList;
	}

	public static List<String> CustomSort(List<String> StrList) {
		StrList.sort((a, b) -> {
			if (a.contains("e") && b.contains("e")) {
				return 0;
			} else if (a.contains("e")) {
				return -1;
			} else {
				return 1;
			}
		});
		return StrList;
	}

	public static int HelperMethod(String a, String b) {
		if (a.contains("e") && b.contains("e")) {
			return 0;
		} else if (a.contains("e")) {
			return -1;
		} else {
			return 1;
		}
	}

	public static List<String> CustomSortHelper(List<String> StrList) {
		StrList.sort((a, b) -> HelperMethod(a, b));
		return StrList;
	}

	// Problem 2.

	public static String ReturnString(List<Integer> IntList) {

		return IntList.stream().map(i -> {
			if (i % 2 == 0) {
				return "e" + i;
			} else {
				return "o" + i;
			}
		}).collect(Collectors.joining(","));

	}

	// Problem 3.

	public static List<String> FilterStringList(List<String> StringList) {
		return StringList.stream().filter(i -> i.startsWith("a") && i.length() == 3).collect(Collectors.toList());

	}


}
