package Numbers;

import java.util.Arrays;

public class Grants {

	public static double grantsAwards(double[] grantsArray, double newBudget){
		
		// your code goes here
	    Arrays.sort(grantsArray); //sort array in ascending order so we can find max cap later
	    
			int n = grantsArray.length;
			double[] grantsSum = new double[n];
			grantsSum[0]=grantsArray[0]; //grantsSum[i]=sum(grantsArray[0]....grantsArray[i])
			
			for(int i=1; i<n; i++){
				grantsSum[i]=grantsSum[i-1]+grantsArray[i];
				System.out.println("sum: "+grantsSum[i]);
			}
			
			double cap=0;
			
			for(int i=n-1; i>=0; i--){
				if(grantsSum[i] <= newBudget){ //first sum that satisfies budget
					double remainder = newBudget - grantsSum[i];
					double remainder_distributed = remainder/(n-1-i);
					if(remainder_distributed >= grantsArray[i])
					{
						cap = remainder/(n-1-i);
						break;
					}else if(i==0){
						cap=newBudget/n;
					}
				}
				else if(i == 0){
					cap = newBudget/n;
				}
			}
			
			return cap;
	}
	
	public static void main(String[] args){
		double[] grantsArray={2,100,50,120,167};
		double res = grantsAwards(grantsArray,400);
		System.out.println("res: "+res);
	}
}
