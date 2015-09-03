package datastructure.search;

public class SearchAlgrithm {

	// 顺序表的二分查找
	int binarySearch(int[] arr, int n) {
		int pos = -1;
		if (arr == null)
			return pos;
		int s = 0, e = arr.length - 1;
		while (s <= e) {
			int m = (s + e) / 2;
			if (arr[m] < n) {
				s = m + 1;
			} else if (arr[m] > n) {
				e = m - 1;
			} else {
				pos = m;
				break;
			}
		}

		return pos;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		SearchAlgrithm searchAlgrithm = new SearchAlgrithm();
		int pos = searchAlgrithm.binarySearch(arr, 11);
		System.out.println(pos);

	}

}
