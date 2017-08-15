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
	    	
	    	int[] chars1_cnt=new int[26]; //for A:Z, how many times each letter seen in chars1
	    	int[] chars2_cnt=new int[26]; //for A:Z, how many times each letter seen in chars2
	    	
	    	for(int i=0; i<chars1.length; i++){
	    		chars1_cnt[chars1[i]]++;
	    		chars2_cnt[chars2[i]]++;
	    	}
	    	
	    	for(int i=0; i<chars1_cnt.length; i++){
	    		//Case 1: char not found in either string
	    		if(chars1_cnt[i] == 0 || chars2_cnt[i] == 0)
	    		{
	    			chars1_cnt[i]=0; //neither string can use this char in Child
	    			chars2_cnt[i]=0; //neither string can use this char in Child
	    		}
	    	}
	    	
	    	//For each string build a linked list containing common chars in both
	    	ArrayList<Character> chars1_list = new ArrayList<Character>();
	    	ArrayList<Character> chars2_list = new ArrayList<Character>();
	    	
	    	for(int i=0; i<chars1.length; i++){
	    		if(chars2_cnt[chars1[i]] > 0){ //if the other string uses this char
	    			chars1_list.add(chars1[i]);
	    		}
	    		
	    		if(chars1_cnt[chars2[i]] > 0){
	    			chars2_list.add(chars2[i]);
	    		}
	    	}
	    	
	    	//traverse linked lists
	    	int maxChild=0;

	    	for (Character c1: chars1_list){
	    		int child=0;
	    		int i=0;
	    		Character c2=chars2_list.get(i);
	    		while( i<chars2_list.size() && (Character.valueOf(c1) != Character.valueOf(c2))){
	    			i++;
	    			c2=chars2_list.get(i);
	    		}
	    		
	    		if(i < chars2_list.size()){
	    			
	    		}
	    	}
	    	
	    }

	    public static void main(String[] args) {
	        Scanner in = new Scanner(System.in);
	        String s1 = in.next();
	        String s2 = in.next();
	        int result = commonChild(s1, s2);
	        System.out.println(result);
	    }
}


