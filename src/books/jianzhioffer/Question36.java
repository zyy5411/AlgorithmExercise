package books.jianzhioffer;

import java.util.Arrays;

public class Question36 {

	int getDisOrderCount(int[] arr) {

		int[] copy = Arrays.copyOf(arr, arr.length);
		return splitAndCount(arr, copy, 0, arr.length - 1);
	}

	int splitAndCount(int[] arr, int[] copy, int start, int end) {
		if (start == end)
			return 0;
		int mid = (start + end) / 2;
		int count1 = splitAndCount(copy, arr, start, mid);
		int count2 = splitAndCount(copy, arr, mid + 1, end);
		int count = 0, index = start, index1 = start, index2 = mid + 1;
		while (index1 <= mid && index2 <= end) {
			if (copy[index1] > copy[index2]) {
				count += mid - index1 + 1;
				arr[index++] = copy[index2++];
			} else {
				arr[index++] = copy[index1++];
			}
		}
		for (int i = index1; i <= mid; i++)
			arr[index++] = copy[i];
		for (int i = index2; i <= end; i++)
			arr[index++] = copy[i];

		return count + count1 + count2;

	}

	public static void main(String[] args) {
		int[] arr = { 7, 5, 6, 4, 3, 2 };
		Question36 q = new Question36();
		System.out.println(q.getDisOrderCount(arr));
	}

}
