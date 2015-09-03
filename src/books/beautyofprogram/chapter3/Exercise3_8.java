package books.beautyofprogram.chapter3;


public class Exercise3_8 {

	Node root;
	int maxDistance;
	
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
	
	/**
	 * ֱ��ʹ�õݹ飬�ܼ򵥣���Ч�ʽϵ͡�
	 * */
	int getMaxDistance(){
		
		getMaxDepth(root);
		return maxDistance;
	}
	
	int getMaxDepth(Node node){
		int leftLen = 0,rightLen = 0;
		if(null != node.left)
			leftLen = getMaxDepth(node.left)+1;
		if(null != node.right)
			rightLen = getMaxDepth(node.right)+1;
		maxDistance = leftLen + rightLen > maxDistance ? leftLen + rightLen : maxDistance;
		return leftLen > rightLen ? leftLen : rightLen;
	}

	/**
	 * ʹ��ջ��ʵ�֡�
	 * */
	
	public static void main(String[] args) {
		Exercise3_8 e = new Exercise3_8();
		e.initNodes();
		System.out.println(e.getMaxDistance());
	}

	class Node {
		Node left;
		Node right;
		public Node(Node l,Node r){
			left = l;
			right = r;
		}
		public Node(){
			
		}
	}

}
