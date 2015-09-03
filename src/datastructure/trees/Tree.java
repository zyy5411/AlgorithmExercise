package datastructure.trees;

public class Tree {

	public TreeNode root;

	/**
	 * 
	 * @param str 用来描述数的字符串，使用逗号和括号，数字和符号之间用空格分开
	 */
	public Tree(String str) {
		StringReader sr = new StringReader(str);
		root = new TreeNode(sr, null);
	}

	public void printByMidOrder() {
		root.printByMidOrder();
		System.out.println();
	}

	public boolean isEmpty() {
		return root == null;
	}

	public static void main(String[] args) {
		Tree t = new Tree("8(8(9,2(4,7)),7)");
		t.printByMidOrder();
	}
}
