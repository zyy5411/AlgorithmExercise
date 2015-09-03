package datastructure.sort;

import java.util.Random;

import util.Tools;

public class QuickSort {

	Random r = new Random();

	void sort(int[] arr, int start, int end) {
		if (start >= end)
			return;
		int index = partition(arr, start, end);
		//		Tools.print(arr);
		sort(arr, start, index - 1);
		sort(arr, index + 1, end);
	}

	int partition(int[] arr, int start, int end) {
		if (start == end)
			return start;
		int l = start + 1, u = end;
		//		Tools.print(arr);
		while (true) {
			while (l <= end && arr[l] <= arr[start])
				l++;
			while (arr[u] > arr[start])
				u--;
			if (l > u)
				break;
			Tools.swap(arr, l++, u--);
		}
		Tools.swap(arr, start, u);

		return u;
	}

	//Adam Drozdek的数据结构与算法书中的代码，要先将最大的元素放在末尾，个人不喜欢。
	void quickSort(int[] data, int first, int last) {
		int lower = first + 1, upper = last;
		Tools.swap(data, first, (first + last) / 2);
		int bound = data[first];
		while (lower <= upper) {
			while (data[lower] < bound)
				lower++;
			while (data[upper] > bound)
				upper--;
			if (lower < upper)
				Tools.swap(data, upper--, lower++);
			else
				lower++;
		}

		Tools.swap(data, upper, first);
		if (first < upper - 1)
			Tools.swap(data, first, upper - 1);
		if (upper + 1 < last)
			Tools.swap(data, upper + 1, last);

	}

	public static void main(String[] args) {

		int time = 100;
		QuickSort b = new QuickSort();
		while (time-- > 0) {
			int arr[] = Tools.getRandomNumbersFromN(0, 100, 30);
			//			int arr[] = { 1, 2, 6, 4, 3, 2, 3, 4, 5, 1 };
			b.sort(arr, 0, arr.length - 1);
			//			Tools.print(arr);
			for (int i = 0; i < arr.length - 1; i++)
				if (arr[i] > arr[i + 1]) {
					System.err.println("sort error");
				}
		}

	}
}
