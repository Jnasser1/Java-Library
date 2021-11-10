import static java.lang.Math.PI;

class Rectangle implements ShapeInterface {
	private double length;
	private double width;

	// Constructor
	Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	// Area method
	public double calculateArea() {
		return length * width;
	}

	// Display method
	public void display() {
		System.out.println("The area of the rectangle with length " + length + ", and width " + width + " is: "
				+ this.calculateArea());
	}

}
