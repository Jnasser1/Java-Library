import java.io.*;

public class Patterns
{ 
	// A function to skip lines, for easier viewing.
	public static void SkipLines(int k) 
	{
		for (int j=0;j<k;j++) {
		System.out.println(" ");
							  }
	}
	
	

    public static void LowerTriangle(int n) 
    { 
        int i, j;  
        for(i=0; i<=n; i++) 
        {                              
          if ( i< n) {
            for(j=0; j<=i; j++) 
            {       
                System.out.print("* "); 
            }  
            
          	System.out.println(" ");
            		}   
          
          else {
        	  System.out.println(".........");
          		}
         }      
        
    } 
     
    
    public static void UpperTriangle(int n) 
    {
    	int i,j;
    	System.out.print("..........");
    	System.out.println("");
   
    	for (i=n;i>=0;i--) {
    		
    		for(j=i; j>0; j--) {	
    			
    		System.out.print("*");    			
    							}
    		
    	System.out.println("");
    					   }    	       	
    }
    
    
    
    public static void Triangle(int n) 
    {
    	int length = 2*n-1;
    	int space = length;  // Variable spacing
    	
    	// Row loop.
    	for (int i=0; i <= length; i=i+2) 
    	{
    		// Loop for spaces
    		for (int j=0; j<space ; j++) 
    		{  			
    			System.out.print(" ");	
    		}    		
    		space = space-2;   // Reduce spacing 
    		
    		for (int j=0; j<=i; j++) 
    	{    	
    			System.out.print("* ");
    	}    	
    		   System.out.println();
    	}   	   	
    	// Seemed easier to hardcode dotted line.
    	System.out.println("  ...........");
        	      								
	}
    
    
    
    public static void ReverseTriangle(int n) 
    {
    	int temp = 2*n-1;
    	int space = 2;
    	System.out.println(" ..................");
    	
    	// Row loop.
    	for (int i=temp; i >= 0; i=i-2) 
    	{
    		// spacing.
    		for (int j=0; j<=space ; j++) 
    		{  			
    			System.out.print(" ");	
    		} 
    		
    		space=space+2;
    		
    		for (int j=0; j<i; j++) 
    	{    	
    			System.out.print("* ");
    	}    	
    		   
    		System.out.println();
    	}
    	
    	
    		
        	      								
	}
    
    
    
    
    
    public static void main(String args[]) 
    { 
        int n = 4; 
        
        LowerTriangle(n); 
        
        SkipLines(1);     
         UpperTriangle(n);
         
         SkipLines(1);            
      	Triangle(n);
                
      SkipLines(1); 
        ReverseTriangle(n);
        
    } 
    
    
    
    
    
    
    
}