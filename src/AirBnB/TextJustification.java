package AirBnB;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public static List<String> fullJustify(String[] words, int maxWidth) {
	    ArrayList<String> ans = new ArrayList<String>();
	   
	    ArrayList<String> lineWords = new ArrayList<String>(); //keep track of words per line
	    int lineLength=0;
	    
	    for(int i=0; i<words.length; i++){
	    	if(lineWords.size() > 0) lineLength++; //pad with space after last word. DO NOT CHANGE THIS LOGIC IT'S CORRECT.
	
	        if(lineLength + words[i].length() <= maxWidth){ //can pack this word
	            lineWords.add(words[i]);
	            lineLength += words[i].length();
	        }else{ //cannot pack word in this line
	        	lineLength--; //assuming there is at least one word before so we subtract the space padding
	            String prevLine=justify(lineWords,lineLength,maxWidth, false); //pack up previous line, which can never be last line
	            ans.add(prevLine); //add previous line to answer
	            
	            lineWords.clear(); //reset so we create a new line
	            lineWords.add(words[i]); //add current word to new line
	            lineLength=words[i].length();
	        }
	        if(i == words.length -1){ //last line and last word
	            String line = justify(lineWords, lineLength, maxWidth, true); //always pack
	            ans.add(line);
	        }
	    }
	    return ans;
	}

	public static String justify(ArrayList<String> lineWords, int lineLength, int maxWidth, boolean lastLine){
	    
	    StringBuilder sb = new StringBuilder();
	    
	    int wordsCount = lineWords.size();
	    int spacesCount = wordsCount-1;
	    int extra = maxWidth - lineLength; //line length includes spaces between words! do not double count
	
	    int extraPerWord=0;
	    int remainingSpaces=0;
	
	    if(!(wordsCount == 1 || lastLine)){ //not left justify: distribute extra among spaces
	        extraPerWord=extra / spacesCount;
	        remainingSpaces=extra % spacesCount;
	    }
	    
	    for(int i=0; i<lineWords.size()-1; i++){ //everything up to but not including last word
	        sb.append(lineWords.get(i));
	        sb.append(" "); //mandatory space per word
	        for(int j=0; j<extraPerWord; j++){
	            sb.append(" "); //extra space per word
	        } 
	
	        if(remainingSpaces > 0){
	            sb.append(" "); //append remainder until we run out
	            remainingSpaces--;
	        }
	    }
	    sb.append(lineWords.get(wordsCount-1)); //append last word
	    if(wordsCount == 1 || lastLine){ //case left justify: append any trailing spaces after last word
	        for(int i=0; i<extra; i++){
	            sb.append(" ");
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
