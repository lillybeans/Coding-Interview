package Pramp;

import java.util.PriorityQueue;

public class KMessedArraySort {

	  static int[] sortKMessedArray(int[] arr, int k) {
	    // your code goes here
	    PriorityQueue<Integer> pq=new PriorityQueue<Integer>(); //min heap
	    
	    //Step 1. build first minheap of size k+1
	    
	    //why you won't need 2k+1 as heap size: 
	    // you only need to ensure your upper bound covers up to furthest element, lower bound automatically ensured since we continuously remove from heap.
	    // suppose you are trying to figure out what to store in res[2] in step 2, 
	    // your heap would have already contained elements up to arr[4] at that point,
	    // with the 2 smallest elements already removed for res[0] and res[1]. 
	    // naturally the next element you remove from heap is smallest so far, with range checked up to arr[4].
	    
	    for(int i=0; i<k+1; i++){ //if k=2, a[0]'s furthest position of a[0] in sorted array is a[2], which you need to check 3 elements: a[0],a[1],a[2]
	      pq.offer(arr[i]);
	    }
	    
	    //Step 2. continuously: remove min from minheap and store in res, add element from arr to minheap
	    int[] res = new int[arr.length];
	     for(int i=k+1; i<arr.length; i++){ //if k=2, starting from from a[3] we continuously remove minimum from pq,then add arr[i]
	      res[i-(k+1)]=pq.poll(); //when i=k+1, res index=0. when i=arr.length, res index=arr.length-(k+1), which is where we should start in next loop
	       pq.offer(arr[i]);
	    }
	    
	    //Step 3. after prev loop, you only filled up to but not including res[arr.length-(k+1)]. You need to fill up all the way to res[arr.length]
	    for(int i=arr.length-(k+1); i<arr.length; i++){
	      res[i]=pq.poll();
	    }
	    
	    return res;
	  }

	  public static void main(String[] args) {
		  int[] arr={1, 4, 5, 2, 3, 7, 8, 6, 10, 9};
		  int k=2;
		  int[] ans=sortKMessedArray(arr,k);
		  for(int i:ans){
			  System.out.print(i+",");
		  }
	  }

}
