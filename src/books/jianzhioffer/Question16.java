package books.jianzhioffer;

import datastructure.basestructure.Link;

public class Question16 {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Link link = new Link();
		for (int a : arr) {
			link.add(a);
		}
		link.print();
		link.reverse();
		link.print();
		link.reverseRecurse();
		link.print();
	}
}
