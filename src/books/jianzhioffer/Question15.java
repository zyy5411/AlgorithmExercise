package books.jianzhioffer;

import datastructure.basestructure.Link;
import datastructure.basestructure.Node;

public class Question15 {

	Node findBackK(Link link, int k) {
		return link.findKFromBack(k);
	}

	public static void main(String[] args) {

		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Link link = new Link();
		for (int a : arr) {
			link.add(a);
		}
		link.print();
		System.out.println(link.findKFromBack(9));
		System.out.println(link.findKFromBackRecurse(2));
	}

}
