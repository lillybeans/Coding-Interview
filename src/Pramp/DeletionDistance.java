package Pramp;

import java.io.*;
import java.util.*;

//"is"
//"so"

//when looking first char, deleting either is the same, but when comparing second char, we compare minimum between
//"is" and "o", and "i" and "so", and we find deleting "i" from is and "o" from "so" is better than deleting all 4.

class DeletionDistance {

  static int deletionDistance(String str1, String str2) {
    
    int len1=str1.length();
    int len2=str2.length();
    int[][] memo = new int[len1+1][len2+1]; //since we are checking previous char
    
    for(int i=0; i<=len1; i++){ //down the rows
      for(int j=0; j<=len2; j++){ //across columns
        if(i==0){
          memo[i][j]=j; //if str1's length is 0, max deletion is entire str2, which has length j
        }else if(j==0){
          memo[i][j]=i; //if str2's length is 0, max deletion is entire str1, which has length i
        }else if(str1.charAt(i-1) == str2.charAt(j-1)){ //if previous chars equalled, same deletion count as deletion count as previous char
          memo[i][j]=memo[i-1][j-1];
        }else{ //current char's don't equal, we need to delete at least one of them, but we need to find optimum. i.e. [isol, sole], better to delete first "i" than "s"
          memo[i][j]=1+Math.min(memo[i-1][j],memo[i][j-1]); 
        }
      }
    }
    
    return memo[len1][len2];
  }

  public static void main(String[] args) {

  }

}