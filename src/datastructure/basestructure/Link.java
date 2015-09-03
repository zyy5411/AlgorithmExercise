package datastructure.basestructure;

public class Link<E> {

	public Node<E> head;

	public Link() {
		head = new Node<E>();
	}

	//	public void add(E[] arr) {
	//
	//	}

	/**
	 * 头插or尾插？
	 * 使用头插
	 * @param i
	 */
	public void add(E... i) {
		Node<E> n;
		for (E e : i) {
			n = new Node<E>();
			n.data = e;
			n.next = head.next;
			head.next = n;
		}
	}

	Node<E> remove(E i) {
		Node<E> n = head;
		//find i
		while (null != n.next && i != n.next.data) {
			n = n.next;
		}
		//i is exist
		if (null != n.next) {
			n.next = n.next.next;
		}

		print();
		return n;
	}

	//从后往前，找出第K个，K从1开始
	public Node<E> findKFromBack(int k) {
		if (k <= 0)
			return null;
		int index = 0;
		Node<E> nodeK = null, node = head;

		while ((node = node.next) != null) {
			index++;
			if (index > k) {
				nodeK = nodeK.next;
			} else if (index == k) {
				nodeK = head.next;
			}
		}
		return nodeK;
	}

	//从后往前，找出第K个，K从1开始,递归算法
	public Node<E> findKFromBackRecurse(int k) {
		int[] index = { 1 };
		return findKRecurse(head, index, k);
	}

	Node<E> findKRecurse(Node<E> node, int[] index, int k) {
		if (node == null) {
			index[0] = 0;
			return null;
		}
		Node<E> nodeK = findKRecurse(node.next, index, k);
		index[0]++;
		return index[0] == k ? node : nodeK;
	}

	//反转该链表
	public void reverse() {
		//		reverseRecurse(head.next);
		Node<E> node = head.next;
		if (node == null)
			return;
		Node<E> pre = node, next = node.next;

		while (next != null) {
			node = next;
			next = node.next;
			node.next = pre;
			pre = node;
		}
		head.next.next = null;
		head.next = node;
	}

	//反转该链表，递归实现
	public void reverseRecurse() {
		if (head.next == null)
			return;
		reverseRecurse(head.next);

	}

	Node<E> reverseRecurse(Node<E> node) {
		if (node.next == null) {
			head.next.next = null;
			head.next = node;
			return node;
		}
		Node<E> nodeAfter = reverseRecurse(node.next);
		nodeAfter.next = node;
		return node;
	}

	public void print() {
		if (head.next == null)
			System.out.print(" null link");
		else {
			Node<E> n = head;
			while (n.next != null) {
				System.out.print(" " + n.next.data);
				n = n.next;
			}
			System.out.print(" \n");
		}
	}

	public static void main(String[] args) {
		int arr[] = { 2, 3, 4, 6, 1, 9, 8, 0 };
		Link<Integer> link = new Link<Integer>();
		for (int a : arr) {
			link.add(a);
		}
		link.print();
		arr = new int[] { 2, 0, 4, 6, 1, 9, 8, 3 };
		for (int a : arr) {
			link.remove(a);
		}
	}

}
