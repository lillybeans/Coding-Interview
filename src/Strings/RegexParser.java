package Strings;

//"." = single char wildcard
//"*" = prev char repeated 0 or more times

//text="aa", pattern="a", false
//text="acd", pattern="ab*c.", true

public class RegexParser {
	static boolean isMatch (String text, String pattern){
		if(!pattern.contains("*") && (text.length() > pattern.length() || text.length() < pattern.length())){
			return false;
		}
		
		int cur_index=0; //for text
		
		for(int i=0; i<pattern.length(); i++){
			if(cur_index == text.length()){
				if(i == pattern.length()-1){
					return false;
				}
				for(int j=i; j<pattern.length()-1; j=j+2)
				{
					if(pattern.charAt(j+1)!='*'){
						return false;
					}
				}
			}
			if(text.charAt(cur_index)!=pattern.charAt(i)){
				if(pattern.charAt(i)=='*'){
					continue; //skip this unmatched char and its wildcard all together
				}
				else if(pattern.charAt(i) == '.'){
					cur_index++; //since it can be anything, count it as a match
					continue;
				}
				else if(i==pattern.length()-1){ //last char in pattern
					return false;
				}
				else if(pattern.charAt(i+1) != '*'){
					return false;
				}
			}
			else
			{
				if(pattern.charAt(i+1)=='*'){
					while(text.charAt(cur_index) == pattern.charAt(i)){
						cur_index++;
					}
					i++;
				}
				else
				{
					cur_index++;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args){
		System.out.println(isMatch("abbb","ab*c"));
	}
}
