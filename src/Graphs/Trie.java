package Graphs;

//also see "PalindromePairs", which is implementatino of Trie

public class Trie {
	
	TrieNode root;
	public Trie(TrieNode node){
		root=node;
	}
	
	public class TrieNode{
		char c;
		TrieNode[] children; //max possible children is 26, one for each char in the alphabet
		boolean isLeaf;
		
		public TrieNode(){
			this.children=new TrieNode[26];
		}
	}
	
	//insert a string into the word
	public void insert(String word){
		
		TrieNode current=this.root; //mark current node
		
		for(int i=0;i<word.length();i++){ //go through each char in the word
			
			int x=word.charAt(i) - 'a'; //get int value of char
			if(current.children[x] == null){ //for current node, no child node exists for this char in the word
				current.children[x]=new TrieNode();
			}
			//else exists
			current=current.children[x]; //go to this node, whether it's the newly created or existing node
		}
		//end of string
		current.isLeaf=true;
	}
	
	public boolean search(String word){
		TrieNode current=this.root;
		for(int i=0;i<word.length();i++){
			int x=word.charAt(i)-'a';
			if(current.children[x]!=null){
				current=current.children[x];
			}
			else{
				return false;
			}
		}
		
		return true;
	}

}
