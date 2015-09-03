package books.jianzhioffer;

import util.Tools;
import datastructure.trees.Tree;
import datastructure.trees.TreeNode;

public class Question27 {

	TreeNode preNode;
	TreeNode firstNode;

	TreeNode translate(TreeNode node) {
		midOrder(node);
		return firstNode;
		//		node.left = translate(node);
	}

	void midOrder(TreeNode node) {
		if (null == node)
			return;
		midOrder(node.left);
		if (null == preNode) {
			preNode = node;
			firstNode = node;
		} else {
			node.left = preNode;
			preNode.right = node;
			preNode = node;
		}
		midOrder(node.right);
	}

	public static void main(String[] args) {
		Tree t = new Tree("10(6(4,8),14(12,16))");
		TreeNode node = t.root;
		Question27 q = new Question27();
		TreeNode newNode = q.translate(node);
		TreeNode lastNode = null;
		while (newNode != null) {
			Tools.println("" + newNode.data);
			lastNode = newNode;
			newNode = newNode.right;
		}
		Tools.println("==");
		while (lastNode != null) {
			Tools.println("" + lastNode.data);
			lastNode = lastNode.left;

		}
	}
}
