//1.3 URLify: Replace ' ' with %20

index = trueLength + spaceCount*2; //last index
str[trueLength]='\0'; //end of array
for (i=trueLength - 1; i>=0; i--){
	if (str[i]==' '){
		str[index-1]='0';
		str[index-2]='2';
		str[index-3]='%';
		index=index-3;
	}
	else{
		str[index-1]=str[i];
		index--;
	}
}

//1.5 OneEditAway

boolean oneEditAway(String first, String second){

	if (Math.abs(first.length() - second.length() > 1)
		return false;


	/* Get shorter (s) and longer (s) string */
	String shorter=first.length() < second.length()? first:second;
	String longer=first.length() < second.length()? second: first;

	int s,l=0;

	boolean foundDifference=false;
	while(s < shorter.length() && l < longer.length()){
		//Case 1: chars don't match
		if(shorter.charAt(i1) != longer.charAt(i2)){
			if(foundDifference){ //this is not the first diff
				return false;
			}else{
				foundDifference=true; //found first diff
			}

			if(shorter.length() == longer.length()){ //replace scenario
				s++;
				l++;
			}else{ //insertion/deletion scenario
				l++; //check next char in longer to see if it equals shorter
			}
		}
		//Case 2: chars match
		else{
			s++;
			l++;
		}
	}
	return true;
}

//1.6 String compression

//using concatenation

String compressedBad(String str){
	String compressedString="";
	int countConsecutive=0;
	for (int i=0; i<str.length(); i++){
		countConsecutive++;

		//check bound before char to prevent ArrayOutOfBoundsException
		if(i+1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
			compressedString += str.charAt(i) + countConsecutive;
			countConsecutive=0; //reset count for next char
		}
	}
	return compressedString;
}

String compressedGood(String str){
	String compressedString = new StringBuilder();
	int countConsecutive=0;
	for (int i=0; i<str.length(); i++){
		countConsecutive++;

		//bound check before char check
		if (i+1 >= str.length() || str.charAt(i) != str.charAt(i+1)){
			compressedString.append(str.charAt(i));
			compressedString.append(countConsecutive);
			countConsecutive=0;
		}
	}
	return compressedString;
}

//1.7 Rotate NxN matrix 90 degrees

rotate(int[][] matrix){

	int n=matrix.length;
	for (int layer=0; layer<n/2; layer++){ //walk through layers

		int first=layer;
		int last=n-1-layer;

		for(int i=layer; i<n-1-layer;i++){ //walk through cell in each layer

			int offset=i-layer;
			int top=matrix[layer][i]; //save top

			//right <- top
			matrix[i][last]=top;

			//bottom <- right
			matrix[last][last-offset]=matrix[i][last];

			//left <- bottom
			matrix[last-offset][first]=matrix[last][last-offset];

			//top <- left
			matrix[first][i]=matrix[last-offset][first];
			

		}
	}
}

//1.9 String rotation

String s1s1= s1+ s1; //waterbottlewaterbottle
return isSubstring(s1s1, s2); //erbottlewat is substring of waterbottlewaterbottle

/* Chapter 4 - graphs */

class Graph{
	Node[] nodes;
}

class Node{
	String name;
	Node[] adjacent;
}

//DFS
void search(Node node){
	if (node == null) return;
	visit(node);
	node.visited=true;
	for (Node a : node.adjacent){
		if(a.visited == false){
			search(a);
		}
	}
}

//BFS
void search(Node node){
	Queue queue=new Queue();
	node.marked=true; 
	queue.enqueue(node); //add to end of queue

	while(!queue.isEmpty()){
		Node n=queue.dequeue(); //remove front of queue
		visit(n);
		for (Node a in n.adjacent){
			if(!a.marked){
				a.marked=true;
				queue.enqueue(a);
			}
		}
	}
}/* Chapter 4 - Graphs */

class Graph{
	Node[] nodes;
}

class Node{
	String name;
	Node[] adjacent;
}

//DFS
void search(Node node){
	if (node == null) return;
	visit(node);
	node.visited=true;
	for (Node a : node.adjacent){
		if(a.visited == false){
			search(a);
		}
	}
}

//BFS
void search(Node node){
	Queue queue=new Queue();
	node.marked=true; 
	queue.enqueue(node); //add to end of queue

	while(!queue.isEmpty()){
		Node n=queue.dequeue(); //remove front of queue
		visit(n);
		for (Node a in n.adjacent){
			if(!a.marked){
				a.marked=true;
				queue.enqueue(a);
			}
		}
	}
}

