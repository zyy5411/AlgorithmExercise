package datastructure.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树相关算法，包括二叉树遍历（先、中、后序遍历）
 * 
 * @author zyy
 *
 */
public class BinaryTreeAlgrithm {
	List<TreeNode> orderedNodeList = new ArrayList<TreeNode>();

	void midOrder(TreeNode n) {
		if (n == null)
			return;
		orderedNodeList.clear();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (n != null) {
			stack.push(n);
			n = n.left;
		}
		while (!stack.isEmpty()) {
			TreeNode popNode = stack.pop();
			orderedNodeList.add(popNode);
			popNode = popNode.right;
			while (popNode != null) {
				stack.push(popNode);
				popNode = popNode.left;
			}
		}
		print("mid:", orderedNodeList);
	}

	void preOrder(TreeNode n) {
		if (n == null)
			return;
		orderedNodeList.clear();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (n != null) {
			orderedNodeList.add(n);
			stack.push(n);
			n = n.left;
		}
		while (!stack.isEmpty()) {
			TreeNode popNode = stack.pop();
			popNode = popNode.right;
			while (popNode != null) {
				orderedNodeList.add(popNode);
				stack.push(popNode);
				popNode = popNode.left;
			}
		}
		print("pre:", orderedNodeList);

	}

	void postOrder(TreeNode n) {
		if (n == null)
			return;
		orderedNodeList.clear();
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		while (n != null) {
			stack1.push(n);
			n = n.left;
		}
		while (!stack1.isEmpty()) {
			n = stack1.pop();
			if (!stack2.isEmpty() && n == stack2.peek()) {
				orderedNodeList.add(n);
				stack2.pop();
			} else if (n.right != null) {
				stack1.push(n);
				stack2.push(n);
				n = n.right;
				while (n != null) {
					stack1.push(n);
					n = n.left;
				}
			} else {
				orderedNodeList.add(n);
			}
		}
		print("post:", orderedNodeList);
	}

	private void print(String desc, List<TreeNode> orderedNodeList2) {
		System.out.print(desc + " ");
		for (TreeNode n : orderedNodeList2) {
			System.out.print(n.data + " ");
		}
		System.out.print("\n");

	}

	public static void main(String[] args) {
		Tree t = new Tree("3(5(1(,7(3,4)),2),7(3,9))");
		BinaryTreeAlgrithm treeAlgrithm = new BinaryTreeAlgrithm();
		treeAlgrithm.midOrder(t.root);
		treeAlgrithm.preOrder(t.root);
		treeAlgrithm.postOrder(t.root);
	}

}
