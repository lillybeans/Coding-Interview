import java.util.*; //imports Set, LinkedHashSet

//Remove duplicate characters in a string without additional buffers (variables are fine).

public class removeDuplicates {
	
	public static void main(String[] args)
	{
		
		char[] str = {'a','a','a','b','a','b','c','c'};
		
		char[] res = removeDuplicatesNoBuffer(str);
		for(char c: res)
		{
			System.out.print(c);
		}
		
		/* Method 2: using a LinkedHashSet */
		
		//String myString="aabbcc";
		//System.out.println(removeDuplicates(myString));
	}
	
	
	public static char[] removeDuplicatesNoBuffer(char[] str)
	{
		//Case 1: null string
		if (str == null) return str;
		
		//Case 2: less than 2 chars - can have no duplicates
		if (str.length < 2) return str;
		
		//Case 3: >= 2 chars
		
		//from 0 to tail: sequence of unique chars in the original string
		//tail is intended to store the xth unique char in the string.
		
		//e.x. aaabbbccc, then:
		
		// when tail=0, we should store 'a' at str[tail] 
		// when tail=1, we should store 'b' at str[tail]
		// when tail=2, we should store 'c' at str[tail]
		
		int tail = 1; //before tail: all unique chars from the string
		
		for(int i=1; i<str.length; i++) //go through entire string 
		{
			//i is the index of the potential candidate to be stored at tail
			int j=0;
			
			//go through our sequence of unique chars we found in the string so far
			for(j=0; j<tail; j++)
			{
				if (str[j] == str[i]){ //already have charAt(i) in our sequence of unique chars
					break; //basically: i++ (skip to next char in string array and search for candidate)
				}
			}
			
			if(j == tail) //there were no breaks
			{
				str[tail] = str[i];
				tail++;
			}
		}
		
		str[tail]=0; //to separate modified string from junk after
		return str;
		
	}
	
	public static String removeDuplicates(String s)
	{
		char[] chars = s.toCharArray();
		
		//Create LinkedHashSet to link all the chars in order
		//Set contains unique values, hence duplicates will automatically be skipped
		Set<Character> set=new LinkedHashSet<Character>();
		for(char c: chars){
			set.add(c);
		}
		
		//StringBuilder is most efficient way to build/modify strings
		StringBuilder noDuplicates = new StringBuilder();
		for(char c: set){
			noDuplicates.append(c);
		}
		
		return noDuplicates.toString();
	}
	
}
