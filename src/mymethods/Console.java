
package mymethods;

//Author: M. Klaassen
//Oct. 30, 2014
//ICS3UC GCVI

//external methods pertaining to input from keyboard (console)
//using Scanner class

import java.util.Arrays;
import java.util.Scanner;

public class Console {
   
 	// member variables - accessible to this class only (private)
 	
 	private static Scanner keyboard = new Scanner(System.in);
 	
 	public static final String RESET = "\u001B[0m";
        public static final String WHITE = "\u001B[37m";
        public static final String RED = "\u001B[31m";
        public static final String PINK = "\u001B[38m"; //or may be 41
        public static final String MAGENTA = "\u001B[35m";
        public static final String YELLOW = "\u001B[33m";
        public static final String GREEN = "\u001B[32m";
        public static final String CYAN = "\u001B[36m";
        public static final String LTBLUE = "\u001B[39m";
        public static final String BLUE = "\u001B[34m";
        
        public static final String REDBKG = "\u001B[41m";
        public static final String YELLOWBKG = "\u001B[43m";
        public static final String GREENBKG = "\u001B[42m";
        public static final String CYANBKG = "\u001B[46m";
        public static final String BLUEBKG = "\u001B[44m";
        
        
        //***********************************************************
	// METHODs to accept INTEGER from the user
        //***********************************************************
	
	// get a valid integer from the console
        public static int getInt(String prompt)
	{
                int num = 0;
		System.out.print(prompt);
		
                //while the user does not input an integer (keyboard does not have an integer)
		while(keyboard.hasNextInt() == false)
		{
			keyboard.nextLine();   // clear input
			System.out.println("\tInvalid entry, must be an integer.");
			System.out.print(prompt);  
		}
		
		// loop terminates when valid integer is entered
		num = keyboard.nextInt();  // we know the input is valid so get it
		keyboard.nextLine(); // flush the input stream
		return(num);
	}
	
	
	
	// get a valid integer between low and high range
	public static int getInt(String prompt,int lowRange,int highRange)
	{
					
		int intNum = getInt(prompt);
		
		while (!(intNum >= lowRange && intNum <= highRange))
		{
			System.out.println("\tInvalid entry, out of range.");
			System.out.println("\tRange is from " + lowRange + " to " + highRange + ".");
						
			intNum = getInt(prompt);	
		}
		
		return intNum;
	}
	
	
	//get a valid integer >= low 
	public static int getInt(String prompt,int lowRange)
	{
		
		int intNum = getInt(prompt);
		
		while (!(intNum >= lowRange))
		{
			System.out.println("\tInvalid entry, out of range.");
			System.out.println("\tMust be greater than or equal to " + lowRange + ".");
						
			intNum = getInt(prompt);
			
		}
		
		return intNum;
	}
	
        //***********************************************************
	// METHODs to accept DOUBLE from the user
        //***********************************************************
        
        
	// get a valid real number from the console
      	public static double getDbl(String prompt)
	{
            double dblNum=0;
            System.out.print(prompt);
		
		while(keyboard.hasNextDouble() == false)
		{
			keyboard.nextLine();   // clear input
			System.out.println("not a real number - please try again ");
			System.out.println(prompt);
		}
		
		// loop terminates when valid double is entered
		dblNum=keyboard.nextDouble();  // we know the input is valid so get it
		keyboard.nextLine(); // flush the input stream
		return(dblNum);
	}
        
        // get a valid real number between low and high range
	public static double getDbl(String prompt,double lowRange,double highRange)
	{
		double dblNum = getDbl(prompt);
		
		while (!(dblNum >= lowRange && dblNum <= highRange))
		{
			System.out.println("Entry is out of range");
			System.out.println("Range is from " + lowRange + " to " + highRange);
						
			dblNum = getDbl(prompt);
			
		}
		
		return dblNum;
	}
	
	
	//get a valid real number >= low 
	public static double getDbl(String prompt,double lowRange)
	{
		double dblNum = getDbl(prompt);
		
		while (!(dblNum >= lowRange))
		{
			System.out.println("Entry is out of range");
			System.out.println("Must be greater than or equal to " + lowRange);
						
			dblNum = getDbl(prompt);
			
		}
		
		return dblNum;
	}
	
        //get a valid real number >= low 
        //and a specific number of decimal places
	public static double getDbl(String prompt,double lowRange,int numDecPlaces)
	{
		double dblNum = getDbl(prompt);
		
		while (!((dblNum >= lowRange)&&NumCalc.round(dblNum,numDecPlaces)==dblNum))
		{
			System.out.println("Entry must be greater than or equal to "+
                                lowRange + " and " + numDecPlaces + " decimal places");
			
						
			dblNum = getDbl(prompt);
			
		}
		
		return dblNum;
	}
        
        //get a valid real number  
        //and a specific number of decimal places
	public static double getDbl(String prompt,int numDecPlaces)
	{
		double dblNum = getDbl(prompt);
		
		while (! (NumCalc.round(dblNum,numDecPlaces)==dblNum) )
		{
			System.out.println("Entry must be "
                                 + numDecPlaces + " decimal places");
			
						
			dblNum = getDbl(prompt);
			
		}
		
		return dblNum;
	}
 }

