import java.util.Random;
import java.util.Random;
import static java.lang.Math.PI;
import java.io.IOException;

class ArrayMax {

	// Initialize new random object to generate random 2d array.
	public static int[][] GenerateArray(int n) {
		Random rand = new Random();
		int[][] array = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				array[i][j] = rand.nextInt();
			}
		}

		// Print array
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("");
		}
		return array;
	}

	// Get maximum of array
	public static int[] GetMax(int[][] array) {
		int maxval = array[0][0];
		int n = array.length;

		int l = 0;
		int k = 0; // For tracking index
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (array[i][j] > maxval) {
					maxval = array[i][j];
					l = i;
					k = j;
					continue;
				} else {
					continue;
				}
			}
		}

		int[] arr = new int[] { maxval, l, k };
		return arr;
	}

	// Generate a random 2d array (matrix) with dimensions n x n, then find max.
	public static void main(String[] args) throws IOException {
		int n = 5;
		System.out.println("The generated array is given by:");
		System.out.println(" ");
		int[][] array = GenerateArray(n);
		int[] maxval = GetMax(array);
		System.out.println(" ");
		System.out.println("The maximum value is: " + maxval[0] + ", it is located at position (i,j,) = " + "("
				+ maxval[1] + "," + maxval[2] + ").");
	}

}
