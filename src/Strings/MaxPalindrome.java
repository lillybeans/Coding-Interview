package Strings;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaxPalindrome {

    static String richieRich(String s, int n, int k){ //n= # digit, k=# digits to change
        
        if (n==1){
            if(k>0){
                return "9"; //return 9
            }
            return s; //return original number
        }
        
        int[] digits=new int[n];
        for(int i=0; i<s.length(); i++){
            digits[i]=s.charAt(i)-'0'; //get int value of char
        }
        
        boolean[] fixed=new boolean[n];
        
        //step 1: palindromize
        int fix=0;
        for(int i=0; i<n/2; i++){
            if(digits[i] != digits[n-1-i]){
                int greater=Math.max(digits[i],digits[n-1-i]);
                if(greater == digits[i]){
                    digits[n-1-i]=digits[i];
                    fixed[n-1-i]=true;
                }else{
                    digits[i]=digits[n-1-i];
                    fixed[i]=true;
                }
                fix++;
            }
        }
        if(k < fix){
            return "-1"; //no solution
        }

        k-=fix; //we have this number of digits left that we can fix


        //step 2: maximize
        for(int i=0; i<n/2; i++){
        	
        	if(digits[i]==9){ //i=9, n-1-i=9
        		continue; //next
        	}

        	if(k>0){ //we can fix at least one number
        		if(!fixed[i] && !fixed[n-1-i]){ //neither fixed
        			if (k>=2){
	        			digits[i]=9;
	        			digits[n-1-i]=9;
	        			k-=2; //fixed i, n-1-i
        			}
        		}
        		else if(fixed[i]){ //i fixed, n-1-i=not fixed
        			digits[i]=9;
        			digits[n-1-i]=9;
        			k--; //fixed n-1-i
	        	}
	        	else{ //i not fixed, n-1-i=fixed
	        		digits[i]=9;
	        		digits[n-1-i]=9;
	        		k--; //fixed i
	        	}
        	}
            
            if(i==n/2-1 && k>0 && n%2==1){ //if n is odd and the next char is the middle
                digits[n/2]=9;
                break;
            }
        }
        
        //3. to string
        
        return convertToString(digits);
    }
    
    public static String convertToString(int[] array){
        String res="";
        for(int i=0; i<array.length; i++){
            res+=array[i];
        }
        return res;
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
