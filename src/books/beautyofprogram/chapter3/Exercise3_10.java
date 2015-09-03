package books.beautyofprogram.chapter3;

import books.beautyofprogram.chapter3.Exercise3_8.Node;

public class Exercise3_10 {

	Node root;
	static int count = 0;
	
	int printNodeAtLevel(int level){
		return printNodeAtLevel(root, level);
	}
	
	int printNodeAtLevel(Node node, int level){
		if(level > 0){
			int result = 0;
			if(null != node.left)
				result = printNodeAtLevel(node.left, level-1);
			if(null != node.right)
				result = printNodeAtLevel(node.right, level-1);
			return result;
		}else if(level == 0){
			System.out.printf(" %d ",node.value);
			return 1;
		}
		
		return 0;
	}

	void printByLevel(){
		int level  = 0;
		while(0 != printNodeAtLevel(level++))
			System.out.print("\n");
	}
	
	void initNodes(){
		root = new Node(
				new Node(
						new Node(
								new Node(),
								null),
						new Node()),
				new Node(
						new Node(
								null,
								new Node()),
						new Node()));
	}
	
	public static void main(String[] args) {
		Exercise3_10 e = new Exercise3_10();
		e.initNodes();
		e.printByLevel();
	}
	
	class Node {
		Node left;
		Node right;
		int value = count ++;
		public Node(Node l,Node r){
			left = l;
			right = r;
		}
		public Node(){
			
		}
	}

}
