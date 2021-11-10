import java.io.*;
import java.util.*;
import java.util.HashMap;
import static java.lang.Math.PI;

public class InputAddition 
{
	
	
	@SuppressWarnings("null")
	public static void main(String args[])
	{
		
		@SuppressWarnings("unused")
		Scanner reader = new Scanner(System.in);
		System.out.println(" Please input numbers to add and type a string when done.");
		
		List<Double> list = new ArrayList<Double>();
		while (reader.hasNextDouble()) {
			list.add(reader.nextDouble());
		}
		int n = list.size();
		double[] array = list.stream().mapToDouble(i->i).toArray();		
				
		double sum = 0;
		for (int j=0; j< n; j++) {
			sum = sum + array[j];
		}
		
		System.out.println("The sum of the numbers entered is: " + sum);
	
		
		
		
	}				
}