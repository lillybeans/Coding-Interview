package Strings;

//Palindromic substring must be continuous
public class LongestPalindromicSubstring {
	
	public static void main(String[] args){
		String s="BACABD";
		System.out.println(LPS(s));
	}
	
	public static String LPS(String s){

		char[] chars=s.toCharArray();
		int n=chars.length;

		//LPS[i][j]=true if str[i...j] is palindrome
		boolean[][] LPS=new boolean[n][n];

		//Length 1(Diagonal): always palindromic
		int maxLength=1;
		int start=0; //index of where longest palindrome starts

		for(int i=0;i<n;i++){
			LPS[i][i]=true;
		}

		//Length 2: palindromic if the two chars equal
		for(int i=0; i<n-1; i++){
			if(chars[i+1] == chars[i]){
				LPS[i][i+1]=true;
				maxLength=2;
				start=i; //update starting position
			}
		}

		//Length 3+
		for (int length=3; length<=n; length++){ 
			for(int i=0; i<n-length+1; i++){ //# of rows we need to check
				int j=i+length-1;

				if(LPS[i+1][j-1] && chars[i] == chars[j]){
					LPS[i][j]=true;

					if(length > maxLength){
						start=i; //update start position to be current cell's i
						maxLength=length;
					}
				}
			} 
		}
		
		return s.substring(start,start+maxLength);
	}
}
