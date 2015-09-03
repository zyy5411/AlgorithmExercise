package books.jianzhioffer;

import util.Tools;
import datastructure.trees.Tree;
import datastructure.trees.TreeNode;

public class Question7_2 {

	TreeNode parent = null, currentParent;

	TreeNode getCommonParent(TreeNode root, TreeNode node1, TreeNode node2) {
		midOrderFind(root, node1, node2);
		Tools.println("p:" + parent.data);
		return null;
	}

	TreeNode midOrderFind(TreeNode node, TreeNode node1, TreeNode node2) {
		if (null == node || null != parent)
			return null;
		midOrderFind(node.left, node1, node2);
		if (node == node1 || node == node2) {
			if (null == currentParent) {
				currentParent = node;
				//				findOne = true;
			} else {
				parent = currentParent;
				return null;
			}
		}
		if (currentParent != null
				&& (node.left == currentParent || node.right == currentParent)) {
			currentParent = node;
		}
		midOrderFind(node.right, node1, node2);
		if (currentParent != null
				&& (node.left == currentParent || node.right == currentParent)) {
			currentParent = node;
		}
		return null;
	}

	public static void main(String[] args) {
		Tree t = new Tree("8(11(9,2(4,7)),7)");
		Question7_2 q = new Question7_2();
		q.getCommonParent(t.root, t.root.left, t.root.left.right.right);
	}
}
