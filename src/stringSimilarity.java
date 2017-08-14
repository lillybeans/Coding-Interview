//string similarity: length of common longest prefix.
//i.e. abc,abd = 2, aaa,aaab=3
//given a string, find the string similarity of the string and each of its suffixes.
//string: ababaa, suffixes: ababaa(6),babaa(0),abaa(3),baa(0),aa(1),a(1)=11
//input: number of test cases, test cases
//sample input:
//1 
//ababaa
//sample output: 11

import java.io.IOException;


public class stringSimilarity {
	
	
	public static void main(String[] args) throws IOException{
		
        String[] strings = {"ababaa"};
        
        int[] res = stringSimilarity(strings);
        for(int i=0; i<res.length;i++){
        	System.out.println(res[i]);
        }
    }
	
    public static int[] stringSimilarity(String[] inputs) {
    	
    	int[] res=new int[inputs.length];
    	
    	for (int i=0; i<inputs.length; i++){//for each string i
    		
    		String s=inputs[i];
    		char[] chars=s.toCharArray();
    		
    		int sum=chars.length; //first suffix is whole string, which is full match
    		
    		for (int j=1; j<chars.length; j++){//for each suffix of the string starting at j
    			
    			int k=0; //running through string i
    			int j_i=j; //running through suffix j
    			int suffix_sum=0;
    			
    			while((j_i)<chars.length && chars[k] == chars[j_i]){ //careful of bounds!
    				k++;
    				j_i++;
    				suffix_sum++;
    			}
    			
    			sum+=suffix_sum;
    		}
    		
    		res[i]=sum;
    	}
    	
    	return res;

    }
}
