package Numbers;
import java.util.*;

//input: candidates= [2,2,3,5] (may contain duplicates), target=7
//output: [2,2,3],[2,5]
class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        Arrays.sort(candidates);
        
        List<List<Integer>> ans = findNumbersSumToTarget(candidates,0,target);
        
        return ans;
    
    }
    
    public static List<List<Integer>> findNumbersSumToTarget(int[] candidates, int start, int target){
        List<List<Integer>> combos = new ArrayList<List<Integer>>();
        for(int i=start; i<candidates.length; i++){

            if(candidates[i] > target){
                break;
            }else if(candidates[i] == target){
                List<Integer> list = new ArrayList<Integer>();
                list.add(candidates[i]);
                combos.add(list);
                break;
            }else if(target - candidates[i] >= candidates[i]){ //difference is larger than candidates[i] to avoid duplicates
                List<List<Integer>> subcombos=findNumbersSumToTarget(candidates,i+1,target-candidates[i]);
                if(subcombos.size() > 0){
                    for(List<Integer> subcombo:subcombos){
                        List<Integer> list = new ArrayList<Integer>();
                        
                        list.add(candidates[i]); //will generate {candidates[i], subcombos that add to target-candidates[i]}
                        list.addAll(subcombo);        
                        if(Collections.indexOfSubList(combos,list) == -1){
                        	combos.add(list);
                        }
                                   
                    }
                }
                
            }
            
            while(i+1 < candidates.length && candidates[i+1] == candidates[i]){ //skip all duplicates of current number since we've already accounted for all combos using the first instance of the current number
            	i++;
            }

        }
        return combos;
    }
    
    public static void main(String[] args){
    	int[] candidates={4,4,2,1,4,2,2,1,3};
    	int target=6;
    	List<List<Integer>> ans = combinationSum2(candidates,target);
    	for(List<Integer> list:ans){
    		System.out.print("[");
    		for(int i=0; i<list.size(); i++){
    			System.out.print(list.get(i));
    			if(i!=list.size()-1){
    				System.out.print(",");
    			}
    		}
    		System.out.print("],");
    	}
    }
    
}