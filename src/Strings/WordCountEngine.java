package Strings;

import java.io.*;
import java.util.*;

class WordCountEngine {

  static String[][] wordCountEngine(String document) {
    Map<String,Integer> map=new HashMap<String,Integer>();
    for (String s : document.split(" ")) {
      String word = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); //^=not, X* is 0+ times, X+ is 1+ times
      if(!map.containsKey(word)){
        map.put(word,1);
      }
      else{
        map.put(word, map.get(word) + 1);
      }
    }
    
    //-1: o1 < o2
    //1: o1 > o2
    //0: o1 = o2
    Queue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<Map.Entry<String,Integer>>(new Comparator<Map.Entry<String, Integer>>() {
      public int compare (Map.Entry<String, Integer> entry1, Map.Entry<String, Integer>entry2) {
        return entry2.getValue() - entry1.getValue(); //entry2 before entry1 for maxheap implementation
        //Reasoning: compare(a,b). If answer negative, put a at front of the queue. If answer positive, put b at front of the queue.
        //return "a-b" will always ensure the smaller element is at the front of the queue.
        //return "b-a" reverses this situation. if b-a is positive, compare(a,b) will return b.
      } 
    });
    for(Map.Entry<String, Integer> entry : map.entrySet()) { 
    	maxHeap.offer(entry);
    }
    
    String[][] answer = new String[maxHeap.size()][2];
    int i=0;
    while(!maxHeap.isEmpty()){
      Map.Entry<String, Integer> wordCount = maxHeap.poll();
      answer[i][0]=wordCount.getKey() + "";
      answer[i][1]=wordCount.getValue() + "";
      i++;
    }
    return answer;
  }

  public static void main(String[] args) {
	  String s="Practice makes perfect so you should practice more and you'll get better!";
	  String[][] wordCounts=wordCountEngine(s);
	  for (String[] wordCount : wordCounts ){
		  System.out.println(wordCount[0]+" "+wordCount[1]);
		  System.out.println();
	  }
  }

}