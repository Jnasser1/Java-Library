package test;

import jb5.LambdasExamples;

import static org.junit.Assert.assertTrue;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LambdasExamplesTest {

	static List<String> StringArray = LambdasExamples.StrArr;
	static List<String> testStr1 = new ArrayList<>(StringArray);
	static List<String> testStr2 = new ArrayList<>(StringArray);

	static List<String> UnitStr, UnitStrCS, UnitStrAsc, UnitStrDesc, alph, alphsorted, StringtoFilter, FilteredString;
	List<Integer> UnitInt;
	boolean isequal;
	String UnitIntstr, testString1, testString2, testString3;

	@BeforeEach
	public void setup() {
		isequal = false;
		alph = new ArrayList<>(Arrays.asList("ca", "ax", "dc", "eb", "xa", "ab"));
		alphsorted = new ArrayList<>(Arrays.asList("ax", "ab", "ca", "dc", "eb", "xa"));

		UnitStr = new ArrayList<>(Arrays.asList("a", "list", "gr", "owi", "elephant", "little elephant"));
		UnitStrCS = new ArrayList<>(Arrays.asList("elephant", "little elephant", "a", "list", "gr", "owi"));

		UnitStrAsc = new ArrayList<>(Arrays.asList("a", "gr", "owi", "list", "elephant", "little elephant"));
		UnitStrDesc = new ArrayList<>(Arrays.asList("little elephant", "elephant", "list", "owi", "gr", "a"));

		UnitInt = new ArrayList<>(Arrays.asList(3, 44));
		UnitIntstr = "o3,e44";

		StringtoFilter = new ArrayList<>(Arrays.asList("elephant", "all", "al", "a", "zero", "zer", "abc"));
		FilteredString = new ArrayList<>(Arrays.asList("all", "abc"));
	}

	@DisplayName("Tesing Length sort Asc")
	@Test
	public void TestAscSort() throws Exception {
		isequal = LambdasExamples.SortLengthAsc(UnitStr).equals(UnitStrAsc);
		assertTrue(isequal);
	}

	@DisplayName("Tesing Length sort Desc")
	@Test
	public void TestDscSort() throws Exception {
		isequal = LambdasExamples.SortLengthDesc(UnitStr).equals(UnitStrDesc);
		assertTrue(isequal);
	}

	@DisplayName("Tesing Alphabetically sort by first char")
	@Test
	public void TestAlphSort() throws Exception {
		isequal = LambdasExamples.SortAlphabetFirstChar(alph).equals(alphsorted);
		assertTrue(isequal);
	}

	@DisplayName("Testing Helper Method works")
	@Test
	public void TestHelper() throws Exception {
		testString1 = "elephant";
		testString2 = "java";
		testString3 = "none";

		isequal = (LambdasExamples.HelperMethod(testString1, testString3) == 0);
		assertTrue(isequal);

		isequal = (LambdasExamples.HelperMethod(testString1, testString2) == -1);
		assertTrue(isequal);

		isequal = (LambdasExamples.HelperMethod(testString2, testString3) == 1);
		assertTrue(isequal);

	}

	@DisplayName("Testing custom sorts works")
	@Test
	public void TestCustomSort() throws Exception {

		isequal = LambdasExamples.CustomSort(UnitStr).equals((UnitStrCS));

		assertTrue(isequal);

	}

	@DisplayName("Testing custom sort with helper should do the same thing as custom sort")
	@Test
	public void TestCustomSortsSame() throws Exception {

		isequal = LambdasExamples.CustomSort(testStr1).equals(LambdasExamples.CustomSortHelper(testStr2));

		assertTrue(isequal);

	}

	// Test for problem 2 method.
	@DisplayName("Testing comma-seperated string from Int list")
	@Test
	public void TestReturnString() throws Exception {
		isequal = (LambdasExamples.ReturnString(UnitInt).equals(UnitIntstr));
		assertTrue(isequal);
	}

	// Test for problem 3 method.
	@DisplayName("Testing Filter for strings starting with 'a' and length 3")
	@Test
	public void TestFilterStringList() throws Exception {
		isequal = (LambdasExamples.FilterStringList(StringtoFilter).equals(FilteredString));
		assertTrue(isequal);
	}

}
