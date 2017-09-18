package Numbers;

import java.util.*;

public class MergeIntervals {
	
	public class Interval {
	 int start;
	 int end;
	 Interval() { start = 0; end = 0; }
	 Interval(int s, int e) { start = s; end = e; }
	}
	
	public static List<Interval> mergeIntervals(List<Interval> intervals){
		
		//1. sort intervals by start time. if start times equal, sort by end time.
		Collections.sort(intervals,new Comparator<Interval>(){
			public int compare(Interval i1, Interval i2){
				if(i1.start > i2.start){
					return 1; //positive, return i2 as smaller
				}
				if(i1.start < i2.start){
					return -1; //negative, return i1 as smaller
				}
				if(i1.start == i2.start){
					if(i1.end > i2.end){
						return 1; //positive, return i2 as smaller
					}
					if(i1.end < i2.end){
						return -1; //negative, return i1 as smaller
					}
					if (i1.end == i2.end){
						return 0; //i1 completely equals i2
					}
				}
				
				return 0; 
				
			}
		});		
		
		//2. merge each pair continuously
		for(int i=0; i<intervals.size()-1; i++){
			Interval cur = intervals.get(i);
			Interval next = intervals.get(i+1);
			
			if(cur.end >= next.start ){ //overlap between current and next
				next.start=cur.start; //next = merge(current,next)
				next.end=Math.max(cur.end,next.end);
				
			}
		}
		
		return intervals;
	
	}

}
