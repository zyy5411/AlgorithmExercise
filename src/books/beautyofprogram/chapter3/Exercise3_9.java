package books.beautyofprogram.chapter3;

import util.Tools;

public class Exercise3_9 {

	Node root;

	void rebuildTree(String pre, String mid) {
		root = Node.create(pre, mid);
	}

	void prePrint() {
		Tools.println("\npre print:");
		root.preprint();
	}

	void midPrint() {
		Tools.println("\nmid print:");
		root.midprint();
	}

	public static void main(String[] args) {
		Exercise3_9 e = new Exercise3_9();
		String pre = "abdcef";
		String mid = "dbaecf";
		e.rebuildTree(pre, mid);
		e.prePrint();
		e.midPrint();
	}

}

class Node {
	Node left;
	Node right;
	char value;
	String pre;
	String mid;

	//		public Node(String pre, String mid) {
	//			this.pre = pre;
	//			this.mid = mid;
	//			value = pre.charAt(0);
	//			int index = mid.indexOf(value);
	//			if (0 == index)
	//				left = null;
	//			else
	//				left = new Node(pre.substring(1, index + 1), mid.substring(0,
	//						index));
	//			if (mid.length() - 1 == index)
	//				right = null;
	//			else
	//				right = new Node(pre.substring(index + 1),
	//						mid.substring(index + 1));
	//		}

	public static Node create(String pre, String mid) {
		if (pre.length() == 0)
			return null;
		Node node = new Node();
		node.pre = pre;
		node.mid = mid;
		node.value = pre.charAt(0);
		int index = mid.indexOf(node.value);
		node.left = Node.create(pre.substring(1, index + 1),
				mid.substring(0, index));
		node.right = Node.create(pre.substring(index + 1),
				mid.substring(index + 1));
		return node;
	}

	void preprint() {
		System.out.print(value);
		if (null != left)
			left.preprint();
		if (null != right)
			right.preprint();
	}

	void midprint() {
		if (null != left)
			left.midprint();
		System.out.print(value);
		if (null != right)
			right.midprint();
	}
}
