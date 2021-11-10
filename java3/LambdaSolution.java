package jb5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;



public class LambdaSolution { 

	public static void main(String[] args) throws IOException {
		String filename = "C:\\Users\\jihad\\Documents\\SampleInput.txt";
		Lambdas Lambda = new Lambdas();                         // initializing function space object
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(filename));
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("error finding file.");
		}
		
		  boolean returnbool = false;
		  String solStr = " ";
		  int numoftestcases = Integer.parseInt(br.readLine()); // The first line of an input file is one number, the number of values to be tested
		  int N = numoftestcases;
		  while (N-- >0 ) {
		   String s = br.readLine().trim();                     // Read the line with no spaces, i.e. "1 4" read as "14"
		   StringTokenizer st = new StringTokenizer(s);         // Use StringTokenizer to break up each number seperately
		   int testindicator= Integer.parseInt(st.nextToken()); // Parse the first int (1-3) telling what test to perform
		   int num = Integer.parseInt(st.nextToken());          // Parse the next int for the number to be tested
		// cases for different tests
		   if (testindicator == 1) 
		   {    
		    returnbool = Lambda.Checker(Lambda.isOdd(), num);    // If Lambda.Checker(PO.num) returns true, "ODD" is executed
		    solStr = (returnbool) ? "ODD" : "EVEN";              //  else "EVEN" is executed and stored in variable solStr
		   } 
		   else if (testindicator == 2) 
		   {			  
		    returnbool = Lambda.Checker(Lambda.isPrime(), num);
		    solStr = (returnbool) ? "PRIME" : "COMPOSITE";
		   } 
		   else if (testindicator == 3) 
		   {	
		    returnbool = Lambda.Checker(Lambda.isPalindrome(), num);
		    solStr = (returnbool) ? "PALINDROME" : "NOT PALINDROME";
		   }
		   // Prints out result for each line
		   System.out.println(solStr);
		  }
		 }

}
