package Graphs;
//for finding shortest path from source to destination given a graph

import java.util.*;

public class Dijkstra {
	
	public static int[] dijkstra(WeightedGraph G, int s){
		int[] dist=new int[G.size()]; //dist[i]=shortest distance between "s" and vertex i; we will update this later
		int[] predecessor=new int[G.size()]; //preceeding node in path
		boolean[] visited=new boolean[G.size()]; //all false initially
		
		for(int i=0; i<dist.length; i++){
			dist[i]=Integer.MAX_VALUE; //initialize distance to all nodes as infinite
		}
		dist[s]=0; //except source, which we initialize the distance to be 0.
		
		for(int i=0; i<dist.length; i++){ //iterate through all the vertices
			int next=minVertex(dist,visited); //find the next vertex to visit, which is the vertex with the shortest distance from our blob of already visited nodes
			visited[next]=true; //set this to true since we're visiting it (so we will absorb this into our blob and not visit it again)
			
			int[] neighbors = G.neighbors(next); //get next's neighbors
			for(int j=0; j<neighbors.length; j++){
				int v=neighbors[j];
				int d=dist[next]+G.getWeight(next,v); //getWeight(next,v) =length of edge between (next,v)
				
				if(d<dist[v]){ //if dist(s,next) + dist(next,v) < current distance to v
					dist[v]=d; //update "d" as shortest distance to v
					predecessor[v]=next; //set "next" to be the predecessor of "v"
				}
			}
			
		}
		return predecessor; //ignore predecessor[s]=0
		
	}
	
	//minVertex: returns the next unvisited vertex with closest distance to our already visited blob of vertices
	private static int minVertex(int[] dist, boolean[] visited){
		int min_distance = Integer.MAX_VALUE; //init min distance
		int min_index = -1; //init min index
		for(int i=0; i<dist.length; i++){
			if(!visited[i] && dist[i] < min_distance){
				min_distance = dist[i];
				min_index=i;
			}
		}
		return min_index;
	}
	
	public static void printPath(WeightedGraph G, int[] predecessor, int source, int destination){
		ArrayList path = new ArrayList();
		int x=destination;
		while(x!=source){ //backtrack the path to the source by finding predecessors
			path.add(0,G.getLabel(x));
			x=predecessor[x];
		}
		
		path.add(0, G.getLabel(source)); //lastly, add the source
		System.out.println(path);
	}
	
	/********** WeightedGraph implementation ***********/
	//for a weighted graph, the "weight" of an edge is the length of the edge
	public class WeightedGraph {

		private int[][] edges; // adjacency matrix
		private Object[] labels;

		public WeightedGraph(int n) {
			edges = new int[n][n];
			labels = new Object[n];
		}
		
		public int size(){ return labels.length; }
		
		public void setLabel(int vertex, Object label) { labels[vertex] = label;}

		public Object getLabel(int vertex) { return labels[vertex ]; }

		public void addEdge(int source, int target, int w) { edges[source][target] = w; }

		public void removeEdge(int source, int target) { edges[source][target] = 0; }

		public int getWeight(int source, int target) {
			return edges[source][target];
		}

		public int[] neighbors(int vertex) {
			int count = 0;
			for (int i = 0; i < edges[vertex].length; i++) {
				if (edges[vertex][i] > 0)
					count++;
			}
			final int[] answer = new int[count];
			count = 0;
			for (int i = 0; i < edges[vertex].length; i++) {
				if (edges[vertex][i] > 0)
					answer[count++] = i;
			}
			return answer;
		}
	}
}
