import static java.lang.Math.PI;


class Circle implements ShapeInterface {
	private double radius;

	// Constructor
	Circle(int radius) {
		this.radius = radius;

	}

	// Area method
	public double calculateArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	// Display method
	public void display() {
		System.out.println("The area of the circle with radius " + radius + " is: " + this.calculateArea());

	}
}
