package books.beautyofprogram.chapter3;

import util.Tools;

public class CopyOfExercise3_4 {

	int number;
	Node root;
	
	void createLink(int n){
		number = n;
		root = new Node(-1, create(n));
		root.next.print();
//		Node n1 = new Node(1,new Node(2,null));
//		Node n2 = n1;
//		n2.next = null;
//		Node n3 = n1.next.next;
	}
	
	private Node create(int n){
		if(n == 1)
			return new Node(1,null);
		return new Node(n, create(n-1));
	}
	
	public void reverse(){
		Node p = root.next;
		Node pNext = p.next;
//		root.next.next = null;
		root.next.next.setNext (null);
//		pNext.setNext ( new Node(55,pNext.next));
//		pNext.data = 123;
//		pNext.next.data = 321;
		Tools.println(""+pNext.data);
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
		CopyOfExercise3_4 e = new CopyOfExercise3_4();
		e.createLink(10);
		e.reverse();

	}

}
