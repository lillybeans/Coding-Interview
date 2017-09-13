package Strings;

import java.util.HashSet;
// find longest substring of all unique chars
// abcdab -> abcd
// bbbbb -> b
//gghigl -> higl

public class LongestUniqueSequence {
	
	public int lengthOfLongestSubstring(String s) {
	        
	        int longest=1;
	        
	        for(int i=0; i<s.length();i++){
	            for(int j=i+1; j<s.length(); j++){
	                if(allUnique(s,i,j) && j-i+1>longest){
	                    longest=j-i+1;
	                }
	            }
	        }
	        
	        return longest;
	        
	    }
	    
    public boolean allUnique(String s, int start, int end){
        HashSet<Character> set = new HashSet<Character>();
        for(int i=start; i<=end; i++){
            if(set.contains(s.charAt(i))){
                return false;
            }
           set.add(s.charAt(i));
        }
        return true;
    }
}
