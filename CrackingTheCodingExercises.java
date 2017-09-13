import java.util.HashSet;

/*-------------------------------------------------------------*/
/* Chapter 1 - Strings and Arrays				  			   */
/*-------------------------------------------------------------*/

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


//Method 2: 32-bit checker (int has 32 bits), O(N)
boolean isUniqueChars(String str){
  int checker=0; //0000 0000 0000 0000, 32>26, range covers all of a-z
  for (int i=0; i<str.length(); i++){
    int bitInChecker=str.charAt(i)-'a'; //bitInChecker=# of bits to shift "1" left
    if((checker&(1<<val)) > 0){ //if bitInChecker has been set
      return false;
    }
    checker |= (1<<bitInChecker); //if not set, union checker with the '1' bit; 1<<bitInChecker means 1 followed by bitInChecker 0's
    //ex. sample union: bitInChecker=5, checker=0000 0000 0000 0000 | 0000 0000 0010 0000
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


	/* Get shorter and longer string */
	String shorter=first.length() < second.length()? first:second;
	String longer=first.length() < second.length()? second: first;

	int s,l=0;

	boolean foundDifference=false;
	while(s < shorter.length() && l < longer.length()){
		//Case 1: chars don't match
		if(shorter.charAt(s) != longer.charAt(l)){
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
//Input: aaabcccccaaa, Output:a2b1c5a3

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


/*-------------------------------------------------------------*/
/* Chapter 2 - Linked List									   */
/*-------------------------------------------------------------*/

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

Node deleteNode(Node head, int d){ //delete node with matching data
	
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

//2.1 Remove Duplicates: remove duplicates from unsorted linked list

void deleteDups(LinkedListNode n){
	HashSet<Integer> set = new HashSet<Integer>(); //keep track of unique integers
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

//2.2 Return Kth to last: find kth to last element of a linked list of unknown length. (i.e. 2nd to last = 2nd element from last)

LinkedListNode nthToLast(LinkedListNode head, int k){
	LinkedListNode p1=head; //p1, p2 will be k nodes apart
	LinkedListNode p2=head;
	
	//Move p1 k nodes into the list
	for(int i=0; i<k;i++){
		if (p1==null) return null;
		p1=p1.next;
	}
	
	while(p1!=null){ //when p1 hits end of list, p2 will be kth to last
		p1=p1.next;
		p2=p2.next;
	}
	
	return p2;
}

//2.3 Delete Middle Node of a linked list, with only access to that node

//strategy: copy the data and link of n.next into n
boolean deleteNode(LinkedListNode n){
	if(n==null || n.next==null){
		return false; //fail
	}
	LinkedListNode next=n.next;
	n.data=next.data;
	n.next=next.next;
	return true;
}

//2.4 Partition around a value x, so that all nodes < x are before all nodes > x. x can appear anywhere in the  ">x" partition

//I: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition=5]
//O: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8

LinkedListNode partition(LinkedListNode node, int x){
	
	LinkedListNode head=node; //elements < x added before head
	LinkedListNode tail=node; //elements > x added after tail
	
	while(node!=null){
		LinkedListNode next=node.next; //save next
		if(node.data < x){
			node.next=head;
			head=node; //node becomes new head since now it's in front of head
		}
		else{
			tail.next=node;
			tail=node; //node becomes new tail since now it's after tail
		}
		node=next; //move to next node
	}
	tail.next=null;
	
	return head; //return changed head
}

//2.5 Sum Lists: two numbers represented by a linked list, digits stored in reverse order

//Input: (7->1->6) + (5->9->2), so 617+295
//Output: (2->1->9), so 912

//Part 1: Digits in Reverse Order
LinkedListNode addList(LinkedListNode l1, LinkedListNode l2, int carry){
	if(l1 == null && l2==null && carry=0){
		return null;
	}
	
	LinkedListNode result=new LinkedListNode(); //linked list for storing our results
	int digit_sum=carry; //sum of current digits: start with previous digit's carry
	
	if(l1 != null){
		digit_sum += l1.data;
	}
	
	if(l2 != null){
		digit_sum += l2.data;
	}
	
	//digit sum can be 2 digits if number > 10, so we gotta get second digit
	
	int sum_ones_digit=digit_sum%10;
	
	if(l1!=null || l2!=null){ //at least still one digit left for the next digit
		LinkedListNode more=addLists(l1==null? null:l1.next, l2==null? null:l2.next, digit_sum >=10? 1:0); //carry=1 if digit_sum>10
		result.setNext(more); //append more to our result
	}
	
	return result;
}

//Part 2: Digits in forward order

//Input: (6->1->7)+(2->9->5)
//Output: (9->1->2)

//if l1.length != l2.length, we need to pad shorter list with zeroes
//challenge: current sum must add all future carries to the right of current digit

class PartialSum{ //reflects the result of adding all future digits
	public LinkedListNode sum=null;
	public int carry=0; //this carry is for the digit to my LEFT (i.e. for the higher digit which is my previous node)
}

LinkedListNode addLists(LinkedListNode l1, LinekdListNode l2){
	int len1=length(l1);
	int len2=length(l2);
	
	if(len1<len2){ //pad shorter list with zeros
		l1=padList(l1,len2-len1);
	}else{
		l2=padList(l2,len1-len2);
	}
	
	PartialSum sum=addListsHelper(l1,l2);
	
	if(sum.carry==0){ //no carry for the highest digit, we are done
		return sum.sum; //return result
	}else{ //there was carry
		LinkedListNode result=insertBefore(sum.sum, sum.carry); //insert carry to the left of our linked list
		return result;
	}
}

PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2){
	if(l1 == null && l2==null){
		PartialSum sum=new PartialSum(); //cannot return null, can only return empty PartialSum object
		return sum;
	}
	
	PartialSum next_digit_sum=addListHelper(l1.next,l2.next); //recursive part that computes sum of all the digits to the right of current
	
	int current_digit_sum = l1.data + l2.data + next_digit_sum.carry;
	
	LinkedListNode full_result = insertBefore(next_digit_sum.sum, current_digit_sum%10); //insert sum of current digit before sum of next digit
	
	PartialSum current_sum = new PartialSum();
	current_sum.sum = full_result; 
	current_sum.carry = current_digit_sum%10;
	
	return current_sum;
}

//pad shorter lists with zeroes
LinkedListNode padList(LinkedListNode l, int padding){
	LinkedListNode head=l;
	for(int i=0; i<padding; i++){
		head=insertBefore(head,0);
	}
	return head;
}

LinkedListNode insertBefore(LinkedListNode list, int data){
	LinkedListNode node=new LinkedListNode(data); //initialize new node with data
	if(list!=null){
		node.next=list; //then insert this newly created node in front of given list
	}
	return node;
}

//2.6 Palindrome: check if a linked list is a palindrome

//Method 1: Reverse and compare
boolean isPalindrome(LinkedListNode head){
	LinkedListNode reversed=reverseAndClone(head);
	return isEqual(head, reversed);
}

LinkedListNode reverseAndClone(LinkedListNode node){
	LinkedListNode head=null;
	while(node!=null){
		LinkedListNode cur = new LinkedListNode(node.data);
		cur.next=head; //insert current before head, where head is our previous node
		head=cur; 
		cur=cur.next; //in next iteration: cur becomes head, and cur.next will be inserted before head (cur)
	}
	return head; //new head, which should be last element in original list
}

boolean isEqual(LinkedListNode one, LinkedListNode two){
	while(one!=null && two!=null){
		if(one.data!=two.data){
			return false;
		}
		one=one.next;
		two=two.next;
	}
	return one==null && two==null; //both have to terminate at the same time, or else not equal!
}

//Method 2: Using a stack to reverse front half, fast runner and slow runner

boolean isPalindrome(LinkedListNode head){
	LinkedListNode fast=head; //moves at 2x slow's speed, so when fast reaches the end, slow will hit middle element
	LinkedListNode slow=head;
	
	Stack<Integer> stack=new Stack<Integer>();
	
	while(fast!=null && fast.next!=null){
		stack.push(slow.data);
		slow=slow.next;
		fast=fast.next.next;
	}
	
	if(fast!=null){ //list has odd number of elements i.e.(1,2,3,4,5), slow=2, fast=5 after i=1
		slow=slow.next; //skip middle element
	}
	
	while(slow!=null){ //palindromic checking starts
		int top=stack.pop().intValue(); //pop the reversed front half of linked list
		if(top != slow.data){
			return false;
		}
		slow=slow.next;
	}
	return true;
}

//2.7 Intersection: determine if two linked lists intersect. If they do, return intersecting node. 
// Must be same node by reference, not just same value.

//I: 3->1->5->9->7->2->1 (intersect at 7)
//         4->6->7->2->1

//Strategy: everything onward from intersection point to tail should be identical for the two lists.
LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2){
	Result result1=getTailAndSize(list1);
	Result result2=getTailAndSize(list2);
	
	if(result.tail!=result2.tail){ //tail not the same means two lists did not intersect earlier
		return null;
	}
	
	//set pointers to start of each linked list
	LinkedListNode shorter=result1.size < result2.size? list1:list2;
	LinkedListNode longer=result1.size < result2.size? list2:list1;
	
	//advanced longer list by difference in lengths so same starting point for both
	longer=getKthNode(longer, math.abs(result1.size-result2.size));
	
	//move both pointers until they coincide. 
	while(shorter!=longer){
		shorter=shorter.next;
		longer=longer.next;
	}
	
	return longer; //they must have coincided because earlier we compared their tails, which equalled
}

class Result{ //stores tail reference and size for each linkedlist
	public LinkedListNode tail;
	public int size;
	public Result(LinkedListNode tail, int size){ //call this constructor once you have tail and size figured out
		this.tail=tail;
		this.size=size;
	}
	
	Result getTailAndSize(LinkedListNode list){
		if(list==null) return null;
		
		int size=1;
		LinkedListNode current=list;
		while(current.next!=null){
			size++;
			current=current.next;
		}
		
		return new Result(current,size); //current would have become tail by this point
	}
	
	LinkedListNode getKthNode(LinkedListNode head, int k){
		while(k>0 && current!=null){ //while we haven't exhausted the list
			current=current.next;
			k--;
		}
		return current;
	}
}

/*-------------------------------------------------------------*/
/* Chapter 3 - Stacks and Queues				  			   */
/*-------------------------------------------------------------*/

//Stack: pop(), push(item), peek(), isEmpty()
//Queue: add(item), remove(), peek(), isEmpty()

//3.1 Three stacks: use single array to implement 3 stacks
//Stack 1: (0,n/3), Stack 2:(n/3,2n/3), Stack 3:(2n/3,n)

class FixedMultiStack{
	private int numberOfStacks=3;
	private int stackCapacity;
	private int[] values; //this array holds values for all 3 stacks
	private int[] sizes; //sizes.length=3, keeps track of size of each of the 3 stacks
	
	public FixedMultiStack(int capacity){
		stackCapacity=capacity;
		values=new int[stackSize * numberOfStacks];
		sizes=new int[numberOfStacks];
	}
	
	//Push value into given stack (dictated by StackNum)
	public void push(int StackNum, int value) throws FullStackException{
		if(isFull(stackNum)) throw new FullStackException;
		
		sizes[stackNum]++;
		values[indexOfTop(stackNum)]=value; //add to top of stackNum
	}
	
	public pop(int stackNum){
		if(isEmpty(stackNum)) throw new EmptyStackException();
		
		int topIndex=indexOfTop(stackNum); //must save this index so we can clear this item later
		int value=values[indexOfTop(stackNum)];
		values[topIndex]=0; //clear
		sizes[stackNum]--;
		return value;
	}
	
	public int peek(int stackNum){
		if(isEmpty(stackNum)) throw new EmptyStackException();
		
		return values[indexOfTop(stackNum)];
	}
	
	public boolean isEmpty(int StackNum){
		return sizes[stackNum]==0;
	}
	
	public boolean isFull(int stackNum){
		return sizes[stackNum]==stackCapcity;
	}
	
	private int indexOfTop(int stackNum){
		int offset=stackNum*stackCapacity; //starting point of our stack in the array
		int size=sizes[stackNum]; //how many elements we actually have in stackNum
		return offset+size-1;
	}
}

//3.2 Stack Min: in addition to push and pop, can also return minimum element. Push, pop, min all O(1)

public class StackWithMin extends Stack<NodeWithMin>{ //use special node that keeps track of min 
	
	class NodeWithMin{ //special node that stores the local min (i.e. minimum of all elements beneath node)
		public int value;
		public int min;
		public NodeWithMin(int v, int min){
			value=v;
			this.min=min;
		}
	}
	
	public void push(int value){
		int newMin=Math.min(value,min()); //check if incoming element's value is less than the min, if yes, update newMin
		super.push(new NodeWithMin(value,newMin)); //must push onto parent constructor that the class extends from
	}
	
	public int min(){
		if(this.isEmpty()){
			return Integer.MAX_VALUE; //if empty, any value will be less than Integer.MAX_VALUE so that value will become the newMin when pushed to stack
		}
		else{
			return peek().min;
		}
	}
}

//3.3 Stack of plates: SetofStacks is composed of several stacks, should create a new stack once previous one exceeds capacity

class SetOfStacks{
	
	ArrayList<Stack> stacks=new ArraytList<Stack>();
	
	public void push(int v){
		Stack last=getLastStack();
		if(last!=null && !last.isFull()){
			last.push(v);
		}else{
			Stack stack=new Stack(capacity);
			stack.push(v);
			stacks.add(stack);
		}
	}
	
	int pop(){
		Stack last=getLastStack();
		if(last == null) throw new EmptyStackException();
		int v=last.pop();
		if(last.size==0) stacks.remove(stacks.size()-1); //remove the last stack if its empty after pop, which is at stacks.size()-1
		return v;
	}
	
	//pop at a specific substack, will need rollover system (i.e. if we pop top of stack 1, bottom of stack 2 should roll over and pushed to stack 1)
	public int popAt(int index){
		return leftShift(index, true); //true is for "removeTop", later we will need to removeBottom for all the stacks we need to rollover.
	}
	
	public int leftShift(int index, boolean removeTop){
		Stack stack=stacks.get(index); //get the specific stack
		int removed_item;
		if (removeTop){
			removed_item=stack.pop();
		}
		else{
			removed_item = stack.removeBottom(); //for the stacks above: need to remove bottom to roll over
		}
		if (stack.isEmpty()){
			stacks.remove(index);
		}
		else if(stacks.size() > index + 1){ //there are stacks above the one we popped: need to rollover those stacks, starting with the one immediately above
			int v=leftShift(index+1, false);
			stack.push(v); //ex. popped stack 1, then we need to push bottom of stack 2 onto top of stack 1
		}
		return removed_item;
	}
}

//3.4 Queue via stacks: implement a MyQueue class which implements a queue using two stacks

//Queue: remove should remove oldest element
//stackNewest: newest item on top
//stackOldest: oldest item on top
//stackNewest and stackOldest will have same elements but reverse order. 
//use two stacks to transfer from one to the other

public class MyQueue<T>{
	Stack<T> stackNewest, stackOldest; //stackNewest: has newest value on top
	
	public myQueue(){
		stackNewest=new Stack<T>();
		stackOldest=new Stack<T>();
	}
	
	public void add (T value){
		stackNewest.push(value);
	}
	
	private void shiftStacks(){ //transfer items from stackNewest to stackOldest. 
		if(stackOldest.isEmpty()){
			while(!stackNewest.isEmpty()){
				stackOldest.push(stackNewest.pop());
			}
		}
	}
	
	public int size(){
		return stackNewest.size() + stackOldest.size();
	}
	
	public T peek(){ //peek oldest element
		shiftStacks();
		return stackOldest.peek(); //peek oldest item
	}
	
	public T remove(){
		shiftStacks();
		return stackOldest.pop(); //pop oldest item
	}
}

//3.5 Sort Stack: sort stack so that smallest items are on top. You can use additional temporary stack, but not another data structure like aray.
//O(N^2) time, O(N) space

//strategy: have two stacks, one unsorted, one sorted. 
//Sorted has biggest element on top, so when you transfer it into unsorted one in the end, you will get smallest on top

void sort(Stack<Integer> unsorted){
	Stack<Integer> sorted=new Stack<Integer>();
	while(!unsorted.isEmpty()){
		int temp=unsorted.pop();
		while(!sorted.isEmpty() && sorted.peek() > temp){
			unsorted.push(sorted.pop()); //transfer elements bigger than temp in sorted list to the unsorted list
		}
		sorted.push(temp);
	}
	
	//all sorted in "Sorted", now transfer elements in "Sorted" back to unsorted so we will get smallest element on top
	while(!sorted.isEmpty()){
		unsorted.push(sorted.pop());
	}
}

//3.6 Dogs and Cats animal shelter: people must adopt the oldest cats and dogs (based on arrival time) in the shelter.

abstract class Animal{ //abstract since dog and cats have similar methods
	private int order;
	protected String name;
	public Animal(String n){name = n;}
	public void setOrder(int ord){order = ord;}
	public int getOrder(){return order;}
	
	public boolean isOlderThan(Animal a){ //we will be able to compare dog and cat since they are both animal
		return this.order < a.getOrder();
	}
}

public class Dog extends Animal{
	public Dog(String n){super(n);}
}

public class Cat extends Animal{
	public Cat(String n){super(n);}
}

class AnimalQueue{
	LinkedList<Dog> dogs=new LinkedList<Dog>();
	LinkedList<Cat> cats=new LinkedList<Cat>();
	private int order=0;
	
	public void enqueue(Animal a){
		a.setOrder(order);
		order++;
		
		if (a instanceof Dog) dogs.addLast((Dog) a);
		else if(a instanceof Cat) cats.addLast(Cat) a);
	}
	
	public Animal dequeueAny(){ //dequeue either dog or cat
		if(dogs.size()==0){
			return dequeueCats();
		}else if(cats.size()==0){
			return dequeueDogs();
		}
		
		//neither dogs or cats are empty
		Dog dog=dogs.peek();
		Cat cat=cats.peek();
		
		if(dog.isOlderThan(cat)){
			return dequeueDogs();
		}else{
			return dequeueCats();
		}
	}
		
	public Dog dequeueDogs(){
		return dogs.poll();
	}
	
	public Cat dequeueCats(){
		return cats.poll();
	}
}
	
/*-------------------------------------------------------------*/
/* Chapter 4 - Graphs							  			   */
/*-------------------------------------------------------------*/

//Binary Search Tree is more stringent than Binary Tree. in BST: each left node < current node, each right node > current node

//In order traversal: LNR
//Pre order traversal: NLR
//Post order traversal: LRN

void inOrderTraversal(TreeNode node){
	if(node!=null){
		inOrderTraversal(node.left);
		visit(node);
		inOrderTraversal(node.right);
	}
}

/* Heaps */
//heaps are complete binary tree (all filled except last level)
//min heap: minimum element on top
//Insert: put new node at last spot, then bubble up new node until heap property satisfied. O(log n)
//Remove min: swap root with last element in heap, then bubble down the new root until heap property satisfied

class Graph{
	Node[] nodes;
}

class Node{
	String name;
	Node[] adjacent;
}

//DFS: as long as you have children, visit children
void search(Node node){
	if (node == null) return;
	visit(node);
	node.visited=true; //step 1: set visited to true
	for (Node a : node.adjacent){ //step 2: visit unvisited children
		if(a.visited == false){
			search(a);
		}
		return {};
	}
}

//BFS: if you have children, enqueue them. Visit according to queue sequence.
void search(Node node){
	Queue queue=new Queue();
	node.marked=true;  //step 1: mark it
	queue.enqueue(node); //step 2: enqueue

	while(!queue.isEmpty()){
		Node n=queue.dequeue(); //remove front of queue
		visit(n);
		for (Node a in n.adjacent){
			if(!a.marked){
				a.marked=true; //step 1: mark it
				queue.enqueue(a); //step 2: enqueue
			}
		}
	}
}

//4.1 Route between nodes: given a directed graph, find whether or not there is a route between two nodes

//BFS

enum State{Unvisited, Visited, Enqueued;} //"Visited" is kind of useless

boolean search(Graph g, Node start, Node end){
	if (start == end) return true; //done
	
	LinkedList<Node> queue=new LinkedList<Node>(); //our queue
	
	for(Node node:g.getNodes()){
		node.state=State.Unvisited; //set all nodes to unvisited
	}
	
	start.state=State.Enqueued;
	queue.add(start);
	Node u;
	while (!queue.isEmpty()){
		u = queue.removeFirst(); //dequeue
		if (u != null){
			for(Node v: n.getAdjacent()){ //enqueue all the unvisited neighbors
				if(v.state == State.Unvisited){
					if (v == end){
						return true;
					}else{
						v.state = State.Enqueued;
						queue.add(v);
					}
				}
			}
		}
		u.state = State.Visited;
	}
	
	return false; //if end found, we would have returned true in the search already
}

//4.2 Minimal Tree: given a sorted array with unique integers, create a binary search tree with minimal height

//Strategy: recurse: middle of array=root, left half = left subtree, right half=right subtree. Recurse on left and right subtree.

TreeNode createMinimalBST(int[] array){
	return createMinimalBST(array, 0, array.length-1);
}

TreeNode createMinimalBST(int arr[], int start, int end){
	int (end < start) return null; //on last iteration, where previous iteration array only contained 1 element
	
	int mid = (start+end)/2;
	TreeNode n=new TreeNode(arr[mid]); //root 
	n.left=createMinimalBST(arr,start,mid-1); //create left subtree (if n is leaf, next round will return null)
	n.right=createMinimalBST(arr,mid+1,end); //create right subtree (if n is leaf, next round will return null)
	
	return n;
}

//4.3 List of Depths: given a binary tree, create a linked list of each level. (i.e. Tree with depth D has D linked lists)
//O(N) time, since we touch each node once and add it to some list

ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
	ArrayList<LinkedList<TreeNode>> lists=new ArrayList<LinkedList<TreeNode>>();
	createLevelLinkedList(root, lists, 0); //initial call
	return lists;
}

void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level){ //start with level 0
	if(root == null) return;
	
	LinkedList<TreeNode> list = null;
	if (lists.size() == level){ //we start with level 0, so if we have added level 0, list.size should be 1. If they equal, level not in list
		list = new Linkedlist<TreeNode>(); //create new list for level
		lists.add(list); //add it to our list of lists
	}else{
		list = lists.get(level);
	}
	
	list.add(root); //this will be later called by left and right subtree nodes, so they will add themself to the list of this level
	createLevelLinkedList(root.left, lists, level+1); //first append the left subtree nodes to the list
	createLevelLinkedList(root.right, lists, level+1); //then append the right subtree nodes to the list
}

//4.4 Check Balanced: check if a binary tree is balanced (i.e. heights of two subtrees of any node <= 1)
//O(N log N), N=checkHeight, logN=number of levels till we get to base case

//Method 1: not efficient since for each node at each level, we go through all the nodes below this level to getHeight. Lots of repeated search!
int getHeight(TreeNode root){ //height = # of levels - 1, height is NOT equal to levels!
	if (root == null) return -1;
	return Math.max(getHeigt(root.left), getHeight(root.right))+1; //the "+1" is where I assume next level will increase my height by 1, but if it doesn't, I have to return "-1" later
}

boolean isBalanced(TreeNode root){
	if(root == null) return true;
	
	int heightDiff = getHeight(root.left) - getHeight(root.right);
	if (Math.abs(heightDiff) > 1){
		return false;
	}else{
		return isBalanced(root.left) && isBalanced(root.right);
	}
}

//Method 2: Efficient - propagate up error early - O(N) time and O(H) space
int checkHeight(TreeNode root){
	if(root == null) return -1;
	
	int leftHeight = checkHeight(root.left);
	if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; //if left not balanced: pass error up. We use h: MIN_VALUE to differentiate from null tree (h: -1)
	
	int rightHeight = checkHeight(root.right);
	if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; //if right not balanced, pass error up
	
	//only check left and right height diff if both left or right subtree are balanced
	int heightDiff = leftHeight - rightHeight;
	if (Math.abs(heightDiff) > 1){
		return Integer.MIN_VALUE; //found error, pass it up
	}else{
		return Math.max(leftHeight, rightHeight) + 1;
	}
}

boolean isBalanced(TreeNode root){
	return checkHeight(root) != Integer.MIN_VALUE;
}

//4.5 Is Binary Search Tree: check if a binary tree is a binary search tree

//Method 1: In-order traversal

int index=0;
void copyBST(TreeNode root, int[] array){ //recursive, in the end order of elements in array will be filled by in order traversal
	if(root == null) return;
	copyBST(root.left, array); //copy left subtree
	array[index] = root.data; //copy root
	index++;
	copyBST(root.right, array); //copy right subtree
}

boolean checkBST(TreeNode root){
	int[] array = new int[root.size];
	copyBST(root, array);
	for (int i=1; i<array.length; i++){
		if(array[i] <= array[i-1]) return false;
	}
	return true;
}

//Method 2: In order traversal improved. Since we only need to compare current vs. previous in traversal, no need for array

Integer previous=null;
boolean checkBST(TreeNode n){
	if(n==null) return true;
	
	if(!checkBST(n.left)) return false; //check left subtree. If left is not BST, return false
	
	if(previous !=null && n.data <= previous){ //check root with last node in our traversal
		return false;
	}
	
	if(!checkBST(n.right)) return false;
	
	return true; // all good
}

//Method 3: using min and max to bound our range of acceptable values for it to be BST

boolean checkBST(TreeNode n){
	return CheckBST(n, null, null);
}

boolean checkBST(TreeNode n, Integer min, Integer max){
	if(n==null) return true;
	
	if(min != null && n.data <= min || max !=null && n.data > max){
		return false;
	}
	
	//check if left and right are BST, where left's new max is root, and right's new min is root
	if (!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max)){
		return false; //if either is not BST, return false
	}
	
	return true;
}

//4.6 Successor: find next node in a binary search tree if doing in order traversal. 
// Assume each node can access its parent

TreeNode inorderSuccessor(TreeNode n){ //assume we have already traversed n, the root
	if (n==null) return null;
	
	if(n.right!=null){
		return leftMostChild(n.right); //left most child of right subtree is the next in in order traversal
	}
	else{
		TreeNode q=n;
		TreeNode x=q.parent;
		//go up until we are left of a node instead of right
		while(x!=null && x.left !=q){ //x.left!=q means q=x.right. if q=x.right, we must have traversed x, since in order = LNR, R is after N
			q=x;
			x=x.parent;
		}
		return x;
	}
}

TreeNode leftMostChild(TreeNode n){ //assume we only call leftMostChild if n.right is not null
	while(n.left!=null){
		n=n.left;
	}
	return n;
}

//4.7 Build Order
//Input:
//	Projects: a,b,c,d,e,f
//	Dependencies: (a,d),(f,b),(b,d),(f,a),(d,c) ---> (a,d)=d dpeneds on a
//Output: f,e,a,b,d,c

//Strategy: do DFS, then push nodes onto a stack, so the first ones being pushed are the leaves (since DFS returns bottom up) who depend on no one.
//so in above example, we will push onto stack: c,d,b,a,e,f. When you pop, you will get: f,e,a,b,d,c

//First let's define all the classes we will need'

public class Project{
	public enum State{COMPLETE, PARTIAL, BLANK}; //COMPLETE: completed checking node and its children, PARTIAL: in the mid of checking its children, BLANK: haven't checked its children yet
	private State state = State.BLANK; //initialize each project to be BLANK
	State getstate();
	void setState(State st) { state = st;}
}

public class Graph{
	privte ArrayList<Project> nodes=new ArrayList<Project>(); //all the nodes (projects) in the graph
	private HashMap<String, Project> map=new HashMap<String, Project>(); //{"a":Project a, "b":Project b...}
}

//This is the function we will call
Project[] findBuilderOrder(String[] projects, String[][] dependencies){
	Graph graph=buildGraph(projects, dependencies);
	return orderProjects(graph.getNodes());
}

Graph buildGraph(String[] projects, String[][] dependencies){
	Graph graph=new Graph();
	for (String project: projects){ //draw the nodes (projects)
		graph.createNode(project);
	}
	for (String[] dependency: dependencies){ //draw the edges (dependencies)
		String first=dependency[0];
		String second=dependency[1];
		graph.addEdge(first,second);
	}
	return graph;
}

Stack<Project> orderProjects(ArrayList<Project> projects){
	Stack<Project> stack=new Stack<Project>();
	for(Project project: projects){
		if(project.getState() == Project.State.BLANK){
			if(!doDFS(project,stack)){ //cycle 
				return null; //error
			}
		}
	}
	return stack;
}

boolean doDFS(Project project, Stack<Project> stack){ //returns false if there's a cycle
	if(project.getState() == Project.State.PARTIAL){
		return false; //cycle since we've come back to the same node again. We must have previously visited it or else state won't be partial.
	}
	
	if (project.getState() == Project.State.BLANK){
		project.setState(Project.State.PARTIAL); //mark state to partial as we are going to traverse its children
		ArrayList<Project> children=project.getChildren();
		for(Project child:children){
			if(!doDFS(child,stack)){
				return false; 
			}
		}
		project.setState(Project.State.COMPLETE);
		stack.push(project); //the first projects that gets pushed onto the stack are the leaves of DFS who have no children
	}
	return true;
	
}

//4.8 First Common Ancestor: find first common ancestor of two nodes in a binary tree, not necessarily BST.

//Method 1: With links to parents - trace n1 and n2's paths up until they intersect; O(d), d=depth of deeper node

TreeNode commonAncestor(TreeNode p, TreeNode q){
	int depth_diff=depth(p)-depth(q);
	TreeNode first=delta > 0? q:p; //get shallower node
	TreeNode second=delta > 0? p:q; //get deeper node
	
	second=goUpBy(second, Math.abs(depth_diff)); //move deeper node up to be same level as shallower
	
	//move both first and second node up until they equal
	while(first!=second && first!=null && second!=null){
		first=first.parent;
		second=second.parent;
	}
	
	return first==null || second == null? null: first; //if not null, common parent found, then return either first or second (here we do first)
}

TreeNode goUpBy(TreeNode node, int delta){
	while (delta > 0 && node!=null){
		node=node.parent;
		delta--;
	}
	return node;
}

int depth(TreeNode node){ //find which level a node is on
	int depth=0;
	while(node !=null){ //increase depth by 1 until you hit root (top)
		node=node.parent;
		depth++;
	}
	return depth;
}

//Method 2: Without links to parents - must search top down from root O(n)

TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q){
	if(!covers(root,p) || !covers(root,q)){ //error: at least one node is not in the tree, or root is null
		return null; 
	}
	return ancestorHelper(root, p, q);
}

TreeNode ancestorHelper(TreeNode node, TreeNode p, TreeNode q){
	if(node == null || node==p || node==q){
		return node;
	}
	
	boolean pIsOnLeft = covers(node.left,p);
	boolean qIsOnLeft = covers(node.left, q);
	if (pIsOnLeft != qIsOnLeft){ //nodes on different sides
		return root; //then root is where they diverge (aka "last common ancestor")
	}
	
	//else: p and q on the same side of node's subtrees - either left or right
	TreeNode childSide = pIsOnLeft? root.left : root.right; //which side to explore next
	return ancestorHelper(childSide, p,q);
}


//check if p is somewhere down in root's either subtrees, if yes, return true, else false
boolean covers(TreeNode root, TreeNode p){
	if(root==null) return false; //we've hit a leaf, or we have a null root
	if(root==p) return true;
	return covers(root.left, p) || covers(root.right, p);
}

//4.9 BST Sequences (array weaving): a binary search tree was created by traversing an array left to right and inserting each element. Given a BST with distinct
//elements, print all possible arrays that could have led to this tree.

//Input:  Root=2, Root.Left=1, Root.Right:3
//Output: {2,1,3}, {2,3,1}

//Strategy: at each level, prepend the root to weavings of all possible arrays of left subtree and all possible arrays of right subtree
//when we weave left and right subtree arrays, we gotta make sure the final array maintains relative order of elements within each left and right
//ex. if we wanna Weave({1,2,3},{4,5,6})={1+Weavings({2,3},{4,5,6}), 4+Weavings({1,2,3},{5,6})

ArrayList<LinkedList<Integer>> allSequences(TreeNode node){
	ArrayList<LinkedList<Integer>> result=new ArrayList<LinkedList<Integer>>();
	
	if(node == null){
		result.add(new LinkedList<Integer>());
		return result;
	}
	
	LinkedList<Integer> prefix=new LinkedList<Integer>();
	prefix.add(node.data); //root's data will be appended to all combos of left and right subtrees
	
	ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
	ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
	
	for(LinkedList<Integer> left:leftSeq){
		for(LinkedList<Integer> right:rightSeq){
			ArrayList<LinkedList<Integer>> weaved=new ArrayList<LinkedList<Integer>>();
			weaveLists(left, right, weaved, prefix); //will make modifications to "weaved"
			result.addAll(weaved);
		}
	}
	
	return result;
}

void weaveLists(LinkedList<Integer> left, LinkedList<Integer> right, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){
	
	if(left.size() == 0 || right.size() == 0){ //either left or right sequences are empty
		LinekdList<Integer> result = (LinkedList<Integer>) prefix.clone(); //first add our prefix
		result.addAll(left);  //don't know which one is empty we will just add both
		result.addAll(right);  //don't know which one is empty we will just add both
		result.add(result); //this is where we actually add to the list. When it's just one element left, we will just add the prefix.
		return;
	}
	
	//recurse with left.head added to the prefix. Removing the head will damage left, so we need to put it back where we found it afterward.
	int left_head = left.removeFirst(); 
	prefix.addLast(left_head); //prefix becomes prefix -> left_head
	weaveLists(left,right,results,prefix);
	prefix.removeLast();
	left.addFirst(left_head); //put left's head back in left
	
	//recurse with right.head added to the prefix.
	int right_head = right.removeFirst();
	prefix.addLast(right_head); //prefix becomes prefix -> right_head
	weaveLists(left,right,results,prefix);
	prefix.removeLast();
	right.addFirst(right_head); //put right's head back in right
	
	
}

//4.10: Check Subtree: T1 and T2 are two very large binary trees, with T1 >>> T2. Check if T2 is a subtree of T1.
//O(n+m), n T1 nodes and m T2 nodes


//Strategy: traverse T1 and T2 and record order of traversals as a string. if T2's string is a substring of T1, we are good.

//Note: in order traversal will not work for BSTs with same values but different structure. It will always print values sorted.
// i.e. Tree 1: Root=2, Left=1, Right=3. Tree 2: Root=1, Root.right=2, Root.right.right=3. Different structure, but same in-order results.

boolean containsTree(TreeNode t1, TreeNode t2){
	StringBuilder string1=new StringBuilder();
	StringBuilder string2=new StringBuilder();
	
	getOrderString(t1, string1);
	getOrderString(t2, string2);
	
	return string1.indexOf(string2.toString())!= -1; //if string2 is a substring of string1, then T2 is subtree of T1
}

void getOrderString(TreeNode node, StringBuilder sb){ //preorder traversal: NLR
	if(node == null){
		sb.append("X"); //if we don't append null nodes we may miss out on structural information. Structural isomers are NOT equivalent trees!
		return;
	}
	
	sb.append(node.data + " ");
	getOrderString(node.left, sb); //add left
	getOrderString(node.right, sb); //add right
}

//4.11 Random node: return a random node in binary search tree. All nodes should be equally likely to be chosen.

//Strategy: Assign each node in tree an index based on in-order traversal. Choose a random node as index and get that node.

class Tree{
	TreeNode root=null;
	
	public int size() {return root == null? 0: root.size();} //returns size of entire tree, which is size at root
	
	public TreeNode getRandomNode(){
		if(root == null) return null;
		Random random = new Random();
		int i=random.nextInt(size()); //generate a number between 0 and size()
		return root.getIthNode(i);
	}
	
	public void insertInOrder(int value){
		if (root == null){
			root = new TreeNode(value);
		}else{
			root.insertInOrder(value);
		}
	}
}

class TreeNode{
	
	private int data;
	public TreeNode left;
	public TreeNode right;
	private int size=0; //for each node, stores size beneath this node (i.e. how many nodes are beneath me)
	
	public TreeNode(int d){
		data=d;
		size=1;
	}
	
	public TreeNode getIthNode(int i){
		int leftSize = left == null? 0: left.size();
		if (i < leftSize){
			return left.getIthNode(i); //recurse in left tree and find it
		}
		else if (i == leftSize){
			return this; //return root
		}
		else{
			return right.getIthNode(i - (leftSize+1)); //i must fall between 0 and right.size, so we need to remap by substracting offset
		}
	}
	
	public void insertInOrder(int d){
		if(d <= data){
			if(left == null){
				left = new TreeNode(d);
			}else{
				left.insertInOrder(d);
			}
		}
		else{
			if (right == null){
				right = new TreeNode(d);
			}else{
				right.insertInOrder(d);
			}
		}
		size++;
	}
}

//4.12 Paths with Sum: given a binary tree where each node is an int (pos or neg), count the number of paths that sum to a given value. 
//Path does not need to start or end at the root or a leaf, but must go downwards (travel from parent to child)
//O(N)

//Strategy: What we are really looking for is a contiguous path from node x to node y where nodes in between x and y add to a target sum
//runningSum_y = sum along path from root to y, runningSum_x = sum along path from root to x, where x has to be on y's path
//runningSum_y = runningSum_x + targetSum
//we will store runningSum_y in a hash table

int countPathsWithSum(TreeNode root, int targetSum){
	return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
}

int countPathsWithSum(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount){
	if (node == null) return 0; //base case
	
	//count paths with sum beginning at root and ending at the current node equal to targetSum
	runningSum += node.data;
	int sum = runningSum - targetSum;
	int totalPaths = pathCount.getOrDefault(sum, 0); //default is 0
	
	if(runningSum == targetSum){ //if runningSum equals targetSum, then one additional path starts at root, since runningSum is cumulative from root
		totalPaths++;
	}
	
	incrementHashTable(pathCount, runningSum, 1); //add current running sum at current node to the hash table
	totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount); //get total paths  that sum to target from left subtree
	totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount); //get total paths that sum to target from right subtree
	incrementHashTable(pathCount, runningSum, -1); //remove this from hash table to nullify this path since we've already checked its left and right subtrees, so other nodes can't use it again.
	
	return totalPaths;
}

void incrementHashTable(HashMap<Integer, Inger> HashTable, int key, int delta){
	int newCount = hashTable.getOrDefault(key, 0) + delta;
	if(newCount == 0){ //remove when zero to reduce space usage
		hashTable.remove(key);
	}else{
		hashTable.put(key, newCount);
	}
}