package books.jianzhioffer;

import util.Tools;

public class Question41 {

	int[] getPair(int[] arr, int n) {
		for (int i = 0; i < arr.length; i++) {
			int num1 = arr[i];
			int num2 = n - num1;
			if (num2 < num1) {
				break;
			}
			if (hasNumber(arr, i + 1, arr.length - 1, num2))
				return new int[] { num1, num2 };
		}
		return new int[] {};
	}

	boolean hasNumber(int[] arr, int start, int end, int num) {
		int l = start, u = end;
		while (l <= u) {
			int mid = (l + u) / 2;
			if (arr[mid] > num)
				u = mid - 1;
			else if (arr[mid] < num)
				l = mid + 1;
			else
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Question41 q = new Question41();
		int[] arr = { 1, 2, 4, 5, 7, 11, 15 };
		Tools.print(q.getPair(arr, 16));
	}

}
