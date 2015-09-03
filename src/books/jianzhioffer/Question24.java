package books.jianzhioffer;

import datastructure.trees.Tree;
import datastructure.trees.TreeNode;

public class Question24 {

	boolean isTrue = false;
	int index = 0;

	/***
	 * 理解错误！！！！！！！！！！
	 * @author Administrator
	 *
	 */
	boolean isBackTrack(int[] arr, Tree t) {
		TreeNode node = t.root;

		backOrder(node, arr);
		return isTrue;
	}

	void backOrder(TreeNode node, int[] arr) {
		if (isTrue || node == null)
			return;
		backOrder(node.right, arr);
		if (!isTrue && node.data == arr[index]) {
			index++;
			if (index == arr.length) {
				isTrue = true;
			}
		} else {
			index = 0;
		}
		backOrder(node.left, arr);
	}

	/**
	 * 修正后的代码
	 * @param arr
	 * @return
	 */
	boolean isLeagel(int[] arr) {
		isTrue = false;
		return isBackOrder(arr, 0, arr.length - 1);
	}

	boolean isBackOrder(int[] arr, int start, int end) {
		if (start >= end)
			return true;
		int split = start;
		while (arr[split] < arr[end])
			split++;
		int tmp = split;
		while (tmp < end) {
			if (arr[tmp++] < arr[end]) {
				return false;
			}
		}
		return isBackOrder(arr, start, split - 1)
				&& isBackOrder(arr, split + 1, end);

	}

	public static void main(String[] args) {
		Question24 q = new Question24();
		Tree t = new Tree("8(6(5,7),10(9,7))");
		//		int[] arr = { 7, 4, 6, 5 };
		int[] arr = { 5, 7, 6, 9, 11, 10, 8 };
		System.out.println(q.isBackTrack(arr, t));
		System.out.println(q.isLeagel(arr));
	}
}
