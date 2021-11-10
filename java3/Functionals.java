package jb5;

import java.util.List;

public class Functionals {

	public List<Integer> rightDigit(List<Integer> IntList) {
		IntList.replaceAll(a -> a % 10);
		return IntList;
	}

	public List<Integer> doubling(List<Integer> IntList) {
		IntList.replaceAll(a -> a * 2);
		return IntList;
	}

	public List<String> noX(List<String> StrList) {
		StrList.replaceAll(str -> str.replace("x", ""));
		return StrList;
	}

}