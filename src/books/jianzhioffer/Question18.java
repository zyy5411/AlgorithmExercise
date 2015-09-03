package books.jianzhioffer;

import datastructure.trees.Tree;
import datastructure.trees.TreeNode;

public class Question18 {

	boolean isSubStructure(Tree a, Tree b) {
		return DFSSearch(a.root, b.root);
	}

	boolean DFSSearch(TreeNode nodeA, TreeNode nodeB) {

		if ((nodeA.data == nodeB.data && isSubStructure(nodeB, nodeA))
				| (nodeA.left != null && DFSSearch(nodeA.left, nodeB))
				| (nodeA.right != null && DFSSearch(nodeA.right, nodeB))) {
			return true;
		}
		return false;
	}

	boolean isSubStructure(TreeNode nodeB, TreeNode nodeA) {
		if ((nodeB.data != nodeA.data)
				| (nodeB.left != null && !isSubStructure(nodeB.left, nodeA.left))
				| (nodeB.right != null && !isSubStructure(nodeB.right,
						nodeA.right))) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Tree treeA = new Tree("8(8(9,2(4,7)),7)");
		Tree treeB = new Tree("2(4,7)");
		Question18 q = new Question18();
		System.out.println(q.isSubStructure(treeA, treeB));
	}

}
