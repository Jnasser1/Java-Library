package jb5;

// PerformOperation is an interface, with one method.
// Returning an interface is no different than returning an object because an interface is an object.
// Function space

public class Lambdas {

	public boolean Checker(PerformOperation p, int number) {
		return p.Check(number);
	}

	// These returning methods are deciding what implementation to give: (Isodd, Isprime, Ispal)
	// The interface PerformOperation tells you how to interact with these in a
	// common way ( Check)
	// Returns an implementation of the PerformOperation interface
	
	
	public PerformOperation isOdd() {
		// If a mod 2 = 0 return false (not odd) else return true
		PerformOperation PO = (int a) -> a % 2 == 0 ? false : true;
		return PO;
	}

	public PerformOperation isPrime() {
		PerformOperation PO = (int a) -> {
			if (a == 1) { // 1 is prime.
				return true;
			} else {
				for (int i = 2; i < Math.sqrt(a); i++) {
					if (a % i == 0) { // Every composite number has at least one prime factor less than or equal to the square root of itself
						return false;
					}
				}
			}
			return true;
		};
		return PO;
	}

	public PerformOperation isPalindrome() {
		PerformOperation po = (int a) -> {
			String str = Integer.toString(a);
			String reverse = "";
			for (int i = str.length() - 1; i >= 0; i--) {
				reverse = reverse + str.charAt(i);
			}

			if (reverse.equals(str)) {
				return true;
			}
			return false;
		};
		return po;
	}

}
