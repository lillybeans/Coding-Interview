package SystemDesign;

import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Node;

public class Cache {
	
	class Node{
		String query;
		String[] results;
		
		Node(String query, String[] results){}
	}
	
	public static int MAX_SIZE=10;
	public Node head, tail;
	public HashMap<String,Node> map;
	public int size=0;
	
	public Cache(){
		map = new HashMap<String, Node>();
	}
	
	public void moveToFront(Node node){}
	public void moveToFront(String query){}
	
	public void removeFromLinkedList(Node node){}
	
	public String[] getResults(String query){ //get results from cache and update Linked-list
		if(!map.containsKey(query)) return null;
		
		//if node exists in the linked list
		Node node = map.get(query);
		moveToFront(node); //update feshness
		return node.results;
	}
	
	public void insertResults(String query, String[] results){ //insert results into linked list and hash
		if(map.containsKey(query)){ //update
			Node node = map.get(query);
			node.results = results;
			moveToFront(node);
			return;
		}
		
		//if map does not contain key, create new node for results and store
		Node node = new Node(query, results);
		moveToFront(node);
		map.put(query,node);
		
		if(size > MAX_SIZE){
			map.remove(tail.query);
			removeFromLinkedList(tail);
		}
	}
}
