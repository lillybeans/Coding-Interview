	import java.io.*;
	import java.util.*;
	import java.text.*;
	import java.math.*;
	import java.util.regex.*;

public class CommonChild {

	    public static int commonChild(String s1, String s2){ 
	        // Complete this function
	    	
	    	char[] chars1=s1.toCharArray();
	    	char[] chars2=s2.toCharArray();
	    	
	    	//Given: chars1.length == chars2.length
	    	
	    	//ASCII 'A' to 'Z': 65 to 90
	    	int offset=65;
	    	
	    	//A to Z: record occurence of each letter in the string
	        int[] chars1_count=new int[26]; 
	        int[] chars2_count=new int[26];
	        
	        for (int i=0; i<chars1.length; i++){
	        	chars1_count[chars1[i]]++;
	        	chars2_count[chars2[i]]++;
	        }
	        
	        int maxCommonChild=0;
	        
	        //go through each of the 26 letters in the alphabet
	        for (int i=0; i<26; i++){
	        	if( chars1_count[i] == 0 || chars2_count[i] == 0){ //if current letter not found in either s1 or s2
	        		continue;
	        	}
	        	
	        	//found a possible head
	        	int s1_head = findChar(chars1,(char)(i+offset),1);
	        	int s2_head = findChar(chars2,(char)(i+offset),1);
	        	
	        	int commonChild=0;
	        	for (int i1=s1_head; i1<chars1.length; i1++){
	        		for (int i2=s2_head; i2<chars2.length;i2++){
	        			if (chars1[i1] == chars2[i2]){
	        				commonChild++;
	        				continue;
	        			}
	        		}
	        	}
	        }
	        
	        return commonChild;
	    }
	    
	    public static int findChar(char[] chars, char c, int occurrence){
	    	int cnt=0;
	    	for (int i=0; i<chars.length;i++){
	    		if(chars[i]==c){
	    			cnt++;
	    			if (cnt == occurrence){
	    				return i;
	    			}
	    		}
	    	}
	    	return -1; //not found
	    }

	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        String s1 = in.next();
	        String s2 = in.next();
	        int result = commonChild(s1, s2);
	        System.out.println(result);
	    }
}


