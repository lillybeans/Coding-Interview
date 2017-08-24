import java.util.HashSet;

/* Chapter 1 - Strings and Arrays */

//1.1 IsUnique: Does string have all unique characters?

//Method 1: check if each char in the alphabet has been hit O(N)
boolean isUniqueChars(String str){
  boolean[] char_set=new boolean[128]; //max range
  
  for(int i=0; i<str.length(); i++){
    int val=str.charAt(i);
    if (char_set[val]){
      return false;
    }
    char_set[val]=true;
  }
  return true;
}


//Method 2: 32-bit checker (int has 32 bits)
boolean isUniqueChars(String str){
  int checker=0; //0000 0000 0000 0000, 32>26, range covers all of a-z
  for (int i=0; i<str.length(); i++){
    int bitInChecker=str.charAt(i)-'a'; //bitInChecker=# of bits to shift
    if((checker&(1<<val)) > 0){ //if bitInChecker has been set
      return false;
    }
    checker |= (1<<bitInChecker); //1<<bitInChecker = 1 followed by bitInChecker 0's
    //ex. bitInChecker=5, checker=0000 0000 0000 0000 | 0000 0000 0010 0000
  }
  return true;
}

//1.2 isPermutation: check if two strings are permutations of each other

//Method 1: sort the two strings
String sort(String s){
	char[] content=s.toCharArray();
	java.util.Arrays.sort(content);
	return new String(content);
}

boolean permutation(String s, String t){
	return sort(s).equals(sort(t));
}

//Method 2: identical char counts
boolean isPermutation(String s, String t){
  if (s.length()!=t.length() {
    return false;
  }
  
  int[] letters=new int[128];
  
  char[] s_array=s.toCharArray();
  for(char c:s_array){
    letters[c]++;
  }
  
  for(int i==0; i<t.length(); i++){
    int c=(int) t.charAt(i);
    letters[c]--;
    if(letters[c]<0){
      return false;
    }
  }
  return true;
}

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

//1.4 Palindrome Permutation: is string a permutation of a palindrome?

//palindrome if at most 1 char has odd counts
boolean isPermutationOfPalindrome(String s){
int countOdd=0; //counts total number of chars with odd occurrences
int[] alphabet=new int[26];
for(char c:s.toCharArray(){
  int x=(int)c;
  alphabet[x]++;
  if (alphabet[x]%2 == 1){ //update our odd counts as we go along
    countOdd++;
  }
  else{
    countOdd--;
  }
}
return countOdd <=1;
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

		for(int i=layer; i<n-1-layer;i++){ //walk through cell in each layer but NOT last cell in segment

			int offset=i-layer;
			int top=matrix[layer][i]; //save top
			
			//break down each layer into 4 segments:
			
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

//1.8 Zero Matrix: if an element in MxN matrix is 0, set its entire row and column to 0

void setZeros(int[][] matrix){
	boolean[] row=new boolean[matrix.length];
	boolean[] column=new boolean[matrix[0].length];
	
	//store row indices and column indices with value 0
	for (int i=0; i<matrix.length; i++){
	  for (int j=0; j<matrix[0].length; j++){
	    if (matrix[i][j]==0){
	      row[i]=true;
	      column[j]=true;
	    }
	  }
	}
	
	//nullify rows
	for (int i=0; i<row.length; i++){
	  if(row[i]) nullifyRow(matrix,i);
	}
	
	//nullify columns
	for (int j=0; j<column.length;j++){
	  if(column[j]) nullifyColumn(matrix,j);
	}
}

void nullifyRow(int[][] matrix, row){
  for(int j=0; i<matrix[0].length;j++){ //go thru all columns in the row provided
    matrix[row][j]=0;
  }
}

void nullifyColumn(int[][] matrix, column){
  for(int i=0; i<matrix.length; i++){
    matrix[i][col]=0;
  }
}

//1.9 String rotation

String s1s1= s1+ s1; //waterbottlewaterbottle
return isSubstring(s1s1, s2); //erbottlewat is substring of waterbottlewaterbottle


/* Chapter 2 - Linked Lists */

class Node{
	Node next=null;
	int data;
	
	public Node(int d){
		data=d;
	}
	
	void appendToTail(int d){
		Node end=new Node(d);
		Node n=this;
		while (n.next!=null){
			n=n.next;
		}
		n.next=end;
	}
}

//2.1 Remove Duplicates: remove duplicates from unsorted linked list

void deleteDups(LinkedListNode n){
	HashSet<Integer> set = new HashSet<Integer>();
	LinkedListNode previous=null;
	
	while(n!=null){
		if(set.contains(n.data)){
			previous.next=n.next; //skip n
		}
		else{
			set.add(n.data); //mark n
			previous=n;
		}
		n=n.next;
	}
}
		}
	}
}

Node deleteNode(Node head, int d){
	
	Node n=head;
	
	if(n.data == d){
		return head.next;  //moved head
	}
	
	while(n.next!=null){
		if (n.next.data==0){
			n.next=n.next.next;
			return head; //head didn't change
		}
		n=n.next;
	}
}

/* Chapter 4 - Graphs */

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

