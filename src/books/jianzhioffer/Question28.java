package books.jianzhioffer;

import util.Tools;

public class Question28 {

	int count = 0;

	void printAllSequence(int[] arr) {
		printAll(arr, 0);
		Tools.println("count:" + count);
	}

	void printAll(int[] arr, int index) {
		if (index == arr.length - 1) {
			Tools.print(arr);
			count++;
		}
		for (int i = index; i < arr.length; i++) {
			Tools.swap(arr, index, i);
			printAll(arr, index + 1);
		}
	}

	public static void main(String[] args) {
		Question28 q = new Question28();
		int[] arr = { 1, 2, 3, 4, 5 };
		q.printAllSequence(arr);
	}

}
