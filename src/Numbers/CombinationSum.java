package Numbers;

import java.util.*;

//input: candidate = [2,3] (no duplicates), target=7
//output: [2,2,3],[7]

//you may use the same number as many times as needed to achieve the target

class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSumHelper(candidates,0,target);
    }
      
    public List<List<Integer>> combinationSumHelper(int[] candidates, int start, int target){
        List<List<Integer>> combos = new ArrayList<List<Integer>>();
        for(int i=start; i<candidates.length; i++){
            if(candidates[i] > target){
                break;
            }else if(candidates[i] == target){
                ArrayList<Integer> single_number = new ArrayList<Integer>();
                single_number.add(candidates[i]);
                combos.add(single_number);
            }else if(target-candidates[i] >= candidates[i]){

                //Scenario 1: Candidate[i] repeated n times exactly sums to target
                if(target % candidates[i] == 0){
                    ArrayList<Integer> repeated_candidate = new ArrayList<Integer>();
                    for(int count=0; count<target/candidates[i]; count++){
                        repeated_candidate.add(candidates[i]);
                    }
                    combos.add(repeated_candidate);
                }
                
                //Scenario 2: 0 to n repetitions of candidate[i] in the subcombos
                int sum=0;
                List<Integer> repeated_candidate = new ArrayList<Integer>();

                while(i+1 < candidates.length && target-sum > candidates[i+1]){
                    sum+=candidates[i];
                    repeated_candidate.add(candidates[i]);
                    
                    List<List<Integer>> subcombos_rep=combinationSumHelper(candidates,i+1,target-sum); 
                    
                    for(List<Integer> subcombo: subcombos_rep){
                        ArrayList<Integer> list = new ArrayList<Integer>(); //generate merged list: {candidates[i], subcombo(i)}
                        list.addAll(repeated_candidate); //append candidates[i] to each subcombo
                        list.addAll(subcombo); //since subcombo is a list
                        combos.add(list);
                    }
                }

            }
        }
        return combos;
    }
}
