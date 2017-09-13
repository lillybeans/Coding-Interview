package Trees;
import Trees.TreeNode;

public class maximumSumPath {
	public static int maxPathSum(TreeNode root) {
        return maxPathSumHelper(root,0,0);
    }
    
    public static int maxPathSumHelper(TreeNode cur, int topSum, int max_left_to_right){
        if(cur == null)
        {
            return topSum;
        }
        
        int leftSum=maxPathSumHelper(cur.left,topSum+cur.val, max_left_to_right);
        int rightSum=maxPathSumHelper(cur.right,topSum+cur.val, max_left_to_right);
        
        int left_to_right = leftSum + rightSum - 2*topSum - cur.val; //if this is max
        if (left_to_right > max_left_to_right){
        	max_left_to_right = left_to_right;
        }
        
        System.out.println("cur.val="+cur.val+" ,TL: "+leftSum+", TR: "+rightSum+", max LR:"+max_left_to_right);
        
        return Math.max(Math.max(leftSum,rightSum),max_left_to_right);
        
    }
    
    public static void main(String[] args){
    	int[] array = {-1,0,1};
    	
    	TreeNode root = TreeNode.buildTree(array);
    	System.out.println(maxPathSum(root));
    }
}
