package Numbers;

import java.util.*;

class PairsWithGivenDifference {

  static int[][] findPairsWithGivenDifference(int[] arr, int k) {
    // your code goes here
    ArrayList<int[]> answer = new ArrayList<int[]>();
    
    Arrays.sort(arr);
    int smaller=0;
    int bigger=1;
    while(smaller < arr.length-1 && bigger < arr.length){
      if(arr[smaller]+k == arr[bigger]){
        answer.add(new int[]{arr[bigger],arr[smaller]});
        smaller++;
        bigger++;
      }
      else if(arr[smaller]+k<arr[bigger]){
        smaller++;
      }
      else{
        bigger++;
      }
    }
    
    int[][] result=new int[answer.size()][2];
    for(int i=0; i<answer.size(); i++){
        result[i]=answer.get(i);
        System.out.println("i:"+i+","+result[i][0]+" "+result[i][1]);
    }
    
    return result;
    
  }

  public static void main(String[] args) {
    int[] arr={0,-1,-2,2,1};
    findPairsWithGivenDifference(arr,1);
  }

}