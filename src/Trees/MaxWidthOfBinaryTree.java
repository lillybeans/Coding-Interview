package Trees;

import java.util.ArrayList;

public class MaxWidthOfBinaryTree {

	static int[] arr;
	
	static int maxWidth(TreeNode root){
		int levels=TreeNode.getLevels(root);
		arr = new int[(int)Math.pow(2,levels)-1];
		fillArray(root,0);
		
		int maxWidth=0;
		
		for(int i=0; i<levels; i++){
			int level_start=(int)Math.pow(2,i)-1; //ex. i=3, 2^3-1=7
			int level_end=(int)Math.pow(2,i+1)-2; //ex. i=3, 2^(3+1)-2=14
			
			int min=Integer.MIN_VALUE; //minimum node index in current level
			int max=Integer.MAX_VALUE; //maximum node index in current level
			
			for(int j=level_start; j<=level_end; j++){
				min = Math.min(arr[j],min);
				max = Math.max(arr[j],max);
			}
			
			int level_width=max-min+1;
			maxWidth=Math.max(maxWidth, level_width);
		}
		
		return maxWidth;
	}
	
	static void fillArray(TreeNode node, int i){
		if(node == null){
			return;
		}
		arr[i]=node.val;
		fillArray(node.left, 2*i+1);
		fillArray(node.right,2*i+2);
	}
}
