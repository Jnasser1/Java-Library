package test;

import jb5.Functionals;
import jb5.Lambdas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionalsTest {


	static int odd, prime, pal;
	boolean isequal;
	String UnitIntstr, testString1, testString2, testString3;
	Lambdas lambdas = new Lambdas();
	Functionals functions = new Functionals();  
	List<Integer> UnitInt, UnitIntRight, UnitIntdouble;
	List<String> StrListX, StrListNoX;
	
	@BeforeEach
	public void setup() {
		isequal = false;
		odd = 3;
		prime = 5;
		pal = 88;
		UnitInt = new ArrayList<>(Arrays.asList(16,8,886,8,1));
		UnitIntRight = new ArrayList<>(Arrays.asList(6,8,6,8,1));
		UnitIntdouble = new ArrayList<>(Arrays.asList(32,16,1772,16,2));
		StrListX= new ArrayList<>(Arrays.asList("hellox", "hello" , " ", "xxxx", "xjxaxvxa"));
		StrListNoX= new ArrayList<>(Arrays.asList("hello", "hello" , " ", "", "java"));
	}

	@DisplayName("Testing Correctly identifying odd numbers")
	@Test
	public void TestOdd() throws Exception {
		isequal = lambdas.Checker(lambdas.isOdd(),odd);
		assertTrue(isequal);
	}

	@DisplayName("Testing Correctly identifying prime numbers")
	@Test
	public void TestPrime() throws Exception {
		isequal = lambdas.Checker(lambdas.isPrime(),prime);
		assertTrue(isequal);
	}

	@DisplayName("Testing Correctly identifying Palindrome numbers")
	@Test
	public void TestPal() throws Exception {
		isequal = lambdas.Checker(lambdas.isPalindrome(),pal);
		assertTrue(isequal);
	}
	

	@DisplayName("Testing rightDigit")
	@Test
	public void TestrightDigit() throws Exception {
		
		assertEquals(functions.rightDigit(UnitInt),UnitIntRight);
	}
	
	@DisplayName("Testing list doubling")
	@Test
	public void Testdoubling() throws Exception {
		
		assertEquals(functions.doubling(UnitInt),UnitIntdouble);
	}
	
	@DisplayName("Testing noX")
	@Test
	public void TestnoX() throws Exception {
		
		assertEquals(functions.noX(StrListX),StrListNoX);
	}
	
	


}