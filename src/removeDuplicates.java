import java.util.*; //imports Set, LinkedHashSet

//Remove duplicate characters in a string without additional buffers (variables are fine).

public class removeDuplicates {
	
	public static void main(String[] args)
	{
		String myString="aabbcc";
		System.out.println(removeDuplicates(myString));
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
