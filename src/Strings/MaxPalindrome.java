package Strings;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaxPalindrome {

    static String richieRich(String s, int n, int k){ //n= # digit, k=# digits to change
        
        char[] digits=new char[n]; //convert to char array
        for(int i=0; i<s.length(); i++){
            digits[i]=s.charAt(i); 
        }
        
        //1. Calculate minimum fixes needed to palindromize (only need to change one digit to make it same as the other)
        int min_fix=0;
        for(int i=0, j=n-1; i<j; i++, j--){ //i & j, execute as long as i<j
        	if(digits[i] != digits[j]){
        		min_fix++;
        	}
        }
        if(min_fix > k){
        	return "-1";
        }
        
        int fix_both=k-min_fix; //number of fixes left for maximization. This way we can turn a non-9 pair into a 9-pair.
        //e.x. 3114, k=2. min_fix changes 3 into 4, fix_both changes 4 to a 9, 3 to a 9 (free)
        
        //2. Actually fix
        for(int i=0, j=n-1; i<=j; i++,j--){
        	if(digits[i]!=digits[j]){ 
        		
        		char greater=(char)Math.max(digits[i],digits[j]); 
        		
        		digits[i]=greater; //mandatory fix to palindromize
        		digits[j]=greater; //mandatory fix to palindromize
        		min_fix--; //I just fixed either i or j by setting both to max
        		
        		if(greater!='9' && fix_both >=1){ //if fixes left: maximize
        			digits[i]='9';
        			digits[j]='9';
        			fix_both--; //I just fixed the other one, whichever one it was
        		}
        	}
        	else{ //if chars equal
        		if(digits[i]!='9' && fix_both >=2) //if fixes left: maximize
        		{
        			digits[i]='9';
        			digits[j]='9';
        			fix_both-=2;
        		}
        	}
        	
        	if(i==j && fix_both > 0){ //middle character of the string
        		digits[i]='9';
        	}
        }
        
        return new String(digits);
        

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        String s = "11231";
        int n = s.length();
        int k = 2;
        
        String result = richieRich(s, n, k);
        System.out.println(result);
    }
}
