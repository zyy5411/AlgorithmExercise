package books.jianzhioffer;

import datastructure.trees.Tree;
import datastructure.trees.TreeNode;

public class Question39 {

	boolean isBalance = true;

	boolean isBalanceTree(Tree tree) {
		TreeNode root = tree.root;
		isBalance(root);
		return isBalance;
	}

	int isBalance(TreeNode node) {
		if (null == node)
			return 0;
		int leftDepth = isBalance(node.left);
		int rightDepth = isBalance(node.right);
		if (Math.abs(leftDepth - rightDepth) > 1) {
			isBalance = false;
		}
		return Math.max(leftDepth, rightDepth) + 1;

	}

	public static void main(String[] args) {

		Tree t = new Tree("1(2(4,5(7,)),3(,6))");
		//		Tree t = new Tree("1(2(4,5(7(6,),)),3(,6))");
		t.printByMidOrder();
		Question39 q = new Question39();
		System.out.println(q.isBalanceTree(t));
	}

}
