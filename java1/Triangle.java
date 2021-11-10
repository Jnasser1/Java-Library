import static java.lang.Math.PI;

class Triangle implements ShapeInterface {
	private double side1;
	private double side2;
	private double side3;

	// Constructor
	Triangle(int side1, int side2, int side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	// Area method
	public double calculateArea() {
		// Heron's formula.
		double s = (side1 + side2 + side3) / 2;
		double area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
		return area;
	}

	// Display method
	public void display() {

		System.out.println("The area of the triangle with sides of length " + side1 + ", " + side2 + ", and " + side3
				+ " is: " + this.calculateArea());
	}

}