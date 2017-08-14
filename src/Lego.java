import java.io.*;
import java.util.*;
public class Lego {
	
	public static final int mod = 1000000007; 
	public static final int max_size = 1001; //everything is 1-indexed, so index=0 will not be used
	
	public static long[] R = new long[max_size]; //ways of building one row, width:1 to M
	
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    	
    	//Initialize: find all ways to build a single row up to for width 1 to max_size
    	R[1]=1;
    	R[2]=2;
    	R[3]=4;
    	R[4]=8;
    	
    	for (int i=5;i<max_size;i++)
    	{
    		R[i]=R[i-1]+R[i-2]+R[i-3]+R[i-4];
    		R[i]=R[i] % mod;
    	}
    	
    	//read in
    	
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());
        int N,M; //for storing height and width
        
        for (int i=0; i<lines; i++)
        {
        	
            String[] line = scanner.nextLine().split(" "); // e.x. '2 2'
            N=Integer.parseInt(line[0]);
            M=Integer.parseInt(line[1]);
            
            /* For each entry (N,M) */
            
            //1. Create arrays to store values using dynamic programming
            long[] A=new long[M+1]; //A=All -> all the ways to build a wall of fixed height N, width=1 to M
            long[] V=new long[M+1]; //V=Valid -> valid ways to build a wall of fixed hheight N, width=1 to M
            long[] I=new long[M+1]; //I=Invalid -> invalid ways to build a wall of fixed height N, width=1 to M
            
            //Goal: find V[M]=A[M]-I[M]
            
            //2. fill A
            for (int m=1; m<=M; m++){
            	A[m]=all(N,m);
            	//System.out.println("A["+m+"]="+A[m]);
            }
            
            //3. fill V[1]...V[M]
            
            V[1]=1; //if N x 1, then only one valid way
            
            //find V[m] for m=2 to m=M
            //reason: I[M] depends on V[2] to V[M], and we need I[M] to calculate V[M]
            for (int m=2; m<=M; m++)
            {
            	//for a wall of width m, find all of its invalid combinations:
            	for (int split=1; split<m; split++){
                	I[m]+=(V[split]*A[m-split])%mod;
                }
            	
            	I[m] %= mod;
            	
            	V[m] = (A[m] - I[m]) % mod;
            }
            
            //4. Answer stored at V[m]
            
            if (V[M] < 0) //if negative
            {
            	V[M] = V[M] + mod;
            }
            
            //5. print V[m]
            System.out.println(V[M]);
            
        }
    }
        
    public static long all(int N, int m){
    	
    	if (N == 1) { return R[m]; }
    	
    	long prod = R[m];
    	
    	for (int n=2; n<=N; n++){ //find product of N rows
    		prod *= R[m]; 
    		prod = prod % mod;
    	}
    	
    	return prod;
    }
        
    
    
}