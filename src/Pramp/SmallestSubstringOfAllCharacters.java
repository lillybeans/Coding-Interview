package Pramp;
import java.util.*;

//Given a string, find the shortest substring of that string that covers all the chars in arr. 
//i.e. str=BACDE, arr=[b,c,d], answer: BACD

//Overall strategy: Sliding window (substring scan) using head and tail
//arr_occur: keeps track of occurrences of chars in str that are found in arr in the sliding window; updates as sliding window updates
//tail: increments counts of occurrences of arr_occur as we scan forward; increments uniqueCounter when it finds a new unique char
//head: decrements counts of occurences of arr_occur as we move head forward; decrements uniqueCounter when it runs out of occurrences of a char
//uniqueCounter: counts number of unique chars in substring found in arr. 
//				A sliding window contains a valid answer as long as uniqueCounter == arr.length (covers all chars in arr)

public class SmallestSubstringOfAllCharacters {
	  static String getShortestUniqueSubstring(char[] arr, String str) {
		  HashMap<Character,Integer> arr_occur = new HashMap<Character,Integer>(); //occurrences for each char in arr
		    
		    for(int i=0; i<arr.length; i++){
		      arr_occur.put(arr[i],0);
		    }
		    
		    //window size
		    int head = 0; 
		    int tail=0;
		    int uniqueCounter=0; //counts number of unique chars in arr found in substring
		    int minLength = Integer.MAX_VALUE;
		    String minString="";
		    
		    for(tail=0; tail<str.length(); tail++){
		      char tail_char=str.charAt(tail); //get current character at tail
		      
		      //step 1: fix head, advance tail and if tail_char is found in arr, increment its occurrence
		      if(arr_occur.containsKey(tail_char)){ //current char in str is found in the set
		        if(arr_occur.get(tail_char) == 0){ //first occurrence
		          uniqueCounter++;
		          arr_occur.put(tail_char,1);
		        }else{
		          arr_occur.put(tail_char,arr_occur.get(tail_char)+1);
		        }
		      }
		      
		      //step 2: as soon as our substring covers all the chars in arr, now we need to minimize its size by advancing head to reduce duplicates 
		      while(uniqueCounter == arr.length){
		    	  char head_char=str.charAt(head);
		    	  if(arr_occur.containsKey(head_char)){ //if head_char found in arr
		    		  if(arr_occur.get(head_char) == 1){ //only occurrence of this char, this is the shortest valid substring we can find with this tail
		    			  if(tail-head+1 < minLength){ 
		    				  minLength = tail-head+1; //update minLength 
		    				  minString = str.substring(head,tail+1); //update minString
		    			  }
		    			  uniqueCounter--; //will break out of this while loop after this iteration so we can increment tail
		    		  }
		    		  arr_occur.put(head_char, arr_occur.get(head_char)-1); //decrease occurrence of this char
		    	  }
		    	  head++; //advance head
		      }
		    }
		    
		    return minString;
	  }
	  
	  public static void main(String[] args) {
		  char[] arr={'A','B','C'};
		  String str="ADOBECODEBANCDDD";
		  String ans=getShortestUniqueSubstring(arr,str);
		  System.out.println("shortest substring of " + str+":"+ans);
	  }
}
