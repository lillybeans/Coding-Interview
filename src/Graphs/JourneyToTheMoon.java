package Graphs;

import java.io.*;
import java.util.*;

public class JourneyToTheMoon {

	public static void main(String[] args){
		
		Scanner scanner=new Scanner(System.in);
		String line=scanner.nextLine();
		String[] NP = line.split(" ");
		int N=Integer.parseInt(NP[0]);
		int P=Integer.parseInt(NP[1]);
		
		//List of List: {astronaut 1:compatriots, atronaut2: compatriots, astronaut 3:compatriots...}
		List<ArrayList<Integer>> astronauts=new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<N; i++){ //for n astronauts
			astronauts.add(new ArrayList<Integer>()); //initialize list of compatriots for each astronaut
		}
		
		for(int i=0; i<P; i++){ //for each pair
			String pair=scanner.nextLine();
			String[] n1n2=pair.split(" ");
			int n1=Integer.parseInt(n1n2[0]);
			int n2=Integer.parseInt(n1n2[1]);

			astronauts.get(n1).add(n2);
			astronauts.get(n2).add(n1);
		}
		
		boolean[] isVisited = new boolean[N];
		ArrayList<Integer> countrySizes=new ArrayList<Integer>(); //size of each country
		Queue<Integer> queue = new ArrayDeque<Integer>(); //this is how you make a queue
		
		for(int i=0; i<N; i++){
			
			int countrySize=0;
			
			if(!isVisited[i]){
				isVisited[i]=true;
				countrySize++;
				queue.addAll(astronauts.get(i)); //flattens ArrayList<Integer> and adds all elements instead of adding a list
			}
			
			while(!queue.isEmpty()){
				int compatriot=queue.poll();
				if (!isVisited[compatriot]){
					isVisited[compatriot]=true;
					countrySize++;
					queue.addAll(astronauts.get(compatriot)); //add compatriot's compatriots
				}
			}
			
			if(countrySize > 0){
				countrySizes.add(countrySize);
			}
		}
		
		long pairs=0;
		long partners=N;
		for(int countrySize:countrySizes){
			pairs+=countrySize * (partners - countrySize); //possible pairs with other country's partners
			partners -= countrySize; //do not wanna double count current country's astronauts as partners with other countries in the future
		}
		
		System.out.println(pairs);
	}
	
}
