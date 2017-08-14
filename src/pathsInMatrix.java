import java.util.*;
import java.io.*;

//array of 0s and 1s
//0: blocked, 1: free. 
//Find all paths going from array[0][0] to array[n][m], where n=#rows, m=#cols, modulo 10^9+7
//can only move right (i,j+1) or down (i+1, j)
//1<= n,m <=1000
public class pathsInMatrix {
	
	public static void main(String[] args){
		
		int[][] array = {
		{1,1,1,1},
		{1,1,1,1},
		{1,1,1,1},
		{1,1,1,1}
		};
		System.out.println(numberOfPaths(array));
				
	}
	
    public static int numberOfPaths(int[][] a) {
    	
    	int n=a.length;
    	int m=a[0].length;
    	long mod=(int)Math.pow(10,9)+7;
    	
    	long[][] paths=new long[n][m]; //dynamic programming to store all possible paths at (i,j)
    	
    	paths[0][0]=a[0][0]; //initialize element at (0,0)
    	
    	if(paths[0][0]==0) { return 0; }
    	
    	//else if first element not 0
    	for (int i=0; i<n; i++){//loop through rows
    		for (int j=0; j<m; j++){//loop through columns
    			
    			if(i==0 && j==0){
    				continue; //skip origin
    			}
    			
    			if(a[i][j]==0) //current element is a block
    			{
    				paths[i][j]=0; //this path leads nowhere
    				continue;
    			}
    			
    			if(i==0){ //first row
    				paths[i][j]=paths[i][j-1]%mod; //check left
    			}
    			else if(j==0){ //first column
    				paths[i][j]=paths[i-1][j]%mod; //check up
    			}
    			else{
    				paths[i][j]=(paths[i][j-1]+paths[i-1][j])%mod; //sum of paths from left and up
    			}
    			
    		}
    	}
    	
    	return (int)paths[n-1][m-1];
    }
}
