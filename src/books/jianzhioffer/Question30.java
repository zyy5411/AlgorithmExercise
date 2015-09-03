package books.jianzhioffer;

import java.util.Arrays;

import util.Tools;

public class Question30 {

	int[] findMinK(int[] arr, int k) {

		int index = 0;
		int start = 0, end = arr.length - 1;
		index = partition(arr, start, end);
		while (k != index) {
			if (index < k) {
				start = index + 1;
			} else {
				end = index - 1;
			}
			index = partition(arr, start, end);
		}

		return Arrays.copyOf(arr, k);
	}

	int partition(int[] arr, int start, int end) {
		int l = start + 1, u = end;
		while (true) {
			while (l <= end && arr[l] <= arr[start]) {
				l++;
			}
			while (arr[u] > arr[start]) {
				u--;
			}
			if (l >= u)
				break;
			Tools.swap(arr, l, u);
		}
		Tools.swap(arr, start, u);
		return u;
	}

	public static void main(String[] args) {
		Question30 q = new Question30();
		int[] arr = { 3, 3, 4, 4, 4, 5, 6, 2, 3, 1, 9, 0 };
		int k = 7;
		Tools.print(q.findMinK(arr, k));

	}
}
