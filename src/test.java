import java.util.*;


public class test {
	
	//for reverse, go up to i<arr.length/2 is good
	//[0,1,2,3,4,5], length=6, length/2=3, so 0,1,2
	//[0,1,2,3,4], length=5, length/2=2, so 0,1. Don't need to reverse 2 since it's in the middle

	public static void main(String[] args){
		//char a='a';
		//System.out.println((int)a);
		List<List<Integer>> list1=new ArrayList<List<Integer>>();
		List<List<Integer>> list2=new ArrayList<List<Integer>>();
		
		ArrayList<Integer> sample=new ArrayList<Integer>();
		ArrayList<Integer> sample2=new ArrayList<Integer>();
		
		sample.add(4);
		sample.add(5);
		list1.add(sample);
		
		sample2.add(4);
		sample2.add(5);
		list2.add(sample2);
		
		System.out.println(Collections.indexOfSubList(list1, list2));
	}
}
