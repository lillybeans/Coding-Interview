package Strings;

import java.io.*;
import java.util.*;

//given strings a and b, find longest palindromic s such that s=substring(a)+substring(b)

public class BuildAPalindrome {
	
	public static int[][] LCS;
	public static int LCS_length=0;
	public static int max_LCS_i=0;
	public static int max_LCS_j=0;
	
	public static void main(String[] args) {

		/*
	    Scanner scanner=new Scanner(System.in);
	    int pairs=Integer.parseInt(scanner.nextLine());
	    
	    for(int i=0; i<pairs; i++){
	      String a = scanner.nextLine();
	      String b = scanner.nextLine();
	      
	      String b_rev=new StringBuilder(b).reverse().toString();

	      System.out.println(palindrome(a,b));
	    }
	    */
	    
		
		
		String a="mlfcpidlqrvngnvttaifcbopnwezesomkxhaiafmvkbjaisyr";
		String b="btultpnxbcrmornqumatserhieqggrivouwfnbnghdfall";
		System.out.println(palindrome(a,b));
		
	      
	  }
	
		public static String palindrome(String a, String b){
			String b_rev=new StringBuilder(b).reverse().toString();
			
			String LCS = LCSString(a,b_rev); //LCS in b
			String LCS_rev = new StringBuilder(LCS).reverse().toString(); //LCS in a
			
			if(LCS == "-1"){
				return "-1";
			}
			
			int LCS_a_end = a.indexOf(LCS_rev)+LCS_rev.length()-1;
			int LCS_b_start = b.indexOf(LCS);

			if(LCS_a_end <= a.length()-1 && LCS_b_start > 0){

				String a_tail=a.substring(LCS_a_end+1,a.length());
				String b_head=b.substring(0,LCS_b_start);

				String a_tail_palindrome=LCSString(a_tail, new StringBuilder(a_tail).reverse().toString());
				String b_head_palindrome=LCSString(b_head, new StringBuilder(b_head).reverse().toString());
				
				if (a_tail_palindrome.length() <=1 && b_head_palindrome.length() <=1){
					return a.charAt(LCS_a_end+1) < b.charAt(LCS_b_start-1)? LCS_rev + a.charAt(LCS_a_end+1) + LCS : LCS_rev + b.charAt(LCS_b_start-1) + LCS;
				}
				
				if (a_tail_palindrome.length() > b_head_palindrome.length())
				{
					if(a.indexOf(a_tail_palindrome) == LCS_a_end+1){
						return LCS_rev + a_tail_palindrome + LCS;
					}
					if (b.indexOf(b_head_palindrome)+b_head_palindrome.length()-1 == LCS_b_start-1)
					{
						return LCS_rev + b_head_palindrome + LCS;
					}
					else return a.charAt(LCS_a_end+1) < b.charAt(LCS_b_start-1)? LCS_rev + a.charAt(LCS_a_end+1) + LCS : LCS_rev + b.charAt(LCS_b_start-1) + LCS;
				}
				else{
					if(a_tail_palindrome.length() == b_head_palindrome.length()){
						if(a.indexOf(a_tail_palindrome) == LCS_a_end+1 && b.indexOf(b_head_palindrome)+b_head_palindrome.length()-1 == LCS_b_start-1){
							return a_tail_palindrome.compareTo(b_head_palindrome) < 0? LCS_rev + a_tail_palindrome + LCS : LCS_rev + b_head_palindrome + LCS;
						}
						else return a.charAt(LCS_a_end+1) < b.charAt(LCS_b_start-1)? LCS_rev + a.charAt(LCS_a_end+1) + LCS : LCS_rev + b.charAt(LCS_b_start-1) + LCS;
					}
					
					//b_head_palindrome > a_tail_palindrome
					if(b.indexOf(b_head_palindrome)+b_head_palindrome.length()-1 == LCS_b_start-1){
						return LCS_rev + b_head_palindrome+ LCS;
					}
					if(a.indexOf(a_tail_palindrome) == LCS_a_end+1){
						return LCS_rev + a_tail_palindrome + LCS;
					}
					else return a.charAt(LCS_a_end+1) < b.charAt(LCS_b_start-1)? LCS_rev + a.charAt(LCS_a_end+1) + LCS : LCS_rev + b.charAt(LCS_b_start-1) + LCS;
				}
			}
			else if(LCS_a_end <= a.length()-1){
				String a_tail=a.substring(LCS_a_end+1,a.length());

				String a_tail_palindrome=LCSString(a_tail, new StringBuilder(a_tail).reverse().toString());
				
				if (a.indexOf(a_tail_palindrome) == LCS_a_end+1 && a_tail_palindrome.length() > 1){
					return LCS_rev+a_tail_palindrome+LCS;
				}
				
				else return LCS_rev + a.charAt(LCS_a_end+1) + LCS;
				
			}
			else if(LCS_b_start > 0){
				String b_head=b.substring(0,LCS_b_start);

				String b_head_palindrome=LCSString(b_head, new StringBuilder(b_head).reverse().toString());
				
				if (b.indexOf(b_head_palindrome)+b_head_palindrome.length()-1 == LCS_b_start-1 && b_head_palindrome.length() > 1){
					return LCS_rev+b_head_palindrome+LCS;
				}
				
				else return LCS_rev + b.charAt(LCS_b_start-1) + LCS;
				
			}
			else{ //no a_tail or b_head
				return LCS_rev + LCS;
			}
		}
	  
	  //LongestPalindrome(a)=LCS(a,a_rev)
	  public static String LCSString(String a, String b){
	    
	    LCS(a,b); //ab across row, ba down column
	    
	    if(LCS_length == 0){ //not found
	    	return "-1";
	    }
	    
	    StringBuilder LCSString = new StringBuilder();
	    for(int i=max_LCS_i; LCS_length > 0; i--){ //assemble the LCS, printing either a/b is fine. Here we choose a. 
	    	LCSString.append(a.charAt(i));
	    	LCS_length--;
	    }
	    
	    return LCSString.toString();
	  }
	  
	  //NOTE: this is a variation of LCSwhere LCS must be contiguous
	  public static void LCS(String s1,String s2){
	    
	    int M=s1.length();
	    int N=s2.length();
	    
	    //MxN: s1 down column, s2 across row
	    
	    LCS=new int[M+1][N+1]; //matrix offset by 1 (makes things easier)
	    LCS_length=-1;
	    
	    for(int i=0; i<=M; i++){ //No ArrayOutOfBounds since we check i-1, j-1
	      for(int j=0; j<=N; j++){
	        
	        if(i==0 || j==0){ //set 1st row, 1st column all 0
	          LCS[i][j]=0;
	          continue;
	        }
	        
	        if(s2.charAt(j-1) == s1.charAt(i-1)){//top left corner=match
	          LCS[i][j]=1+LCS[i-1][j-1]; //1 for the match + whatever was there originally       
	        }
	        else{
	          LCS[i][j]=0; //since must be contiguous, we only check along diagonals from starting points
	        }
	        
	        if(LCS[i][j] > LCS_length){
	        	LCS_length=LCS[i][j];
	        	max_LCS_i=i-1;
	        	max_LCS_j=j-1;
	        }
	        else if(LCS[i][j] == LCS_length){
	        	if(s1.charAt(i-1) < s1.charAt(max_LCS_i)){ //return lower of the two
	        		max_LCS_i=i-1;
	        		max_LCS_j=j-1;
	        	}
	        }
	      }
	    }
	    
	  
	  }
}
