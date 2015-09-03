package com.microsoft;

import datastructure.basestructure.Link;
import datastructure.basestructure.Node;

/**
 * ½«L:(L0,L1,L2...Ln) --> (L0,Ln,L1,Ln-1,L2,Ln-2,...)
 * @author Administrator
 *
 */
public class ChangeLinkNodes {

	void change(Node<Integer> head) {
		if (null == head || null == head.next)
			return;
		Node<Integer> end1 = head, end2 = head;
		while (null != end2.next && null != end2.next.next) {
			end1 = end1.next;
			end2 = end2.next.next;
		}
		Node<Integer> head2 = reverseNode(end1.next);
		end1.next = null;
		Node<Integer> tmpNode2, head1 = head;
		while (null != head2) {
			tmpNode2 = head2.next;
			head2.next = head1.next;
			head1.next = head2;
			head1 = head2.next;
			head2 = tmpNode2;
		}

		head.print();

	}

	Node<Integer> reverseNode(Node<Integer> head) {

		Node<Integer> node = head;
		if (node == null)
			return head;
		Node<Integer> pre = node, next = node.next;

		while (next != null) {
			node = next;
			next = node.next;
			node.next = pre;
			pre = node;
		}
		head.next = null;
		return node;
	}

	public static void main(String[] args) {
		ChangeLinkNodes c = new ChangeLinkNodes();
		Link<Integer> link = new Link<Integer>();
		for (int i = 10; i >= 0; i--) {
			link.add(i);
		}
		c.change(link.head.next);
	}
}
