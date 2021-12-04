/*
* Monique Klaassen
* Oct 2015
* GCVI
*
*this class contains methods that involve number manipulation
*/
package mymethods;


public class NumCalc {
    
    //returns a random number between 2 non-negative integers
    //returns -1 if the parameters are not both non-negative
    //or if high is less than low
    public static int random(int low, int high)
    {
        int num=low;
        
        if (  (high < low) || (high<0) || (low<0) ) num=-1;
        else
            
        {
            num = (int) (low+Math.random()*(high-low+1));
        }
        
        return num;
    }
    
    //rounds num to the number of decimal places determined by decPlaces
    // if decPlaces is < 0 then 0 is returned
    
    public static double round(double num, int decPlaces)
    {
        double answer =0;
        
        if (decPlaces<0) answer = 0;
        
     else
        
        answer =(int)(num*Math.pow(10,decPlaces)+(Math.signum(num)*.5))/Math.pow(10,decPlaces);
        
        
        
        return answer;
    }
}
