package books.jianzhioffer;

import datastructure.trees.Tree;
import datastructure.trees.TreeNode;

public class Question19 {

	void translateToMirror(Tree t) {
		if (t.isEmpty())
			return;
		mirror(t.root);
	}

	void mirror(TreeNode node) {
		if (node == null)
			return;
		TreeNode tmp = node.left;
		node.left = node.right;
		node.right = tmp;
		mirror(node.left);
		mirror(node.right);
	}

	public static void main(String[] args) {
		Tree treeA = new Tree("8(8(9,2(4,7)),7)");
		Question19 q = new Question19();
		treeA.printByMidOrder();
		q.translateToMirror(treeA);
		treeA.printByMidOrder();
	}

}
