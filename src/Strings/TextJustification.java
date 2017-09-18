package Strings;

import java.util.*;

public class TextJustification {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> ans = new ArrayList<String>();
       
        ArrayList<String> line_words = new ArrayList<String>(); //keep track of words per line
        int line_length=0;
        
        for(int i=0; i<words.length; i++){
        	if(line_words.size() > 0) line_length++; //pad with space after last word. DO NOT CHANGE THIS LOGIC IT'S CORRECT.
 
            if(line_length + words[i].length() <= maxWidth){ //can pack this word
                line_words.add(words[i]);
                line_length += words[i].length();
            }else{ //cannot pack word in this line
            	line_length--; //assuming there is at least one word before so we subtract the space padding
                String prev_line=justify(line_words,line_length,maxWidth, false); //pack up previous line, which can never be last line
                ans.add(prev_line); //add previous line to answer
                
                line_words.clear(); //reset so we create a new line
                line_words.add(words[i]); //add current word to new line
                line_length=words[i].length();
            }
            if(i == words.length -1){ //last line and last word
                String line = justify(line_words, line_length, maxWidth, true); //always pack
                ans.add(line);
            }
        }
        return ans;
    }
    
    public static String justify(ArrayList<String> line_words, int line_length, int maxWidth, boolean lastWord){
        
        StringBuilder sb = new StringBuilder();
        
        int words_count = line_words.size();
        int extra = maxWidth - line_length;
        
        if(words_count == 1){ //one word
            sb.append(line_words.get(0));
            for(int i=0; i<extra; i++){
                sb.append(" ");
            }
            return sb.toString();
        }
        
        if(lastWord){
            for(String word: line_words){
                sb.append(word);
                sb.append(" ");
            }
            for(int i=0; i<extra-1; i++){ //"extra-1" since last word already has " " after it
                sb.append(" ");
            }
            return sb.toString();
        }
        
        int extra_per_word= extra/(words_count-1);
        int remaining_spaces = extra % (words_count-1);
        
        for(int i=0; i<line_words.size(); i++){
            sb.append(line_words.get(i));
            if(i != line_words.size()-1){
                sb.append(" "); //1. mandatory space (basic income)
            }   
            for(int j=0; j<extra_per_word; j++){
                sb.append(" "); //2. extra per word (dividend)
            }
            if(remaining_spaces > 0){
                sb.append(" "); //3. append remaining spaces until we run out (first come first serve prizes)
                remaining_spaces--;
            }
        }
        
        return sb.toString();
    }
	
    public static void main(String[] args){
    	String[] words={"a","b","c","d","e"};
    	int maxWidth=3;
    	List<String> ans = fullJustify(words,maxWidth);
    	for(String word: ans){
    		System.out.println("\""+word+"\"");
    	}
    }
}
