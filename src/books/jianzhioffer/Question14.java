package books.jianzhioffer;

import util.Tools;

public class Question14 {

	void sort(int[] arr) {
		int l = 0, u = arr.length - 1;
		while (l < u) {
			while (l < u && (arr[l] & 1) == 1) {
				l++;
			}
			while (l < u && (arr[u] & 1) == 0) {
				u--;
			}
			if (l >= u)
				break;
			Tools.swap(arr, l, u);
		}
	}

	public static void main(String[] args) {
		int[] arr = { -1, -2, 3, 5, 7, 8, 9, 0, 6 };
		Question14 q = new Question14();
		q.sort(arr);
		Tools.print(arr);
	}

}
