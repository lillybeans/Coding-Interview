package Strings;

import java.io.*;
import java.util.*;


//given string BCABC, find longest palindrome hidden in the string. Answer: BAB.

public class LongestPalindrome {

	  public static void main(String[] args) {
	    Scanner scanner=new Scanner(System.in);
	    int pairs=Integer.parseInt(scanner.nextLine());
	    
	    for(int i=0; i<pairs; i++){
	      String a = scanner.nextLine();
	      String b = scanner.nextLine();
	      
	      int[][] LCS_a_b=LCS(a,b);
	      if(LCS_a_b[b.length()][a.length()]==0){
	        System.out.println("-1");
	      }
	      else
	      {
	        String ab=a+b;
	        String ba=new StringBuilder(ab).reverse().toString();

	        int N=ab.length();

	        System.out.println(palindrome(ab,ba,N,N));
	      }
	    }
	  }
	  
	  //LongestPalindrome(a)=LCS(a,a_rev)
	  public static String palindrome(String ab, String ba, int i, int j){
	    
	    int[][] LCS=LCS(ab,ba); //ab across row, ba down column
	    
	    if(i==0 || j==0){ //reached first row or column, which must be all 0s
	      return "";
	    }
	    
	    if(ab.charAt(j-1) == ba.charAt(i-1)){ //indices-1 because of offset
	      return ab.charAt(j-1) + palindrome(ab,ba,i-1,j-1);
	    }
	    
	    //else check LCS in top and left of current, we want the path with longer one
	    int top=LCS[i-1][j];
	    int left=LCS[i][j-1];
	    
	    if(top > left){
	      return palindrome(ab,ba,i-1,j); //return top
	    }
	    else if(left > top){
	      return palindrome(ab,ba,i,j-1); //return left
	    }
	    else{ //left == top, return lower
	      String top_palindrome=palindrome(ab,ba,i-1,j);
	      String left_palindrome=palindrome(ab,ba,i,j-1);
	      int compare_top_left=top_palindrome.compareTo(left_palindrome);
	      
	      return compare_top_left < 0 ? top_palindrome:left_palindrome;
	    }
	      
	  }
	  
	  public static int[][] LCS(String s1,String s2){
	    
	    int N=s1.length();
	    int M=s2.length();
	    
	    //MxN: s1 across row, s2 down column 
	    
	    int[][] LCS=new int[M+1][N+1]; //matrix offset by 1 (makes things easier)
	    
	    for(int i=0; i<=M; i++){ //No ArrayOutOfBounds since we check i-1, j-1
	      for(int j=0; j<=N; j++){
	        
	        if(i==0 || j==0){ //set 1st row, 1st column all 0
	          LCS[i][j]=0;
	          continue;
	        }
	        
	        if(s1.charAt(j-1) == s2.charAt(i-1)){//top left corner=match
	          LCS[i][j]=1+LCS[i-1][j-1]; //1 for the match + whatever was there originally       
	        }
	        else{
	          LCS[i][j]=Math.max(LCS[i-1][j],LCS[i][j-1]); //max(top,left)
	        }
	        
	      }
	    }
	    
	    return LCS;
	  
	  }
}
