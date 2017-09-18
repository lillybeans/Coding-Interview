package Strings;

public class LongestCommonSubstring {
	
	static int LCSubStr(char X[], char Y[], int m, int n) 
    {
        // continuous
		
        int LCStuff[][] = new int[m + 1][n + 1];
        int result = 0;  // To store length of the longest common substring
         
        // Following steps build LCSuff[m+1][n+1] in bottom up fashion
        for (int i = 0; i <= m; i++) 
        {
            for (int j = 0; j <= n; j++) 
            {
                if (i == 0 || j == 0) //corners
                    LCStuff[i][j] = 0;
                else if (X[i - 1] == Y[j - 1]) //if previous chars equal
                {
                    LCStuff[i][j] = LCStuff[i - 1][j - 1] + 1;
                    result = Integer.max(result, LCStuff[i][j]);
                } 
                else
                    LCStuff[i][j] = 0;
            }
        }
        return result;
    }

}
