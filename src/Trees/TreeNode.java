package Trees;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class TreeNode {

	 int val;
	 TreeNode left;
	 TreeNode right;
	 TreeNode(int x) { val=x;}
	 
	 public static TreeNode buildTree(int[] arr){
		 TreeNode root = new TreeNode(arr[0]);
		 for(int i=1; i<arr.length; i++){
			 TreeNode node = new TreeNode(arr[i]);
			 insert(root,node);
			 //insertFail(root,node);
		 }
		 return root;
	 }
	 
	 //BST but not necessarily full or complete!
	 public static void insert(TreeNode root, TreeNode node){
		 
		 if(node.val < root.val){
			 if(root.left == null){
				 root.left = node;
			 }
			 else{
				 insert(root.left, node);
			 }
		 }
		 else{
			 if(root.right == null){
				 root.right = node;
			 }
			 else{
				 insert(root.right,node);
			 }
		 }
	 }
	 
	 //This method will fail!!! BAD!!
	 /*
	 public static void insertFail(TreeNode root, TreeNode node){
		 if(root == null){ //this literally won't do anything!
			 root=node; 
			 return;
		 }
		 
		 if(node.val < root.val){
			 insertFail(root.left,node); //will NOT set root.left = node in the next round, because root.left is null, so no reference/link set up between root.left and its object!
		 }
		 else
		 {
			 insertFail(root.right,node);
		 }
	 }
	 */
	 
	 public static void printByLevel(TreeNode root){
		 if(root == null){
			 return;
		 }
		 
		 ArrayList<ArrayList<TreeNode>> level_lists = new ArrayList<ArrayList<TreeNode>>();
		 ArrayList<TreeNode> current_level = new ArrayList<TreeNode>();
		 ArrayList<TreeNode> next_level = new ArrayList<TreeNode>();
		 
		 current_level.add(root);
		 
		 while(current_level.size()>0){
			 level_lists.add(current_level);
			 next_level = new ArrayList<TreeNode>();
			 for(TreeNode node:current_level){
				 if(node.left!=null){
					 next_level.add(node.left);
				 }
				 if(node.right!=null){
					 next_level.add(node.right);
				 }
			 }
			 current_level=next_level;
		 }
		 
		 for(int i=0; i<level_lists.size(); i++){
			 System.out.print("level "+i+": ");
			 for(TreeNode node: level_lists.get(i)){
				 System.out.print(node.val+" ");
			 }
			 System.out.println();
		 }
		
	 }
	 
	 //4 levels means the tree has 4 layers, root is layer 1
	 public static int getLevels(TreeNode node){ //pass root to this
		 if(node == null){
			 return 0; 
		 }
		 
		 return 1+Math.max(getLevels(node.left),getLevels(node.right));
	 }
	 
	 public static void inOrder(TreeNode cur){
		 if(cur == null){
			 return;
		 }
		 
		 inOrder(cur.left);
		 System.out.print(cur.val);
		 inOrder(cur.right);
	 }
	 
	 public static void main(String[] args){
		 int[] arr={10,4,6,2,20,8};
		 /*
		  * 			10
		  *         /        \
		  * 	4				20
		  *   /   \
		  * 2		6
		  *           \
		  * 			8
		  */

		 TreeNode root=buildTree(arr);
		 //inOrder(root);
		 printByLevel(root);
		 System.out.println(getLevels(root));
		 
	 }
}
