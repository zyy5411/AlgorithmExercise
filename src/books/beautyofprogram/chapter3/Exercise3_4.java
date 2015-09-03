package books.beautyofprogram.chapter3;


public class Exercise3_4 {

	int number;
	Node root;
	
	void createLink(int n){
		number = n;
		root = new Node(-1, create(n));
		root.next.print();
	}
	
	private Node create(int n){
		if(n == 1)
			return new Node(1,null);
		return new Node(n, create(n-1));
	}
	
	public void reverse(){
		Node p = root.next;
		Node pNext = p.next;
		while(null != pNext){
			Node tmp = pNext.next;
			pNext.next = p;
			p = pNext;
			pNext = tmp;
		}
		root.next.next = null;
		root.next = p;
		
		System.out.println("\nafter reverse");
		root.next.print();
	}
	
	private class Node{
		int data;
		Node next;
		public Node(int data, Node next){
			this.next = next;
			this.data = data;
		}
		
		public void setNext(Node next) {
			this.next = next;
		}

		void print(){
			System.out.print("  node:"+data);
			if(null != next)
				next.print();
		}
	}
	
	public static void main(String[] args) {
		Exercise3_4 e = new Exercise3_4();
		e.createLink(10);
		e.reverse();

	}

}
