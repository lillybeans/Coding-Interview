package Strings;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//Remove pairs of adjacent identical chars from string until string has no pairs left
//i: abbac, o: c
//i: baab, o: Empty String
//i: aabcd, o: bcd

public class SuperReducedString {

    public static String super_reduced_string(String s){
        // Complete this function
    	ArrayList<Character> chars=new ArrayList<Character>();
    	for (char c: s.toCharArray()){
    		chars.add(c);
    	}
    	
    	int size_before=chars.size();
    	int size_after=-1;
    	
    	while(size_before != size_after){
    		size_before=chars.size();
    		for(int i=0; i<chars.size(); i++){
    			if(i<chars.size()-1 && chars.get(i) == chars.get(i+1)){
    				chars.remove(i);
    				chars.remove(i); //remove i+1, now at i because we removed one before it;
    			}
    		}
    		size_after=chars.size(); //check new size
    	}
        
    	StringBuilder sb = new StringBuilder();
        for (Character c: chars){
        	sb.append(Character.valueOf(c));
        }
        
        String res=sb.toString();
        
        if (res.length() == 0){
        	return ("Empty String");
        }
        
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = super_reduced_string(s);
        System.out.println(result);
    }
}
