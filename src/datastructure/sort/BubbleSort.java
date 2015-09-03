package datastructure.sort;

import util.Tools;

public class BubbleSort {
	int count = 0;

	/**
	 * 用变量end定位到交换的最后一个数，下一次只需循环到此即可
	 * @param arr
	 */
	void sort(int[] arr) {
		int end = arr.length - 1;
		while (true) {
			int e = end;
			for (int i = 0; i < e; i++) {
				count++;
				if (arr[i] > arr[i + 1]) {
					Tools.swap(arr, i, i + 1);
					end = i;
				}
			}
			Tools.print(arr);
			Tools.println("count:" + count + "," + end);
			if (e == end)
				break;
		}
	}

	public void sort2(int[] a) {
		count = 0;
		int temp = 0;
		for (int i = a.length - 1; i > 0; --i) {
			boolean isSort = false;
			for (int j = 0; j < i; ++j) {
				count++;
				if (a[j + 1] < a[j]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					isSort = true;
				}
			}
			Tools.print(a);
			Tools.println("count:" + count);
			if (!isSort)
				break;
		}
	}

	public static void main(String[] args) {
		int arr[] = { 4, 5, 6, 2, 3, 7, 9, 8, 4, 5, 6, 2, 3, 7, 9, 8 };
		BubbleSort b = new BubbleSort();
		b.sort(arr);
		arr = new int[] { 4, 5, 6, 2, 3, 7, 9, 8, 4, 5, 6, 2, 3, 7, 9, 8 };
		b.sort2(arr);
	}
}
