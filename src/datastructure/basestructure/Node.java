package datastructure.basestructure;

public class Node<E> {
	public E data;
	public Node<E> next;

	public void print() {
		Node<E> n = this;
		while (null != n) {
			System.out.print(n.data + " ");
			n = n.next;
		}
		//		System.out.print(data + " ");
		//		if (null != next)
		//			next.print();
	}

	@Override
	public String toString() {
		return "node:" + data;
	}
}