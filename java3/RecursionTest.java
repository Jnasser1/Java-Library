package test;


import jb5.Recursion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecursionTest {


	static int[] intarr1,intarr2,intarr3;
	
	boolean isequal;
	
	Recursion groupbyfn = new Recursion();

	@BeforeEach
	public void setup() {
	
		isequal = false;
		
	}

	@DisplayName("Testing Recursive GroupSum works")
	@Test
	public void TestGroupClumpSum() throws Exception {
		intarr1 = new int[]{2,4,8};
		intarr2 = new int[]{1,2,4,8,1};
		intarr3 = new int[]{2,4,4,8};
		
		isequal = groupbyfn.groupSumClump(0, intarr1, 10);
		assertTrue(isequal);
		isequal = groupbyfn.groupSumClump(0, intarr2, 14);
		assertTrue(isequal);
		isequal = groupbyfn.groupSumClump(0, intarr3, 14);
		assertFalse(isequal);
	}
}
