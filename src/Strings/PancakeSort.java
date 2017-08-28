package Strings;

public class PancakeSort {
	  
	  public static void flip(int[] arr, int k){
		  int x=0;
		  int y=k-1;
		  while(y>x){
			 int temp=arr[x];
			 arr[x]=arr[y];
			 arr[y]=temp;
			 x++;
			 y--;
		 }
	  }

	  //for each iteration: find max, place max at last index. then find second biggest and place it at second last, then third biggest at third last...
	  static int[] pancakeSort(int[] arr) {
	    int unsorted=arr.length;
	    while(unsorted > 0)
	    {
	      int max=arr[0];
	      int max_index=0;
	      for(int i=0; i<unsorted; i++){
	        if(arr[i] > max){
	          max=arr[i];
	          max_index=i;
	        }
	      }
	      flip(arr,max_index+1);
	      flip(arr,unsorted);
	      unsorted--;
	    }

	    return arr;
	  }
	  
	  public static void printArray(int[] arr){
		  for(int c:arr){
			  System.out.print(c+" ");
		  }
		  System.out.println();
	  }

	  public static void main(String[] args) {
		  int[] arr={3,2,4,1,5};
		  pancakeSort(arr);
	  }
}
