package Numbers;

public class NumberClosestToTheMean {

	public static int closestToMean(int[] numbers){
		double sum=0;
		for(int i=0; i<numbers.length; i++){
			sum+=numbers[i];
		}
		double mean = sum/numbers.length;
		
		int closest_index=0;
		double smallest_diff=Math.abs(numbers[0]-mean);
		
		for(int i=0; i<numbers.length;i++){
			if(Math.abs(numbers[i]-mean) < smallest_diff){
				closest_index=i;
				smallest_diff=Math.abs(numbers[i]-mean);
			}
		}
		return closest_index;
	}
	
}
