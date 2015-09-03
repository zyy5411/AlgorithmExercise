package books.jianzhioffer;

import java.util.ArrayList;
import java.util.List;

import util.Tools;
import datastructure.trees.Tree;
import datastructure.trees.TreeNode;

public class Question25 {

	List<Integer> path = new ArrayList<Integer>();

	void printThePath(Tree t, int n) {
		TreeNode node = t.root;
		DFS(node, n, 0);
	}

	void DFS(TreeNode node, int n, int currentSum) {
		if (null == node) {
			return;
		}
		//¼ôÖ¦
		if (currentSum > n)
			return;
		path.add(node.data);
		//Ò¶×Ó½Úµã
		if (null == node.left && null == node.right
				&& currentSum + node.data == n) {
			Tools.print(path);
		} else {
			DFS(node.left, n, currentSum + node.data);
			DFS(node.right, n, currentSum + node.data);
		}
		path.remove(path.size() - 1);

	}

	public static void main(String[] args) {

		Tree t = new Tree("10(5(4,7),12)");
		int n = 22;
		Question25 q = new Question25();
		q.printThePath(t, n);
	}

}
