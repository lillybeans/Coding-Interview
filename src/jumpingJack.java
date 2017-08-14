import java.util.HashMap;
import java.util.Map;


public class jumpingJack {
	
	//n<=2000
	//k<=4,000,000
	
	public static void main(String[] args){
        
		int n=4;
		int k=3;
		
		System.out.println(maxStep(n,k));
	}

	public static int maxStep(int n, int k){
		
		//landing_step: storing which step we would end up at each level if we jumped at every time
		//(key: i, value: landing_step) 
		Map<Integer, Integer> landing_step=new HashMap<Integer, Integer>();
		
		int sum=0; //start at step 0
		
		for (int i=1; i<= n; i++){
			
			sum+=i; //add current step
			landing_step.put(i, sum);
		}
		
		//no problem
		if(!landing_step.containsValue(k)){
			return landing_step.get(n).intValue();
		}
		else{ //just skip the step at i=1
			return landing_step.get(n).intValue()-1;
		}
	}
}
	
	