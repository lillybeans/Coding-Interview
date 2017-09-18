package Strings;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LongestCommonSubsequence { //not necessarily continuous, as long as relative order is maintained. See main method for example

public static int commonChild(String s1, String s2){ 
    // Complete this function
	
	int n=s1.length(); //n chars in both strings
	
	char[] chars1=s1.toCharArray();
	char[] chars2=s2.toCharArray();
	
	int[][] lcs=new int[n][n]; 
	
	for (int i=0; i<n; i++){ //chars2
		for(int j=0; j<n; j++){ //chars1
			
			if(i==0 || j==0){
				if(chars2[i]==chars1[j]){
					lcs[i][j]=1; //current=1
				}
    			//first row
    			if(i==0 && j>0){
    				lcs[i][j]=Math.max(lcs[i][j],lcs[i][j-1]); //current=max(left,current)
    			}
    			//first column
    			else if(j==0 && i>0){
    				lcs[i][j]=Math.max(lcs[i][j],lcs[i-1][j]); //current=max(top,current)
    			}
			}

			else if(chars2[i]==chars1[j]){
				lcs[i][j]=1+lcs[i-1][j-1];
			}
			else{
				lcs[i][j]=Math.max(lcs[i-1][j],lcs[i][j-1]);
			}
		}
	}
	
	return lcs[n-1][n-1];
	
}


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = "MCB";
        String s2 = "MAC";
        int result = commonChild(s1, s2);
        System.out.println("Longest common subsequence between " + s1 +" and " + s2 + " is :"+result);
    }
}


