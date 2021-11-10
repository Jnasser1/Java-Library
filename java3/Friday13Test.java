package test;
import jb5.Friday13;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class Friday13Test {

	static String[] Friday13thdate = new String[3];
	boolean isequal;
	@BeforeEach
	public void setup() {
		Friday13thdate[0] = "june";
		Friday13thdate[1]= "13";
		Friday13thdate[2] = "2014";
		isequal = false;
		 
	}
	
	
	
	@DisplayName("Testing a known Friday the 13th date should return true.")
	@Test
	public void TestFriday13th() {
		Friday13 f13 = new Friday13();
		assertTrue(f13.Friday13Check(Friday13thdate));
		Friday13thdate[1] = "14";
		assertFalse(f13.Friday13Check(Friday13thdate));
				
	}
	

}