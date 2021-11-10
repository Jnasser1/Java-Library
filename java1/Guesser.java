import java.io.*;
import java.util.*;


public class Guesser 
{
	
	
	@SuppressWarnings("null")
	public static void main(String args[]) throws IOException 
	{
		
		Scanner reader = new Scanner(System.in);
		System.out.println(" Guess a number in the range 1-100.");
		
		
		
		// Generating a random and setting it between 1-100.
		Random r = new Random();
		int low = 10;
		int high = 100;
		int rand = r.nextInt(high-low) + low;
		
		// Loop for guessing.
		
		for (int j=0;j<5;j++) 
		{
			
		int x = (int) reader.nextInt();
		int difference = Math.abs(x-rand);
	
		if (difference <= 10) {
			System.out.println(" Your answer was witin 10 of the "
					+ "correct one!. The random number was: " + rand);
			break;
		}		
		else if (difference > 10) {
			System.out.println("Please guess again!");
						
									}											
		if (j == 4) {
			System.out.println("Sorry, the number was " + rand);
		}
		}
		
		
		
	}				
}