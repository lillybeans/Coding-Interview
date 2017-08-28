package Strings;

import java.util.*;
import java.io.*;

//given string[], output all pairs (i,j) that form a palindrome

//input: [abcd,dcba,lls,s,sssll]
//output: (0,1), (1,0), (3,2), (2,4)


public class PalindromePairs {
	
	public static ArrayList<ArrayList<Integer>> pairs;
	
	public static class TrieNode{ //must have static to be referenced inside the class
		char c;
		TrieNode[] children; //max 26 children, each char in the alphabet
		ArrayList<Integer> palindromeSoFar; //stores id of all strings that are palindromic up to this node from i=0
		int id; //stores at leaf the index of this string in the string array
		boolean isLeaf;
		
		public TrieNode(){
			this.children=new TrieNode[26];
			this.palindromeSoFar=new ArrayList<Integer>();
		}
	}
	
	//insert the reverse of word
	public static void insertReverse(TrieNode root, String word, int id){
		TrieNode current=root; //we will update current as we go further down the levels
		
		for(int i=word.length()-1; i>=0; i--){
			int x=word.charAt(i)-'a';
			if(current.children[x]==null){ //no child node for this char
				current.children[x]=new TrieNode();
			}
			if(isPalindrome(word,0,i)){
				current.palindromeSoFar.add(id); //palindrome from (0,i) for current id
			}
			current=current.children[x]; //else go to the newly constructed or existing child node
		}
		
		//end of word: record id and mark leaf true
		current.id=id;
		current.isLeaf=true;
		current.palindromeSoFar.add(id); //at this point, 1 letter left, always a palindrome
		
	}
	
	public static void search(TrieNode root, String word, int id){
	
		TrieNode current=root;
		for(int i=0; i<word.length();i++){
			int x=word.charAt(i)-'a';
			//found, follow branch to next level
			if(current.children[x]!=null){
				current=current.children[x];
				continue;
			}
			
			//Case 1: exhausted current but search word still has chars left, then we check remaining of word is palindrome
			else if(current.isLeaf && current.id != id){
				
				if(isPalindrome(word,i,word.length()-1)){
					ArrayList<Integer> matching_pair=new ArrayList<Integer>();
					matching_pair.add(id); //search word's id
					matching_pair.add(current.id); //id of string that we found in the tree
					pairs.add(matching_pair);
				}
				else //remaining not palindrome, not found
				{
					return;
				}
			}
			
			else{ //null, not found
				return;
			}
			
		}
		
		//Case 2: exhausted search word but tree still has nodes left, then we check for partial match case
		for(int matching_id:current.palindromeSoFar){ //for each string matching_id who is a palindrome below this point, they are all matches for id
			if(matching_id != id) //not itself
			{
				ArrayList<Integer> matching_pair=new ArrayList<Integer>();
				matching_pair.add(id); //search word's id
				matching_pair.add(matching_id); //id of string that we found in the tree
				pairs.add(matching_pair);
			}
		}
	}

	public static boolean isPalindrome(String word, int start, int end){
		while(start < end){
			if(word.charAt(start)!=word.charAt(end)){
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
	
    public static ArrayList<ArrayList<Integer>> palindromePairs(String[] words) {
        pairs=new ArrayList<ArrayList<Integer>>();
        TrieNode root=new TrieNode();
        for(int i=0; i<words.length; i++){
        	insertReverse(root,words[i],i);
        }
        
        for(int i=0; i<words.length; i++){
        	search(root,words[i],i);
        }
        
        return pairs;
    }
	
	public static void main(String[] args){
		String[] words={"abcd","dcba","lls","s","sssll"};
		palindromePairs(words);
		
		for(ArrayList<Integer> pair:pairs){
			System.out.println(pair.get(0) + "," + pair.get(1));
		}
	}
}
