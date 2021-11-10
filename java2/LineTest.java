package test;

import jb4.Line;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class LineTest {

	
	static Line line1, line2, line22, line0, line2approxt, line2approxf, lineZero;

	@BeforeEach
	public void setUp() {
		
		line1 = new Line(5, 4, 3, 1); // slope 1.5
		line2 = new Line(4, 4, 3, 2); // slope 2
		line22 = new Line(8, 8, 6, 4); // slope 2
		line0 = new Line(8, 8, 6, 8); // slope 0.
		lineZero = new Line(10, 5, 10, 5); // Distance 0.
		line2approxf = new Line(8, 8, 6, 3.9997); // not parallel to line2.
		line2approxt = new Line(8, 8, 6, 4.0001); // parallel to line2.

	}
	
	
	

	@DisplayName("Slope calculation should work")
	@Test
	public void testSlope() {
		assertEquals("Regular slope calculation should work", 1.5, line1.getSlope(), 0.0001);
	}

	@DisplayName("Ensure correct handling of zero slope")
	@Test
	public void testZeroSlope() {
		assertEquals("Slope between two points on same vertical line should be 0.", 0, line0.getSlope(), 0.0001);

	}

	@DisplayName("Distance between two point calculation should work")
	@Test
	public void testDistance() {

		assertEquals("Regular distance calculation should work", 3.605551275463989, line1.getDistance(), 0.0001);
		assertEquals("Regular distance calculation should work", 2, line0.getDistance(), 0.0001);
	}

	@DisplayName("Ensure correct handling of zero distance")
	@Test
	public void testZeroDistance() {
		assertEquals("Distance between two of the same point should be zero", 0, lineZero.getDistance(), 0.0001);

	}

	@DisplayName("Ensure correct handling of parallel lines")
	@Test
	public void testParallel() {
		assertTrue("Two lines are parallel if their slopes are within 0.0001 of eachother.",
				line2.parallelTo(line2approxt));
		assertFalse("Two lines are parallel if their slopes are within 0.0001 of eachother.",
				line2.parallelTo(line2approxf));

	}

	@DisplayName("Ensure a line is parallel to itself")
	@Test
	public void testParallelSelf() {
		assertTrue("Two lines are parallel if their slopes are within 0.0001 of eachother.", line2.parallelTo(line2));
	}

}
