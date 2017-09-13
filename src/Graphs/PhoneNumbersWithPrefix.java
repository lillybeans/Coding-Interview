package Graphs;

import java.util.*;
import java.io.*;

//given string[], output all pairs (i,j) that form a palindrome

// given 10 million phone numbers, efficient return how many start with given prefix
/*

"1234567890"
"1234678905"
"1234678905"
"1243567890"
"1254356789"
"1454356789"

'''
starts with:
"" = 6
"12" = 5
"123" = 3
"0324" = 0
"1454356789" = 1
"123467890" = 2


'''

*/

public class PhoneNumbersWithPrefix{
  static TrieNode tree;

  static class TrieNode{
    TrieNode[] children; //0-9
    int count = 0;
    
    public TrieNode(){
      children = new TrieNode[10];
    }

    public int get_children_length(){ 
      int length=0;
      for(int i=0; i<children.length; i++){
        if(children[i] != null){
          length++;
        }
      }
      return length;
    }
  }

  public static int getNumbersWithPrefix(String prefix){ //search

    TrieNode root = tree;
    TrieNode current = root;
  

    for(int i=0; i<prefix.length() && current != null; i++){
      if(current.children[prefix.charAt(i)-'0'] == null){
        return 0;
      }

      current = current.children[prefix.charAt(i)-'0'];
    }
    
    if(current == null){
      return 0;
    }

    return current.count;

  }

  public static int searchChildren(TrieNode current){
    if(current == null){
      return 1; //leaf node
    }

    int sum_from_children=0;

    for(int i=0; i<current.children.length; i++){
      if(current.children[i] != null){
        sum_from_children += searchChildren(current.children[i]);
      }
    }

    return sum_from_children;
  }

  public static void buildTrie(String[] input){ //insert

    TrieNode root = new TrieNode();

    for(int i=0; i<input.length; i++){ //go through all the numbers
      String s = input[i];
      TrieNode current = root;
      for(int j=0; j<s.length(); j++){ //for each number in input
        if(current.children[s.charAt(j)-'0'] == null){
          current.children[s.charAt(j)-'0'] = new TrieNode(); 
        }
        
        current.count += 1;
        current = current.children[s.charAt(j)-'0'];
      }
    }

    tree = root;
  }
  
  public static void main(String[] args){
    System.out.println("ello world the sequel");
    String [] data = {"1234567890",
                      "1234678905",
                      "1234678905",
                      "1243567890",
                      "1254356789",
                      "1454356789"};
    
    buildTrie(data);
    
    String valueToSearch = "145";
    int total = getNumbersWithPrefix(valueToSearch);
    System.out.println("There are "+total+" many strings in data that start with "+ valueToSearch);
  }

}