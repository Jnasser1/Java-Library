package jb5;

public class Recursion {
	public void SubArray(int[] arr) {
	for (int i=0; i<arr.length; i++) 
	{   // If you're in the middle of the array and adjacent values are equal
		if (i>0 && arr[i] == arr[i-1]) {
		// collect values
			arr[i-1] = arr[i-1] + arr[i];
		// If you can still move to the right and the adjacent right value isn't equal to your current, 
		if (i+1< arr.length && arr[i] != arr[i+1]) 
		{
	    // Zero out the current value 
			arr[i] = 0;
		}
		else if (i == arr.length-1) 	
	    // Zero out the current value 
			arr[i] =0;
		}
	}
	}
	
	
	public boolean groupSumClump(int position, int[] arr, int targetsum) {
	SubArray(arr);
	if (position >= arr.length) {return targetsum == 0;}
	if (groupSumClump(position+1,arr,targetsum-arr[position])) {return true;}
	if (groupSumClump(position+1,arr,targetsum)) {return true;}
	else {return false;}
	}
	
	
	
}
