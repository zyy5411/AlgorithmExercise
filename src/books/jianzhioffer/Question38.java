package books.jianzhioffer;

import util.Tools;

public class Question38 {

	int findRepeatCount(int[] arr, int k) {
		int l = 0, u = arr.length - 1;
		int start = l, end = u;
		while (l <= u) {
			int mid = (l + u) / 2;
			if (arr[mid] <= k)
				l = mid + 1;
			else {
				u = mid - 1;
			}
		}
		end = u;

		l = 0;
		u = arr.length - 1;
		while (l <= u) {
			int mid = (l + u) / 2;
			if (arr[mid] < k)
				l = mid + 1;
			else {
				u = mid - 1;
			}
		}
		start = l;
		Tools.print("" + start + "," + end);

		return end - start + 1;
	}

	public static void main(String[] args) {
		Question38 q = new Question38();
		int[] arr = { 1, 2, 3, 3, 3, 3, 4, 5, 5, 5, 5, 6 };
		System.out.println(q.findRepeatCount(arr, 3));
	}

}
