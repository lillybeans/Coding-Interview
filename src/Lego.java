import java.io.*;
import java.util.*;
public class Lego {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());
        int n,m=0; //for storing height and width
        
        for (int i=0; i<lines; i++)
        {
            String[] line = scanner.nextLine().split(" "); // e.x. '2 2'
            n=Integer.parseInt(line[0]);
            m=Integer.parseInt(line[1]);
            
            System.out.println(validWays(n,m)%1000000007);
        }
    }
    
    public static int validWays(int n, int m){
        
        int total_ways = (int) Math.pow((oneRowWays(m)),n);
        int invalid_ways = invalidWays(n,m);
        
        return (total_ways - invalid_ways);
    }
    
    public static int oneRowWays(int m){
        if (m == 1) { return 1; }
        else if (m == 2) { return 2; }
        else if (m == 3) { return 4; }
        else if (m == 4) { return 8; }
        
        //5 and above. e.x. for 5 there are only 4 ways to build it: *xxxx, **xxx, ***xx, ****x, where * = block length, x = rest
        return oneRowWays(m-1) + oneRowWays(m-2) + oneRowWays(m-3) + oneRowWays(m-4);
    }
    
    public static int invalidWays(int n, int m){
        
        if (m==1) { return 0; }
        if (m==2) { return 1; }
        
        int invalid_ways=0;
        
        //i is the number of splits, we can have up to maximum m-1 splits
        //for each i:
        //calculate how many ways to have i splits
        //given a fixed split i, calculte the valid ways to arrange remaining blocks divided by the splits
        for(int i=1; i<=m-1; i++)
        {
            invalid_ways += nCr(m-1,i)*validWays(n,m-i);

        }
        
        return invalid_ways;
    }
    
    //Combinations: how many ways of choosing r items from n
    public static int nCr(int n, int r)
    {
        int numerator=1;
    	int denominator=1;
        
    	//after cancelling with bottom factorial, we have: (n-1)(n-2)(n-3)...(r+1)
    	for (int i=n; i>r; i--)
    	{
    		numerator *= i;
    	}
    	
    	for (int i=n-r; i>=1; i--)
    	{
    		denominator *= i;
       	}
    	
    	return (numerator/denominator);
    }
    
}